package graphEditor.controller.buttonBar.buttons;

import graphEditor.controller.buttonBar.actions.DeleteEdgeAction;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.KeyEvent;

public class DeleteEdgeButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("delete edge");
        setEnabled(true);
    }

    public DeleteEdgeButton(String name, GraphPanel panel) {
        super(new DeleteEdgeAction(name, panel));
        setButtonProperties();
    }
}