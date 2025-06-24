package swing;

import models.StatusType;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class TableStatus extends JLabel {

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private StatusType type;

    public void setType(StatusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type == StatusType.VIDE) {
                g = new GradientPaint(0, 0, new Color(175, 191, 29), 0, getHeight(), new Color(196, 217, 2));
            } else if (type == StatusType.EN_LISSE) {
                g = new GradientPaint(0, 0, new Color(35, 166, 97), 0, getHeight(), new Color(96, 247, 168));
            } else {
                g = new GradientPaint(0, 0, new Color(209, 19, 44), 0, getHeight(), new Color(237, 85, 105));
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(grphcs);
    }
}
