/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author RT
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.text.JTextComponent;

public class ComponentFactory {
    
    // Applique le style par défaut à un JTextField
    public static void applyTextFieldStyle(JTextField field) {
        field.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        field.setBackground(new Color(0, 0, 0, 0)); // Transparent
        field.setForeground(Color.decode("#7A8C8D"));
        field.setFont(new Font("sansserif", Font.PLAIN, 13));
        field.setSelectionColor(new Color(75, 175, 152));
        field.setCaretColor(Color.decode("#7A8C8D"));
    }
    
    // Applique le style similaire à un JPasswordField
    public static void applyPasswordFieldStyle(JPasswordField field) {
        applyTextFieldStyle(field); // Réutilise le style de base
        field.setEchoChar('•'); // Caractère spécial pour les mots de passe
    }
    
    // Applique le style similaire à un JTextArea
    public static void applyTextAreaStyle(JTextArea area) {
        area.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        area.setBackground(new Color(0, 0, 0, 0));
        area.setForeground(Color.decode("#7A8C8D"));
        area.setFont(new Font("sansserif", Font.PLAIN, 13));
        area.setSelectionColor(new Color(75, 175, 152));
        area.setCaretColor(Color.decode("#7A8C8D"));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
    }
    
    // Méthode factory pour créer des JTextField pré-stylés
    public static JTextField createStyledTextField(int columns) {
        JTextField field = new JTextField(columns);
        applyTextFieldStyle(field);
        return field;
    }
    
    // Méthode factory pour créer des JPasswordField pré-stylés
    public static JPasswordField createStyledPasswordField(int columns) {
        JPasswordField field = new JPasswordField(columns);
        applyPasswordFieldStyle(field);
        return field;
    }
    
    // Méthode factory pour créer des JTextArea pré-stylés
    public static JScrollPane createStyledTextArea(int rows, int columns) {
        JTextArea area = new JTextArea(rows, columns);
        applyTextAreaStyle(area);
        return new JScrollPane(area);
    }
    
    public static void applyComboBoxStyle(JComboBox<?> combo) {
        combo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        combo.setBackground(new Color(7, 164, 121));
        combo.setForeground(Color.decode("#7A8C8D"));
        combo.setFont(new Font("sansserif", Font.PLAIN, 13));
        
        // Style pour le bouton de la combo box
        Component comp = combo.getEditor().getEditorComponent();
        if (comp instanceof JTextComponent) {
            ((JTextComponent) comp).setBorder(null);
            ((JTextComponent) comp).setOpaque(false);
        }
    }
    
    public static <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> combo = new JComboBox<>(items);
        applyComboBoxStyle(combo);
        return combo;
    }
}