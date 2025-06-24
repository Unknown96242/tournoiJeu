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

// MyComboBox.java
import javax.swing.*;
import java.awt.*;

public class MyComboBox<E> extends JComboBox<E> {
    public MyComboBox(E[] items) {
        super(items);
        setFont(BaseStyle.DEFAULT_FONT);
        setBackground(BaseStyle.BACKGROUND_COLOR);
        setForeground(BaseStyle.FOREGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBackground(isSelected ? BaseStyle.SELECTION_COLOR : BaseStyle.BACKGROUND_COLOR);
                label.setForeground(BaseStyle.FOREGROUND_COLOR);
                label.setFont(BaseStyle.DEFAULT_FONT);
                return label;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(BaseStyle.BACKGROUND_COLOR);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), BaseStyle.BORDER_RADIUS, BaseStyle.BORDER_RADIUS);
        super.paintComponent(g);
        g2.dispose();
    }
}

