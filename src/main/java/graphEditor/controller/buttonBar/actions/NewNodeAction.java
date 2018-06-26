package graphEditor.controller.buttonBar.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import graphEditor.view.GraphPanel;
import graphEditor.model.GraphVertex;
import graphEditor.controller.undoredo.NewNodeEdit;

public class NewNodeAction extends AbstractAction {
    final private GraphPanel panel;

    public NewNodeAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphVertex v = new GraphVertex(panel.getModel(), 100, 100, 55, 120, "New Node");
        panel.getModel().addVertex(v);
        panel.getModel().getUndoManager().addEdit(new NewNodeEdit(v, panel.getModel())); //undo/redo system helper
    }
}
