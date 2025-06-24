/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.JFrame;

/**
 *
 * @author RT
 */
public class WindowsManager {
    public static void openNewWindow(JFrame currentWindow, JFrame newWindow) {
        newWindow.setVisible(true);
        currentWindow.dispose(); // Ferme la fenêtre actuelle
    }
}
