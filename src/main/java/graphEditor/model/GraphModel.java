package graphEditor.model;

import javax.swing.undo.UndoManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class GraphModel extends Observable implements Observer {
    private ArrayList<GraphVertex> vertices;
    private ArrayList<GraphEdge> edges;
    private String filename;
    private boolean additionMode, changed;
    final private UndoManager undomanager = new UndoManager();

    public GraphModel(String filename, boolean var) { //this is called when creating a blank model is needed
        this.filename = filename;
        initializeModel();
        setChanged();
        notifyObservers();
    }

    public GraphModel(String filename) {
        this.filename = filename;
        if (filename.contains(".gv")) {
            loadGraphViz(filename); //load graphViz file if we're provided with one
        } else {
            load(filename); //load simple graph file
        }
    }

    public UndoManager getUndoManager() {
        return this.undomanager;
    }

    public GraphVertex getSelected() {
        for (GraphVertex v : vertices) {
            if (v.isSelected()) {
                return v; //return the selected vertex if one exists
            }
        }
        return null;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public ArrayList<GraphVertex> getVertices() {
        return vertices;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    public void addVertex(GraphVertex v) {
        vertices.add(v);
        setChanged();
        notifyObservers();
    }

    public void addEdge(GraphEdge e) {
        edges.add(e);
        setChanged();
        notifyObservers();
    }

    public void removeVertex(GraphVertex v) {
        vertices.remove(v); //remove the vertex
        Iterator<GraphEdge> edgeIterator = edges.iterator();
        while (edgeIterator.hasNext()) {
            GraphEdge edge = edgeIterator.next();
            if (edge.getVertices().contains(v)) {
                edgeIterator.remove(); //remove all the edges that were connected to the vertex
            }
        }
        setChanged();
        notifyObservers();
    }

    public void removeEdge(GraphEdge e) {
        edges.remove(e);
        setChanged();
        notifyObservers();
    }

    public boolean isAdditionMode() {
        return additionMode;
    }

    public void setAdditionMode(boolean additionMode) {
        this.additionMode = additionMode;
        setChanged();
        notifyObservers();
    }

    private void initializeModel() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        additionMode = false;
        changed = false;
    }

    /*
    this saves the file in the same format as the import file
    */
    public void save(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.format("%d %d", vertices.size(), edges.size())); //first line contains the number of vertices and edges

        for (GraphVertex v : vertices) { //add lines about the vertices
            lines.add(String.format("%d %d %d %s %s", v.getX(), v.getY(), v.getHeight(), v.getWidth(), v.getName()));
        }

        for (GraphEdge e : edges) { //add lines about the edges (the indexes of the vertices that are connected through the edge)
            ArrayList<GraphVertex> edgeVertices = e.getVertices();
            lines.add(String.format("%d %d", vertices.indexOf(edgeVertices.get(0)), vertices.indexOf(edgeVertices.get(1))));
        }

        Path file = Paths.get(filename);
        try {
            Files.write(file, lines, Charset.forName("UTF-8")); //save the file
        } catch (IOException e) {
            System.err.println("An unexpected error occurred!");
            e.printStackTrace();
        }
        changed = false;
    }

    public void load(String filename) {
        try { //fail-safe
            FileReader reader = new FileReader(filename);
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine(); //read the first line
            initializeModel(); //initialize the model
            String[] tokens = line.split(" "); //split the amount of vertices and edges from the first line
            int numVertices = Integer.parseInt(tokens[0]);
            int numEdges = Integer.parseInt(tokens[1]);

            for (int i = 0; i < numVertices; i++) {
                String nameString = "";
                String localLine = buffReader.readLine();
                String[] localTokens = localLine.split(" "); //split the data about the vertex
                GraphVertex vertexToAdd = new GraphVertex(this, Integer.parseInt(localTokens[0]), //first is the X coordinate
                        Integer.parseInt(localTokens[1]), //second is the Y coordinate
                        Integer.parseInt(localTokens[2]), //third is the height
                        Integer.parseInt(localTokens[3]), //fourth is the width
                        " ");
                for (int tokenIter = 4; tokenIter < localTokens.length; tokenIter++) {
                    nameString = nameString + localTokens[tokenIter] + " ";
                }
                vertexToAdd.setName(nameString);
                addVertex(vertexToAdd); //the name of the vertex

            }

            for (int i = 0; i < numEdges; i++) {
                String localLine = buffReader.readLine();
                String[] localTokens = localLine.split(" "); //split the data about the edge for further processing
                GraphVertex vertex1 = vertices.get(Integer.parseInt(localTokens[0])); //assign the vertex with the index given in the file
                GraphVertex vertex2 = vertices.get(Integer.parseInt(localTokens[1])); //assign the vertex with the index given in the file
                addEdge(new GraphEdge(this, vertex1, vertex2)); //create the edge
            }

            setFilename(filename); //store the filename
        } catch (IOException e) {
            System.err.println("An unexpected error occurred!");
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    /*
    knowing the format of the simple GraphViz examples we come to the following implementation
     */
    public void loadGraphViz(String filename) {
        try {
            int lineCount = 0;
            try (FileReader input = new FileReader(filename);
                 LineNumberReader count = new LineNumberReader(input)) {
                while (count.readLine() != null) {
                    lineCount = count.getLineNumber(); //the line count is needed for knowing what line are we reading
                }
            }
            FileReader reader = new FileReader(filename);
            BufferedReader buffReader = new BufferedReader(reader);
            initializeModel();
            String localLine;
            buffReader.readLine();
            for (int i = 0; i < lineCount - 2; i++) { //ignore the last line (thus the (-2) in the condition)
                localLine = buffReader.readLine(); //ignore the first line
                localLine = localLine.replace(";", ""); //remove all the semicolons
                localLine = localLine.replaceAll("\\s+", ""); //remove all the spaces
                String[] localTokens = localLine.split("--"); //split the string in tokens by "--"

                final int pos1 = lineCount * 10; //avoiding overlapping vertices
                GraphVertex v1 = new GraphVertex(this, pos1, pos1, 50, 50, localTokens[0]);
                final int pos2 = lineCount * (10 + 10); //avoiding overlapping vertices
                GraphVertex v2 = new GraphVertex(this, pos2, pos2, 100, 100, localTokens[1]);
                if (!containsVertex(localTokens[0])) { //if the current name was already encountered, don't add it
                    addVertex(v1);
                } else { //else it's new, so add it
                    v1 = returnVertex(localTokens[0]);
                }
                //same for the second vertex
                if (!containsVertex(localTokens[1])) {
                    addVertex(v2);
                } else {
                    v2 = returnVertex(localTokens[1]);
                }
                addEdge(new GraphEdge(this, v1, v2)); //create the edge between these two
            }
            setFilename(filename);
        } catch (IOException e) {
            System.err.println("An unexpected error occurred!");
            e.printStackTrace();
        }
        setChanged();
        notifyObservers();
    }

    //(used for the GraphViz loading)
    private boolean containsVertex(String s) {
        for (GraphVertex v : vertices) {
            if (v.getName().equals(s)) {
                return true; //return true if the model contains a vertex with the same name (used for the GraphViz loading)
            }
        }
        return false;
    }

    private GraphVertex returnVertex(String s) {
        for (GraphVertex v : vertices) {
            if (v.getName().equals(s)) {
                return v; //returns the vertex that has the name s
            }
        }
        return null;
    }

    public boolean isChanged() {
        return changed;
    }

    @Override
    public void update(Observable observed, Object message) {
        changed = true;
        setChanged();
        notifyObservers();
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}
