package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.globalEdits.UndoAction;
import graphEditor.view.GraphPanel;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class UndoButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_Z);
        setToolTipText("undo");
        setEnabled(false);
    }

    public UndoButton(GraphPanel panel) {
        super(new UndoAction("", new ImageIcon("target/classes/icons/undo.png"), panel));
        setButtonProperties();
    }
}