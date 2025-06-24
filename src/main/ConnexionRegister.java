package main;

import component.PanelCover;
import component.PanelLoginAndRegister;
import models.Tournament;
import utils.WindowsManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ConnexionRegister extends javax.swing.JFrame {

    // Formatage des nombres pour l'animation (3 décimales)
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    
    // Variables de layout et composants
    private MigLayout layout; // Gestionnaire de disposition
    private PanelCover cover; // Panel pour l'image de couverture
    private PanelLoginAndRegister loginAndRegister; // Panel pour les formulaires
    
    // États et dimensions
    private boolean isLogin = true; // True = mode Login, False = mode Register
    private final double addSize = 30; // Taille supplémentaire pendant l'animation
    private final double coverSize = 40; // Taille initiale du panel cover (40%)
    private final double loginSize = 60; // Taille initiale du panel login (60%)

    
    public ConnexionRegister() {
        initComponents(); // Méthode générée par NetBeans
        init(); // Notre méthode d'initialisation personnalisée
    }

    /**
     * Initialise la disposition et les animations
     */
    private void init() {
        // Configuration du layout principal
        layout = new MigLayout("fill, insets 0"); // Remplissage complet, pas de marges
        
        // Création des panels
        cover = new PanelCover(); // Panel visuel (image/design)
        loginAndRegister = new PanelLoginAndRegister(); // Panel des formulaires
        
        // Configuration de l'animation
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                // fraction varie de 0 à 1 pendant l'animation
                double fractionCover; // Fraction pour le panel cover
                double fractionLogin; // Fraction pour le panel login
                double size = coverSize; // Taille de base
                
                // Animation de la taille (agrandissement puis rétrécissement)
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                
                // Calcul des positions selon le mode (login ou register)
                if (isLogin) {
                    // Animation vers la gauche (passage en mode register)
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    // Animation vers la droite (retour en mode login)
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                
                // Changement de formulaire à mi-parcours
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                
                // Formatage et application des nouvelles positions
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate(); // Rafraîchir l'affichage
            }

            @Override
            public void end() {
                // Inverser l'état à la fin de l'animation
                isLogin = !isLogin;
            }
        };
        
        // Configuration de l'animateur
        Animator animator = new Animator(800, target); // Durée: 800ms
        animator.setAcceleration(0.5f); // Accélération progressive
        animator.setDeceleration(0.5f); // Décélération progressive
        animator.setResolution(0); // Fluidité maximale
        
        // Ajout des composants au panel principal
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%");
        
        // Configuration initiale des panels
        loginAndRegister.showRegister(!isLogin);
        cover.login(isLogin);
        
        // Ajout de l'événement de clic sur le panel cover
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Démarrer l'animation si elle n'est pas déjà en cours
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        
        // Ajout du listener pour la redirection après login
        loginAndRegister.setLoginSuccessListener(() -> {
            Tournament tournamentConnecte= loginAndRegister.getCurrentTournament();
            // Ouvrir la nouvelle fenêtre
            WindowsManager.openNewWindow(this, new GestionnaireInterface(tournamentConnecte));
            
            // Fermer cette fenêtre
            dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnexionRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnexionRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnexionRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnexionRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnexionRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
