package graphEditor.controller.menuBar.window.actions;

import graphEditor.view.GraphFrame;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class DefaultAction extends AbstractAction {
    final private GraphFrame frame;

    public DefaultAction(GraphFrame frame) {
        super("Restore Default Layout"); //set item name
        setEnabled(true);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setSize(frame.getDefaultDimension()); //set the default dimensions
        frame.setLocation(frame.getDefaultPosX(), frame.getDefaultPosY()); //return to the default position
    }
}