package swing;

import utils.BaseStyle;
import java.awt.*;
import javax.swing.*;

public class MyTextField extends JTextField {
    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";

    public MyTextField() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(BaseStyle.FOREGROUND_COLOR);
        setFont(BaseStyle.DEFAULT_FONT);
        setSelectionColor(BaseStyle.SELECTION_COLOR);
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(BorderFactory.createEmptyBorder(10, left, 10, right));
    }

    @Override
protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(BaseStyle.BACKGROUND_COLOR);
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), BaseStyle.BORDER_RADIUS, BaseStyle.BORDER_RADIUS);
    paintIcon(g);
    super.paintComponent(g);
    
    // Déplacer l'affichage du hint ici et utiliser getText().length() == 0
    if (getText().length() == 0 && !hint.isEmpty()) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Insets ins = getInsets();
        FontMetrics fm = g.getFontMetrics();
        g2.setColor(BaseStyle.HINT_COLOR);
        g2.drawString(hint, ins.left, getHeight() / 2 + fm.getAscent() / 2 - 2);
    }
}

// Supprimez complètement la méthode paint() existante

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        if (getText().isEmpty()) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            Insets ins = getInsets();
//            FontMetrics fm = g.getFontMetrics();
//            g.setColor(BaseStyle.HINT_COLOR);
//            g.drawString(hint, ins.left, getHeight() / 2 + fm.getAscent() / 2 - 2);
//        }
//    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }
}
