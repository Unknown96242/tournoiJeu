/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pages;

/**
 *
 * @author RT
 */

    
import dao.DatabaseService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Match;
import models.Player;
import dao.MatchDao;
import dao.PlayerDao;

public class MatchMakingPanel extends JPanel {

    private int tournamentId;
    private int currentRound = 1;
    private List<Player> activePlayers;
    private List<Match> currentMatches;
    private JPanel matchesPanel;
    private JButton nextRoundButton;
    
    
    
    
    public MatchMakingPanel(int tournamentId) {
        this.tournamentId = tournamentId;
//        initComponents();
        initializeComponents();
        loadPlayers();
        startNextRound();
    }
    
    public void reloadData() {
    // Recharge la liste des joueurs actifs
    loadPlayers();
    // Redémarre le round courant (affiche les matchs du round en cours)
    startNextRound();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Panel pour afficher les matchs en cours
        matchesPanel = new JPanel();
        matchesPanel.setLayout(new BoxLayout(matchesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(matchesPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        // Bouton pour passer au round suivant
        nextRoundButton = new JButton("Round Suivant");
        nextRoundButton.addActionListener(e -> startNextRound());
        nextRoundButton.setEnabled(false);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextRoundButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadPlayers() {
        try {
            activePlayers = PlayerDao.getAllPlayer(tournamentId);
            // Filter only active players (assuming status "active" means still in tournament)
            activePlayers.removeIf(player -> !"en lisse".equals(player.getStatus()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement des joueurs : " + ex.getMessage(), 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void startNextRound() {
        // Check if tournament is over (only one player left)
        if (activePlayers.size() <= 1) {
            if (activePlayers.size() == 1) {
                JOptionPane.showMessageDialog(this, 
                    "Tournoi terminé ! Le gagnant est : " + activePlayers.get(0).getUsername(), 
                    "Tournoi terminé", JOptionPane.INFORMATION_MESSAGE);
            }
            nextRoundButton.setEnabled(false);
            return;
        }
        
        // Effacer les matchs précédents
        matchesPanel.removeAll();
        
        // Créer les matchs pour ce round
        currentMatches = createMatchesForRound();
        
        // Afficher chaque match
        for (Match match : currentMatches) {
            JPanel matchPanel = createMatchPanel(match);
            matchesPanel.add(matchPanel);
            matchesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        }
        
        // Mettre à jour l'interface
        matchesPanel.revalidate();
        matchesPanel.repaint();
        nextRoundButton.setEnabled(false); // Désactiver jusqu'à ce que tous les matchs soient terminés
    }
    
    private List<Match> createMatchesForRound() {
        List<Match> matches = new ArrayList<>();
        LocalDate now = LocalDate.now();
        
        // Appariement simple - prend les joueurs dans l'ordre
        for (int i = 0; i < activePlayers.size(); i += 2) {
            if (i + 1 >= activePlayers.size()) {
                // Nombre impair de joueurs - donne un bye à un joueur
                Player byePlayer = activePlayers.get(i);
                JOptionPane.showMessageDialog(this, 
                    byePlayer.getUsername() + " a un bye pour ce round", 
                    "Round avec bye", JOptionPane.INFORMATION_MESSAGE);
                continue;
            }
            
            Player player1 = activePlayers.get(i);
            Player player2 = activePlayers.get(i + 1);
            
            Match match = new Match(now, currentRound, player1.getId(), player2.getId(), tournamentId);
            
            try {
                match = MatchDao.addMatchInTournament(match);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Erreur de création du match : " + ex.getMessage(), 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            
            matches.add(match);
        }
        
        return matches;
    }
    
    private JPanel createMatchPanel(Match match) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Match " + match.getId()));
        
        try {
            Player player1 = findPlayerById(match.getPlayer1Id());
            Player player2 = findPlayerById(match.getPlayer2Id());
            
            JLabel matchLabel = new JLabel(
                String.format("%s (classement: %d) vs %s (classement: %d)", 
                    player1.getUsername(), player1.getRanking(),
                    player2.getUsername(), player2.getRanking()));
            
            JButton player1Button = new JButton(player1.getUsername() + " Gagant");
            JButton player2Button = new JButton(player2.getUsername() + " Gagant");
            
            player1Button.addActionListener(e -> recordMatchResult(match, player1.getId()));
            player2Button.addActionListener(e -> recordMatchResult(match, player2.getId()));
            
            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
            buttonPanel.add(player1Button);
            buttonPanel.add(player2Button);
            
            panel.add(matchLabel, BorderLayout.NORTH);
            panel.add(buttonPanel, BorderLayout.CENTER);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur de création du panneau de match : " + ex.getMessage(), 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        
        return panel;
    }
    
    private Player findPlayerById(double playerId) {
        for (Player player : activePlayers) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }
    
    
    private void recordMatchResult(Match match, int winnerId) {
        try {
            // Mettre à jour le match dans la base de données
            match.setStatus("termine");
            match.setWinnerId(winnerId);
            DatabaseService.executeUpdate(
                "UPDATE matches SET status=?, winner_id=? WHERE id=?", 
                match.getStatus(), match.getWinnerId(), match.getId());
            
            // Retirer le perdant des joueurs actifs
            double loserId = (match.getPlayer1Id() == winnerId) ? match.getPlayer2Id() : match.getPlayer1Id();
            activePlayers.removeIf(player -> player.getId() == loserId);
            
            // Mettre à jour le statut du joueur dans la base de données
            DatabaseService.executeUpdate(
                "UPDATE players SET status='elimine' WHERE id=?", 
                loserId);
            
            // Vérifier si tous les matchs sont terminés
            if (allMatchesComplete()) {
                currentRound++;
                nextRoundButton.setEnabled(true);
                JOptionPane.showMessageDialog(this, 
                    "Tous les matchs sont terminés ! Prêt pour le round suivant.", 
                    "Round terminé", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur d'enregistrement du résultat : " + ex.getMessage(), 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean allMatchesComplete() {
        for (Match match : currentMatches) {
            if (!"termine".equals(match.getStatus())) {
                return false;
            }
        }
        return true;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
