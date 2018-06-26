package graphEditor.model;

import java.util.Observable;

/*
the code needs no description
 */
public class GraphVertex extends Observable {
    private String name;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean selected;

    public GraphVertex(GraphModel model, int x, int y, int height, int width, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.selected = false;
        addObserver(model);
    }

    public GraphVertex(GraphModel model, GraphVertex vertex) {
        this.name = vertex.name;
        this.x = vertex.x;
        this.y = vertex.y;
        this.height = vertex.height;
        this.width = vertex.width;
        this.selected = false;
        addObserver(model);
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public void setX(int x) {
        this.x = x;
        setChanged();
        notifyObservers();
    }

    public void setY(int y) {
        this.y = y;
        setChanged();
        notifyObservers();
    }

    public void setHeight(int height) {
        this.height = height;
        setChanged();
        notifyObservers();
    }

    public void setWidth(int width) {
        this.width = width;
        setChanged();
        notifyObservers();
    }

    public void setSelected(boolean val) {
        this.selected = val;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isSelected() {
        return selected;
    }
}