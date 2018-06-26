package graphEditor.controller.buttonBar.buttons;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import java.awt.event.KeyEvent;

import graphEditor.controller.buttonBar.actions.AddEdgeAction;
import graphEditor.view.GraphPanel;

public class AddEdgeButton extends JButton {
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_N); //Alt+N does the same
        setToolTipText("add edge");
        setEnabled(false); //by the default the button will be turned off because by default there is no vertex selected
    }

    public AddEdgeButton(String name, GraphPanel panel) {
        super(new AddEdgeAction(name, panel)); //set the corresponding action
        setButtonProperties();
    }
}