package graphEditor.view;

import graphEditor.controller.CopyPasteBuffer;
import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.JPanel;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {
    private GraphModel graphModel;
    final private CopyPasteBuffer buffer;
    private int addLineX, addLineY;

    private void setUpPanel() {
        setBackground(new Color(238, 238, 238)); //set the background color of the panel
        setVisible(true); //set it visible (show it)
        setOpaque(true); //make it non transparent
    }

    public GraphPanel(GraphModel model) {
        setUpPanel(); //set it up
        model.addObserver(this); //update it when the model changes
        this.buffer = new CopyPasteBuffer(); //for storing the copied vertex and retrieving it from there
        setModel(model);
    }

    public void updateObserver(GraphModel model) {
        model.addObserver(this);
    }

    public CopyPasteBuffer getCopyPasteBuffer() {
        return buffer;
    }

    public void setAddLineX(int addLineX) {
        this.addLineX = addLineX;
    }

    public void setAddLineY(int addLineY) {
        this.addLineY = addLineY;
    }

    public void setModel(GraphModel model) {
        this.graphModel = model;
        repaint(); //when the model has changed, repaint the panel
    }

    public GraphModel getModel() {
        return graphModel;
    }

    private void drawAdditionLine(Graphics g) {
        GraphVertex v = graphModel.getSelected();
        if (v != null && graphModel.isAdditionMode()) {
            int x1 = v.getX() + v.getWidth() / 2;
            int y1 = v.getY() + v.getHeight() / 2;
            g.drawLine(x1, y1, addLineX, addLineY); //draw the line from the vertex, to the cursor, every frame
            repaint(); //paint the changes
        }
    }

    private void drawEdges(Graphics g) {
        for (GraphEdge e : graphModel.getEdges()) {
            ArrayList<GraphVertex> localVertices = e.getVertices();
            GraphVertex vertex1 = localVertices.get(0);
            GraphVertex vertex2 = localVertices.get(1);
            int x1 = vertex1.getX() + vertex1.getWidth() / 2;
            int y1 = vertex1.getY() + vertex1.getHeight() / 2;
            int x2 = vertex2.getX() + vertex2.getWidth() / 2;
            int y2 = vertex2.getY() + vertex2.getHeight() / 2;
            g.drawLine(x1, y1, x2, y2); //draw the edges (lines between the centers of the vertices)
        }
    }

    private void drawVertices(Graphics g) {
        for (GraphVertex v : graphModel.getVertices()) {
            if (v.isSelected()) {
                g.setColor(new Color(128, 128, 255)); //set the color of the vertex purplish if it is selected
            } else {
                g.setColor(Color.WHITE); //default color
            }
            g.fillRect(v.getX(), v.getY(), v.getWidth(), v.getHeight()); //draw the color
            g.setColor(Color.BLACK);
            g.drawRect(v.getX(), v.getY(), v.getWidth(), v.getHeight()); //draw the border of the vertex
            int x = v.getX() + v.getWidth() / 2 - (8 + (v.getName().length() / 2 - 1) * 7); //centering the text based on char width
            int y = v.getY() + v.getHeight() / 2 + 5; //centering the text based on char height
            g.drawString(v.getName(), x, y); //put the string over the vertex
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, //turning on antialiasing
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawEdges(g);
        drawVertices(g);
        drawAdditionLine(g);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint(); //every time the model gets updated, repaint the panel
    }
}