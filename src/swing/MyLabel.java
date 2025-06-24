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

public class MyLabel extends JLabel {
    public MyLabel(String text) {
        super(text);
        setFont(BaseStyle.DEFAULT_FONT);
        setForeground(BaseStyle.FOREGROUND_COLOR);
    }
}
