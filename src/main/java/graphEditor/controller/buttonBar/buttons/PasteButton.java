package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.globalEdits.PasteAction;
import graphEditor.view.GraphPanel;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

public class PasteButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_C);
        setToolTipText("undo");
    }

    public PasteButton(GraphPanel panel) {
        super(new PasteAction("", new ImageIcon(ClassLoader.getSystemResource("images/paste.png")), panel));
        setButtonProperties();
    }
}