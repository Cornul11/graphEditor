package graphEditor.controller.menuBar.window.actions;

import graphEditor.view.GraphFrame;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class SetDefaultAction extends AbstractAction {
    final private GraphFrame frame;

    public SetDefaultAction(GraphFrame frame) {
        super("Set Default Layout");
        setEnabled(true);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setDefaultDimension(frame.getSize()); //set the current size to be the default size for the user
        frame.setDefaultPosX(frame.getX()); //set the current X the default X for the user
        frame.setDefaultPosY(frame.getY()); //set the current Y the default Y for the user
    }
}