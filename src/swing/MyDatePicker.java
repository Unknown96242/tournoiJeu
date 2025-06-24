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
import java.util.Date;
import javax.swing.SpinnerDateModel;

// MyDatePicker.java
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.SpinnerDateModel;

public class MyDatePicker extends JSpinner {
    public MyDatePicker() {
        super(new SpinnerDateModel());
        setFont(BaseStyle.DEFAULT_FONT);
        setForeground(BaseStyle.FOREGROUND_COLOR);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComponent editor = new JSpinner.DateEditor(this, "yyyy-MM-dd");
        editor.setBackground(BaseStyle.BACKGROUND_COLOR);
        editor.setForeground(BaseStyle.FOREGROUND_COLOR);
        setEditor(editor);
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
    
    public LocalDate getSelectedDate() {
        Date date = (Date) getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDateTime getSelectedDateTime() {
        Date date = (Date) getValue();
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
