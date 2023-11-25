package net.ashsta.components;

import net.ashsta.App;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PasswordField extends JPasswordField {

    public PasswordField() {
        setMaximumSize(new Dimension(512, 64));
        setBorder(App.BORDER);
        setFont(App.FONT);
        ((AbstractDocument) getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter(16));
    }

    private static class CharacterLimitDocumentFilter extends DocumentFilter {

        private final int CHARACTER_LIMIT;

        public CharacterLimitDocumentFilter(int characterLimit) {
            CHARACTER_LIMIT = characterLimit;
        }

        @Override
        public void replace(FilterBypass filterBypass, int offset, int deleteLength, String text, AttributeSet attributeSet) throws BadLocationException {
            int overCharacterLimit = (filterBypass.getDocument().getLength() + text.length()) - (CHARACTER_LIMIT + deleteLength);
            if (overCharacterLimit > 0)
                text = text.substring(0, text.length() - overCharacterLimit);
            if (text.length() > 0 || deleteLength > 0)
                super.replace(filterBypass, offset, deleteLength, text, attributeSet);
        }
    }
}
