/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

/**
 *
 * @author RT
 */
import utils.BaseStyle;
import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {
    public MyRadioButton(String text) {
        super(text);
        setFont(BaseStyle.DEFAULT_FONT);
        setBackground(BaseStyle.BACKGROUND_COLOR);
        setForeground(BaseStyle.FOREGROUND_COLOR);
    }
}
