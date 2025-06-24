package pages;

import dao.PlayerDao;
import models.Model_Card;
import models.StatusType;
import models.Tournament;
import swing.ScrollBar;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.Player;

public class Form_Home extends javax.swing.JPanel {
    private final Tournament tournois;
    private List<Player> players;

    public Form_Home(Tournament tournament) {
        
            this.tournois = tournament;
            initComponents();
            long joueursEnLisse = 0;
            int matcRestant = 0;

            try {
                this.players = PlayerDao.getAllPlayer(tournois.getId());
    
                // Compter uniquement les joueurs EN LISSE
                joueursEnLisse = this.players.stream()
                    .filter(player -> player != null && "en lisse".equalsIgnoreCase(player.getStatus()))
                    .count();
    
                // Calculer les matchs restants seulement s'il y a des joueurs en lice

                if (joueursEnLisse > 1) {
                    matcRestant = (int) Math.ceil(joueursEnLisse / 2.0);
                } else if (joueursEnLisse == 1) {
                     // Cas particulier : un seul joueur en lice (vainqueur)
                    matcRestant = 0;
                }
            
            
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, "Erreur de chargement des joueurs", ex);
                JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement des données du tournoi", 
                "Erreur", JOptionPane.ERROR_MESSAGE);
                this.players = Collections.emptyList(); // Liste immuable vide
            }
            
            card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/profit.png")), "Cagnotte", Double.toString(tournois.getPrizePool())+" fcfa", "Increased by 60%"));
            card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "Equies en lisse",Long.toString(joueursEnLisse), "Increased by 25%"));
            card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/flag.png")), "Match restant", Integer.toString(matcRestant), "Increased by 70%"));
            //  add row table
            spTable.setVerticalScrollBar(new ScrollBar());
            spTable.getVerticalScrollBar().setBackground(Color.WHITE);
            spTable.getViewport().setBackground(Color.WHITE);
            JPanel p = new JPanel();
            p.setBackground(Color.WHITE);
            spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
            
        
        
        StatusType status = StatusType.EN_LISSE;

        if(!players.isEmpty()){
                for (Player player : players) {
                    if(player.getStatus().equalsIgnoreCase("elimine")){
                        status = StatusType.ELIMINE;
                    }
                    table.addRow(new Object[]{player.getUsername(), player.getEmail(), player.getTeam(), player.getBirthdate(), status});

                }
            }else{
                table.addRow(new Object[]{"Acun Participant trouve","", "", "", StatusType.VIDE});

            }
    }
    
    public void reloadData() {
    try {
        // Récupère à nouveau les joueurs
        this.players = PlayerDao.getAllPlayer(tournois.getId());

        // Vide le tableau
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
        model.setRowCount(0);

        // Recalcule les stats
        long joueursEnLisse = this.players.stream()
            .filter(player -> player != null && "en lisse".equalsIgnoreCase(player.getStatus()))
            .count();

        int matcRestant = 0;
        if (joueursEnLisse > 1) {
            matcRestant = (int) Math.ceil(joueursEnLisse / 2.0);
        } else if (joueursEnLisse == 1) {
            matcRestant = 0;
        }

        // Mets à jour les cards
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/profit.png")), "Cagnotte", Double.toString(tournois.getPrizePool())+" fcfa", "Increased by 60%"));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/stock.png")), "Equies en lisse",Long.toString(joueursEnLisse), "Increased by 25%"));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/icon/flag.png")), "Match restant", Integer.toString(matcRestant), "Increased by 70%"));

        // Recharge les lignes
        StatusType status = StatusType.EN_LISSE;
        if(!players.isEmpty()){
            for (Player player : players) {
                if(player.getStatus().equalsIgnoreCase("elimine")){
                    status = StatusType.ELIMINE;
                }
                model.addRow(new Object[]{player.getUsername(), player.getEmail(), player.getTeam(), player.getBirthdate(), status});
            }
        }else{
            model.addRow(new Object[]{"Acun Participant trouve","", "", "", StatusType.VIDE});
        }
    } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(Form_Home.class.getName()).log(Level.SEVERE, "Erreur de rechargement des joueurs", ex);
        JOptionPane.showMessageDialog(this, 
            "Erreur lors du chargement des données du tournoi", 
            "Erreur", JOptionPane.ERROR_MESSAGE);
        // Vide le tableau si erreur
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"Acun Participant trouve","", "", "", StatusType.VIDE});
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new component.Card();
        card2 = new component.Card();
        card3 = new component.Card();
        panelBorder1 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new swing.Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("VUE D'ENSEMBLE");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Email", "Rang", "Date Ajout", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Card card1;
    private component.Card card2;
    private component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private swing.Table table;
    // End of variables declaration//GEN-END:variables
}
