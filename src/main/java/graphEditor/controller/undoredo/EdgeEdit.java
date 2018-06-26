package graphEditor.controller.undoredo;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphEdge;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EdgeEdit extends AbstractUndoableEdit {
    final private GraphEdge edge;
    final private GraphModel model;

    public EdgeEdit(GraphEdge edge, GraphModel model) {
        this.edge = edge;
        this.model = model;
    }

    public void undo() throws CannotUndoException {
        model.removeEdge(edge);
    }

    public void redo() throws CannotRedoException {
        model.addEdge(edge);
    }

    public boolean canUndo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }

    public boolean canRedo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }
}