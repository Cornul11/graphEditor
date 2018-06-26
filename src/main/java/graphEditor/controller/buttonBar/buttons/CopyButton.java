package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.globalEdits.CopyAction;
import graphEditor.view.GraphPanel;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

public class CopyButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_C);
        setToolTipText("undo");
    }

    public CopyButton(GraphPanel panel) {
        super(new CopyAction("", new ImageIcon("target/classes/icons/copy.png"), panel));
        setButtonProperties();
    }
}
