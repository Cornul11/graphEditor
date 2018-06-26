package graphEditor.controller.menuBar.window.actions;

import graphEditor.view.GraphFrame;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class FullScreenAction extends AbstractAction {
    final private GraphFrame frame;
    private boolean toggle;

    public FullScreenAction(GraphFrame frame) {
        super("Toggle Full Screen");
        setEnabled(true);
        this.frame = frame;
        this.toggle = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (toggle) {
            frame.setExtendedState(JFrame.NORMAL); //return to the normal state
            frame.setSize(new Dimension(800, 600)); //set the default size
            frame.dispose(); //destroy the current frame
            frame.setUndecorated(false); //bring back the borders and the bar of the window
            frame.setVisible(true); //repaint the frame
            frame.setLocationRelativeTo(null); //set to the center of the screen
            toggle = false; //set that the full-screen toggle is off
        } else {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximize on both dimensions
            frame.dispose();
            frame.setUndecorated(true); //hide the borders and top bar
            frame.setVisible(true);
            toggle = true;
        }
    }
}