package graphEditor.controller.buttonBar.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import java.util.ArrayList;

import graphEditor.controller.undoredo.DeleteNodeEdit;
import graphEditor.model.GraphVertex;
import graphEditor.model.GraphEdge;
import graphEditor.view.GraphPanel;


public class DeleteNodeAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public DeleteNodeAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
    }

    private void fixEnabled() {
        if (panel.getModel().getSelected() != null) { //the button is activated only when a vertex is selected
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphVertex v = panel.getModel().getSelected();
        if (v != null) { //if a vertex is selected
            ArrayList<GraphEdge> edges = new ArrayList<>();
            for (GraphEdge edge : panel.getModel().getEdges()) { //for all the edges in the model..
                for (GraphVertex ev : edge.getVertices()) { //..check if the selected vertex is connected through any of the edges
                    if (ev == v) {
                        edges.add(edge);
                    }
                }
            }
            panel.getModel().getUndoManager().addEdit(new DeleteNodeEdit(v, panel.getModel(), edges)); //also add the removed edges to the undoManager
            panel.getModel().removeVertex(v);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
