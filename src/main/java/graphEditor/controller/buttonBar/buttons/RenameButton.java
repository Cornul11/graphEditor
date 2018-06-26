package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.buttonBar.actions.RenameAction;
import graphEditor.view.GraphPanel;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

public class RenameButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_N);
        setToolTipText("rename");
        setEnabled(false);
    }

    public RenameButton(String name, GraphPanel panel) {
        super(new RenameAction(name, panel));
        setButtonProperties();
    }
}