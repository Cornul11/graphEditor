package graphEditor.controller.undoredo;

import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class MoveEdit extends AbstractUndoableEdit {
    final private GraphVertex vertex;
    final private int oldX, oldY, newX, newY;

    public MoveEdit(GraphVertex vertex, int oldX, int oldY, int newX, int newY) {
        this.vertex = vertex;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    public void undo() throws CannotUndoException {
        vertex.setX(oldX);
        vertex.setY(oldY);
    }

    public void redo() throws CannotRedoException {
        vertex.setX(newX);
        vertex.setY(newY);
    }

    public boolean canRedo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }

    public boolean canUndo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }
}
