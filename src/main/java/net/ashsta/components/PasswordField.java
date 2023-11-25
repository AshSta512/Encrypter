package net.ashsta.components;

import net.ashsta.Cosmetic;
import net.ashsta.Encryption;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PasswordField extends JPasswordField {

    private static final Dimension SIZE = new Dimension(512, 64);

    public PasswordField() {
        setMaximumSize(SIZE);
        setBorder(Cosmetic.BORDER);
        setFont(Cosmetic.DEFAULT_FONT);
        ((AbstractDocument) getDocument()).setDocumentFilter(new CharacterLimitDocumentFilter());
    }

    private static class CharacterLimitDocumentFilter extends DocumentFilter {

        //TODO: This will not remove extra characters if previous set password is over new keysize length

        @Override
        public void replace(FilterBypass filterBypass, int offset, int deleteLength, String text, AttributeSet attributeSet) throws BadLocationException {
            int overCharacterLimit = (filterBypass.getDocument().getLength() + text.length()) - (Encryption.getEncryptionSettings().getKeySize() + deleteLength);
            if (overCharacterLimit > 0)
                text = text.substring(0, text.length() - overCharacterLimit);
            if (text.length() > 0 || deleteLength > 0)
                super.replace(filterBypass, offset, deleteLength, text, attributeSet);
        }
    }
}
