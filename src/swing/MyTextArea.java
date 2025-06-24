/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import utils.BaseStyle;
import javax.swing.JTextArea;

/**
 *
 * @author RT
 */
public class MyTextArea extends JTextArea {
    public MyTextArea(int rows, int cols) {
        super(rows, cols);
        setFont(BaseStyle.DEFAULT_FONT);
        setBackground(BaseStyle.BACKGROUND_COLOR);
        setForeground(BaseStyle.FOREGROUND_COLOR);
        setSelectionColor(BaseStyle.SELECTION_COLOR);
    }
}
