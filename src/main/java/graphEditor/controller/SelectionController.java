package graphEditor.controller;

import graphEditor.controller.undoredo.EdgeEdit;
import graphEditor.controller.undoredo.MoveEdit;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphEdge;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SelectionController implements MouseListener, MouseMotionListener {
    final private GraphModel model;
    final private GraphPanel panel;
    private boolean pressed = false;
    private int oldX, oldY;
    private long start;

    public SelectionController(GraphModel model, GraphPanel panel) {
        this.model = model;
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        start = System.currentTimeMillis(); //helper for addingEdge
        if (model.isAdditionMode()) { //if we're in addition mode (add edge)
            for (GraphVertex v : model.getVertices()) {
                if (e.getX() > v.getX() &&
                        e.getX() < v.getX() + v.getWidth() &&
                        e.getY() > v.getY() &&
                        e.getY() < v.getY() + v.getHeight() && //if we've pressed on vertex
                        model.getSelected() != null && //and we already have a selected one
                        v != model.getSelected()) { //avoiding making an edge with the same selected vertex
                    GraphEdge newEdge = new GraphEdge(model, model.getSelected(), v); //create the edge itself
                    model.addEdge(newEdge); //add it to the model
                    model.setAdditionMode(false); //turn of addition mode
                    model.getUndoManager().addEdit(new EdgeEdit(newEdge, model)); //undo/redo system helper
                    break;
                }
            }
        } else { //it was a simple click for selecting a vertex
            for (GraphVertex v : model.getVertices()) {
                if (e.getX() > v.getX() &&
                        e.getX() < v.getX() + v.getWidth() &&
                        e.getY() > v.getY() &&
                        e.getY() < v.getY() + v.getHeight()) {
                    oldX = v.getX(); //needed for undo/redo system
                    oldY = v.getY();
                    v.setSelected(true); //make the clicked vertex selected
                } else {
                    v.setSelected(false); //make all the other unselected
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        start = System.currentTimeMillis(); //helper for addingEdge and filtering movements from simple clicks
        pressed = true;
        if (!model.isAdditionMode()) {
            for (GraphVertex v : model.getVertices()) {
                if (e.getX() > v.getX() &&
                        e.getX() < v.getX() + v.getWidth() &&
                        e.getY() > v.getY() &&
                        e.getY() < v.getY() + v.getHeight()) {
                    v.setSelected(true); //make the clicked vertex selected
                    oldX = v.getX(); //needed for undo/redo system
                    oldY = v.getY();
                } else {
                    v.setSelected(false); //make all the other vertices unselected
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        long end = System.currentTimeMillis() - start; //if the click was very short don't move the vertex
        if (end > 100) { //filter short clicks from long-clicks with movement
            for (GraphVertex v : model.getVertices()) {
                if (v.isSelected()) {
                    v.setX(e.getX() - v.getWidth() / 2); //set the new X
                    v.setY(e.getY() - v.getHeight() / 2); //set the new Y
                    v.setSelected(false); //after moving unselect the vertex
                    model.getUndoManager().addEdit(new MoveEdit(v, oldX, oldY, v.getX(), v.getY())); //undo/redo helper
                }
            }
        }
        pressed = false;
        model.update(); //notify observers (in our case undo/redo buttons are the ones that are important)
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressed) {
            for (GraphVertex v : model.getVertices()) {
                if (v.isSelected()) {
                    v.setX(e.getX() - v.getWidth() / 2); //on every drag change the position..
                    v.setY(e.getY() - v.getHeight() / 2); //..of the vertex to follow the cursor
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (model.isAdditionMode()) { //if in addition mode draw the line for edges while choosing it
            panel.setAddLineX(e.getX());  //it follows the cursor
            panel.setAddLineY(e.getY());
        }
    }

}
