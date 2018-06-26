package graphEditor.controller;

import graphEditor.controller.menuBar.file.actions.SaveFileAction;
import graphEditor.view.GraphPanel;

import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowClosing implements WindowListener {
    final private GraphPanel panel;

    public WindowClosing(GraphPanel panel) {
        this.panel = panel;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    /*
    this code is described in controller/menuBar/file/actions/ExitAction.java
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if (panel.getModel().isChanged()) {
            String[] options = {"Yes",
                    "No",
                    "Cancel"};
            int result = JOptionPane.showOptionDialog(panel, "Do you want to save this file before exiting?",
                    "Warning",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (result == JOptionPane.OK_OPTION) {
                AbstractAction saveFile = new SaveFileAction(null, panel);
                saveFile.actionPerformed(null);
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}