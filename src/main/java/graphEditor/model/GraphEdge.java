package graphEditor.model;

import java.util.ArrayList;
import java.util.Observable;

/*
the code needs no description
 */
public class GraphEdge extends Observable {
    final private GraphVertex vertex1, vertex2;

    public GraphEdge(GraphModel model, GraphVertex vertex1, GraphVertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.addObserver(model);
    }

    public ArrayList<GraphVertex> getVertices() {
        ArrayList<GraphVertex> vertices = new ArrayList<>(); //create an array with the two vertices that are to be returned
        vertices.add(this.vertex1); //add the first vertex to the array
        vertices.add(this.vertex2); //add the second vertex to the array
        return vertices; //return the array
    }
}