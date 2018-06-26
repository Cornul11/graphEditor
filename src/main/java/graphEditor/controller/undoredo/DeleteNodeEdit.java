package graphEditor.controller.undoredo;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.model.GraphEdge;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import java.util.List;

public class DeleteNodeEdit extends AbstractUndoableEdit {
    final private GraphVertex vertex;
    final private GraphModel model;
    final private List<GraphEdge> edges;

    public DeleteNodeEdit(GraphVertex vertex, GraphModel model, List<GraphEdge> edges) {
        this.vertex = vertex;
        this.model = model;
        this.edges = edges;
    }

    public void undo() throws CannotRedoException {
        model.addVertex(vertex);
        for (GraphEdge e : edges) {
            model.addEdge(e); //add the edge from the current edit
        }
    }

    public void redo() throws CannotUndoException {
        model.removeVertex(vertex);
        for (GraphEdge e : edges) {
            model.removeEdge(e); //remove the edge from the current edit
        }
    }

    public boolean canRedo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }

    public boolean canUndo() { //overriding this because when *this* exists, it can be redone or undone
        return true;
    }
}