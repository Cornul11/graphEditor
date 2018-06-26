package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

public class CopyPasteBuffer {
    private GraphVertex buffer;

    public CopyPasteBuffer() {
        //blank constructor
    }

    public void copy(GraphModel model, GraphVertex vertex) {
        buffer = new GraphVertex(model, vertex); //set the copied vertex into the buffer as a copy
    }

    public GraphVertex paste() {
        if (buffer != null) {
            return buffer; //if the buffer has a vertex in it, return it for pasting it
        }
        return null;
    }
}