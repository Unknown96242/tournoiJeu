package component;

import swing.Button;
import swing.MyPanel;
import swing.MyTextArea;
import swing.MyLabel;
import swing.MyNumberField;
import swing.MyComboBox;
import swing.MyDatePicker;
import swing.MyPasswordField;
import swing.MyTextField;
import dao.TournamentDao;
import models.Tournament;
import utils.UtilsFonction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    private Tournament tournois;

    
    

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }
    
 
    
    private void initRegister() {
        // Créer un nouveau panel pour le contenu
        JPanel contentPanel = new JPanel(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]10[]10[]25[]push"));
        contentPanel.setBackground(Color.WHITE);

        // Ajouter tous les composants à contentPanel
        JLabel label = new JLabel("Enregistrer son tournoi");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        contentPanel.add(label, "gaptop 30");
        
        
        tf_emailLogin= new MyTextField();
//        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        tf_emailLogin.setHint("Votre Email");
        contentPanel.add(tf_emailLogin, "w 60%");
        
        pwf_password = new MyPasswordField();
        pwf_password.setHint("Mot de passe");
        contentPanel.add(pwf_password, "w 60%");
        
        pwf_confirmPassword = new MyPasswordField();
        pwf_confirmPassword.setHint("Confirmer votre mot de passe");
        contentPanel.add(pwf_confirmPassword, "w 60%");
        
        tf_Name = new MyTextField();
//        txtName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/raven/icon/user.png")));
        tf_Name.setHint("Nom tournoi");
        contentPanel.add(tf_Name, "w 60%");
        
        tf_game = new MyTextField();
        tf_game.setHint("jeu");
        contentPanel.add(tf_game, "w 60%");
        
        String[] Type = {"solo", "team"};
        cbx_type = new MyComboBox<>(Type);
        contentPanel.add(cbx_type, "w 60%");
        
//        String[] is_online = {"En ligne ", "En presentiel"};
//        JComboBox<String> cbx_isOnline = new MyComboBox<>(is_online);
//        cbx_isOnline.setSelectedIndex(0);
//        contentPanel.add(cbx_isOnline);

        MyLabel lbl_isOnline = new MyLabel("En ligne: ");

        rbtn_Online = new JRadioButton("oui");
        rbtn_Offline = new JRadioButton("non");
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(rbtn_Online);
        group.add(rbtn_Offline);
        
        contentPanel.add(lbl_isOnline);
        contentPanel.add(rbtn_Online);
        contentPanel.add(rbtn_Offline);
        
//        MyTextField tf_location = new MyTextField();
//        tf_location.setHint("Lieu");
//        contentPanel.add(tf_location, "w 60%");
        
        
        MyLabel pnl_title = new MyLabel("Nombre max de participant :");
//        pnl_title.setLayout(new FlowLayout(FlowLayout.LEFT, 13, 0));
        contentPanel.add(pnl_title);
        
        nf_maxParticipant = new MyNumberField(10);
//        nf_maxParticipant.Set("Nombre maximum de participant");
        contentPanel.add(nf_maxParticipant, "w 60%");
        
        String[] Status = {"Statuts Tournoi", "en preparation", "en cours", "Termine"};
        cbx_Status = new MyComboBox<>(Status);
        cbx_Status.setSelectedIndex(0);
        
//        cbx_Status.addActionListener(e -> {
//            if(cbx_Status.getSelectedIndex() == 0){
//                cbx_Status.setSelectedIndex(-1);     
//            }
//        });
        
//        cbx_Status.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
//            @Override
//            public java.awt.Component getListCellRendererComponent(
//                javax.swing.JList list, Object value, int index,
//                boolean isSelected, boolean cellHasFocus) {
//
//                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//
//                    // Si c'est l'élément "Type", le rendre gris et non sélectionnable
//                    if (index == 0 || value.equals("Type")) {
//                        setForeground(Color.GRAY);
//                        setEnabled(false);  // désactive l'interaction visuelle (pas fonctionnelle mais visuellement clair)
//                    } else {
//                        setEnabled(true);
//                    }
//
//             return this;
//            }
//        });
        
        contentPanel.add(cbx_Status, "w 60%");
        
        
        
        
        tf_recompense = new MyTextField();
        tf_recompense.setHint("Recompense");
        contentPanel.add(tf_recompense, "w 60%");
        
        ta_Description = new MyTextArea(6, 30);
        ta_Description.setText("Description");
        contentPanel.add(ta_Description);

        
        datePanel = new MyPanel();
        datePanel.setLayout(new BorderLayout(0, 5));
//        datePanel.setBackground(new Color(255, 255, 255));
        
        // Ligne des titres
        MyPanel titleRow = new MyPanel();
        titleRow.setLayout(new FlowLayout(FlowLayout.LEFT, 13, 0));
        titleRow.add(new MyLabel("Date de :"));
        titleRow.add(Box.createHorizontalStrut(18));
        titleRow.add(new MyLabel("Début"));
        titleRow.add(Box.createHorizontalStrut(78));
        titleRow.add(new MyLabel("Fin"));

        // Ligne des pickers
        MyPanel pickerRow = new MyPanel();
        pickerRow.setLayout(new FlowLayout(FlowLayout.LEFT, 13, 0));
        pickerRow.add(Box.createHorizontalStrut(53));
        date_debut = new MyDatePicker();
        date_fin = new MyDatePicker();
        pickerRow.add(date_debut);
        pickerRow.add(Box.createHorizontalStrut(18));
        pickerRow.add(date_fin);

        datePanel.add(titleRow, BorderLayout.NORTH);
        datePanel.add(pickerRow, BorderLayout.CENTER);
        contentPanel.add(datePanel, "w 60%");
        
        
        
        cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("S'INSCRIRE");
        contentPanel.add(cmd, "w 40%, h 40, gapbottom 30");
        

        // Configurer le JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Vider et reconfigurer le register panel
        register.removeAll();
        register.setLayout(new BorderLayout());
        register.add(scrollPane, BorderLayout.CENTER);
        
        // Rafraîchir l'affichage
        register.revalidate();
        register.repaint();
        
        cmd.addActionListener( e -> registerTournament());

        
        
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Se connecter");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        
        tf_email = new MyTextField();
        tf_email.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        tf_email.setHint("Email");
        login.add(tf_email, "w 60%");
        
        pwf_password = new MyPasswordField();
        pwf_password.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        pwf_password.setHint("Mot de passe");
        login.add(pwf_password, "w 60%");
        
        JButton cmdForget = new JButton("Mot de passe oublie ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SE CONNECTER");
        login.add(cmd, "w 40%, h 40");
        
        cmd.addActionListener(e ->connectionHost() );
        
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    
    private void registerTournament() {
        // Récupérer toutes les valeurs des champs
        boolean succes = true;
        if(validateField()){
            try {
                String email = tf_emailLogin.getText().trim();
                String password = UtilsFonction.encrypt(pwf_confirmPassword.getText().trim());
                String name = tf_Name.getText().trim();
                String game = tf_game.getText().trim();
                String type = cbx_type.getSelectedItem().toString();
                boolean isOnline = rbtn_Online.isSelected();
                int maxParticipant = Integer.parseInt(nf_maxParticipant.getText().trim());
                String status = cbx_Status.getSelectedItem().toString();
                double recompenses = Double.parseDouble(tf_recompense.getText().trim()) ;
                String description = ta_Description.getText().trim();
                LocalDate dateDebut = date_debut.getSelectedDate();
                LocalDate dateFin = date_fin.getSelectedDate();
                Tournament newTournament = new Tournament(email, password, name, game, type, isOnline, maxParticipant, status, recompenses, description, dateDebut, dateFin);

                try {
                    TournamentDao.createTournament(newTournament);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                    succes = false;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                succes = false;
            }
            if(succes){
                JOptionPane.showMessageDialog(this, "Tournois enregistre avec succes");
                resetForm();
            }else{
                showMessageError("Veuillez entrer convenablement utes les informations");
            }
        }
    }
    private void connectionHost() {
        Boolean loginIsValid = false;
        Tournament currentTournament = null;


        // Récupérer toutes les valeurs des champs
        String email = tf_email.getText();
        String password ;
        if(UtilsFonction.isValidEmail(email)){
            password = pwf_password.getText().trim();
            if(!UtilsFonction.isEmpty(password)){
//                Tournament currentTournament = null;
                try {
                    currentTournament = TournamentDao.getCurrentTournament(email);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PanelLoginAndRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (currentTournament!= null ){
                    if(currentTournament.getEmail().equals(email)){
                        String currentPass = UtilsFonction.decrypt(currentTournament.getPassword()); 
                        if(password.equals(currentPass));
                        loginIsValid = true;
                    }
                }                
            } 
        }
        
        if (loginIsValid) { // Condition de connection
            if (successListener != null) {
                this.tournois = currentTournament;
                successListener.onLoginSuccess();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Erreur lors de la saisie des donnees");
        }
      
    }

    private void resetForm() {
        tf_emailLogin.setText("");
        pwf_password.setText("");
        pwf_confirmPassword.setText("");
        tf_Name.setText("");
        tf_game.setText("");
        cbx_type.setSelectedIndex(0);
        nf_maxParticipant.setText("");
        cbx_Status.setSelectedIndex(0);
        tf_recompense.setText("");
        ta_Description.setText("Description");
    }
    
    public interface LoginSuccessListener {
        void onLoginSuccess();
    }
     
    public void setLoginSuccessListener(LoginSuccessListener listener) {
        this.successListener = listener;
    }
    
//    valider les champs du formulair d'enregistrement
    private boolean validateField(){
        String email = tf_emailLogin.getText().toString().trim();
        String password = pwf_confirmPassword.getText().trim();
        String ConfirmPass = pwf_confirmPassword.getText().trim();
        String name = tf_Name.getText().trim();
        String game = tf_game.getText().trim();
        String type = cbx_type.getSelectedItem().toString();
        String maxParticipant = nf_maxParticipant.getText();
        String status = cbx_Status.getSelectedItem().toString();
        String recompenses = tf_recompense.getText().trim();
        String description = ta_Description.getText().trim();
        LocalDate dateDebut = date_debut.getSelectedDate();
        LocalDate dateFin = date_fin.getSelectedDate();
        
        List<String>champs=List.of(email, password, ConfirmPass, name, game, type, maxParticipant, status, recompenses, description);
        boolean textField = champs.stream().anyMatch(c -> c.isEmpty());
        boolean textMatchPassword = !password.equals(ConfirmPass);
        boolean textLengthPassword = password.length()<6;
        boolean dateValidate = !dateDebut.isBefore(dateFin);
        if(textField){
            showMessageError("Veuillez remplir tous les champs");
            return false;
        }
        
        if(textMatchPassword){
            showMessageError("Les mots de passe ne sont pas identiques");
            return false;
        }
        
        if(textLengthPassword){
            showMessageError("Le mot de passe doit contenir minimum 6 caracteres");
            return false;
        }
        if (dateValidate) {
            showMessageError("Veuillez entrer des date convenabeles");
            return false;
        }
                
        return true;
    }
    
    
    public Tournament getCurrentTournament(){
        return tournois;
    }
    
    private void showMessageError(String message){
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

    }
    

    @SuppressWarnings("unchecked")
    
    private MyTextField tf_email;
    private MyTextField tf_emailLogin;
    private MyPasswordField pwf_password;
    private MyPasswordField pwf_confirmPassword;
    private MyTextField tf_Name;
    private MyTextField tf_game;
    private MyComboBox<String> cbx_type; 
    private JRadioButton rbtn_Online;
    private JRadioButton rbtn_Offline;
    private MyNumberField nf_maxParticipant;
    private JComboBox<String> cbx_Status;
    private MyTextField tf_recompense;
    private MyTextArea ta_Description;
    private MyPanel datePanel;
    private MyDatePicker date_debut;
    private MyDatePicker date_fin;
    private Button cmd;
    
    private LoginSuccessListener successListener;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables

    
}
