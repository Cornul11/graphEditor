package graphEditor.controller.undoredo;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class NewNodeEdit extends AbstractUndoableEdit {
    final private GraphVertex vertex;
    final private GraphModel model;

    public NewNodeEdit(GraphVertex vertex, GraphModel model) {
        this.vertex = vertex;
        this.model = model;
    }

    public void undo() throws CannotUndoException {
        model.removeVertex(vertex);
    }

    public void redo() throws CannotRedoException {
        model.addVertex(vertex);
    }

    public boolean canRedo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }

    public boolean canUndo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }
}
