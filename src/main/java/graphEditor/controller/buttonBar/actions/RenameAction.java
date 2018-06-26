package graphEditor.controller.buttonBar.actions;

import graphEditor.view.GraphPanel;
import graphEditor.controller.undoredo.RenameEdit;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RenameAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public RenameAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
    }

    private void fixEnabled() {
        if (panel.getModel().getSelected() != null) { //the button is enabled only when a vertex is selected
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) JOptionPane.showInputDialog(
                panel,
                "Enter the new name:\n",
                "Rename",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                panel.getModel().getSelected().getName()); //the default input is the current name of the vertex
        if (s != null) {
            panel.getModel().getUndoManager().addEdit(new RenameEdit(panel.getModel().getSelected(),
                    panel.getModel().getSelected().getName(),
                    s)); //undo/redo system helper
            panel.getModel().getSelected().setName(s); //set the new name
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
