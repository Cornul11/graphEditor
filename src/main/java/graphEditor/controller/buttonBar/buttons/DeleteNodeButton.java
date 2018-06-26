package graphEditor.controller.buttonBar.buttons;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

import graphEditor.controller.buttonBar.actions.DeleteNodeAction;
import graphEditor.view.GraphPanel;

public class DeleteNodeButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_N);
        setToolTipText("delete node");
        setEnabled(false);
    }

    public DeleteNodeButton(String name, GraphPanel panel) {
        super(new DeleteNodeAction(name, panel));
        setButtonProperties();
    }
}