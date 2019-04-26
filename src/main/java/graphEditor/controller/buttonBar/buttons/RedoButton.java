package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.globalEdits.RedoAction;
import graphEditor.view.GraphPanel;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class RedoButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_Y);
        setToolTipText("redo");
        setEnabled(false);
    }

    public RedoButton(GraphPanel panel) {
        super(new RedoAction("", new ImageIcon(ClassLoader.getSystemResource("images/redo.png")), panel));
        setButtonProperties();
    }
}