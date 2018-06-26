package graphEditor.controller.undoredo;

import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RenameEdit extends AbstractUndoableEdit {
    final private GraphVertex vertex;
    final private String oldName, newName;

    public RenameEdit(GraphVertex vertex, String oldName, String newName) {
        this.vertex = vertex;
        this.oldName = oldName;
        this.newName = newName;
    }

    public void undo() throws CannotUndoException {
        vertex.setName(oldName);
    }

    public void redo() throws CannotRedoException {
        vertex.setName(newName);
    }

    public boolean canRedo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }

    public boolean canUndo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }
}
