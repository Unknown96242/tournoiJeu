
package swing;

/**
 *
 * @author RT
 */

import utils.BaseStyle;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.text.*;

public class MyNumberField extends JTextField {
    
    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }
    public MyNumberField(int columns) { 
        super(columns);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(BaseStyle.BACKGROUND_COLOR);
        setForeground(BaseStyle.FOREGROUND_COLOR);
        setFont(BaseStyle.DEFAULT_FONT);
        setSelectionColor(BaseStyle.SELECTION_COLOR);
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new NumericFilter());
        
    }

    private static class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
    
    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            //  suffix is right
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}


