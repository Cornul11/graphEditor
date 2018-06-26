package graphEditor.controller.menuBar.file.actions;

import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class ExitAction extends AbstractAction {
    final private GraphPanel panel;

    public ExitAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            if (result == JOptionPane.OK_OPTION) { //save before exiting
                AbstractAction saveFile = new SaveFileAction(null, panel);
                saveFile.actionPerformed(null);
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) { //exit without saving
                System.exit(0);
            }
        } else { //nothing to save, so exit straight away
            System.exit(0);
        }
    }
}