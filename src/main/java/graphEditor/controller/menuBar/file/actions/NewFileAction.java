package graphEditor.controller.menuBar.file.actions;

import graphEditor.controller.SelectionController;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class NewFileAction extends AbstractAction {
    final private GraphPanel panel;

    public NewFileAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        setEnabled(true);
    }

    private void showNewFileDialog() {
        String s = (String) JOptionPane.showInputDialog(
                panel,
                "Enter the name of the new file:\n",
                "New file",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (s != null) {
            GraphModel newModel = new GraphModel(s, true);
            panel.setModel(newModel); //update the model
            panel.updateObserver(newModel);
            SelectionController sc = new SelectionController(panel.getModel(), panel); //reset the mouse controls for the new model
            panel.addMouseMotionListener(sc);
            panel.addMouseListener(sc);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getModel().isChanged()) {
            String[] options = {"Yes",
                    "No",
                    "Cancel"};
            int result = JOptionPane.showOptionDialog(panel, "Do you want to save this file before creating another one?",
                    "Warning",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]);
            if (result == JOptionPane.OK_OPTION) { //save the file and only after that create another one
                AbstractAction saveFile = new SaveFileAction(null, panel);
                saveFile.actionPerformed(null);
                showNewFileDialog();
            } else if (result == JOptionPane.NO_OPTION) { //create a new file without saving the current file
                showNewFileDialog();
            }
        } else { //create a new file instantly, because there's nothing to save
            showNewFileDialog();
        }
    }
}