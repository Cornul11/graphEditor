package graphEditor.controller.buttonBar.buttons;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

import graphEditor.controller.buttonBar.actions.NewNodeAction;
import graphEditor.view.GraphPanel;

public class NewNodeButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_N);
        setToolTipText("new node");
    }

    public NewNodeButton(String name, GraphPanel panel) {
        super(new NewNodeAction(name, panel));
        setButtonProperties();
    }
}