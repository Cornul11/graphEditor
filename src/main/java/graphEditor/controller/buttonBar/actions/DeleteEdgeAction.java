package graphEditor.controller.buttonBar.actions;

import graphEditor.model.GraphEdge;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DeleteEdgeAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public DeleteEdgeAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
        fixEnabled();
    }

    private void fixEnabled() {
        if (panel.getModel().getEdges().isEmpty()) { //the button is enabled only when there are edges in the model
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final ArrayList<GraphEdge> edges = panel.getModel().getEdges();
        String selectionValues[] = new String[edges.size()]; //initialize the selection values (the amount of edges)
        int i = 0;
        for (GraphEdge edge : edges) {
            selectionValues[i++] = String.format("%d: %s <-> %s",
                    edges.indexOf(edge),
                    edge.getVertices().get(0).getName(),
                    edge.getVertices().get(1).getName());
        }
        String result = (String) JOptionPane.showInputDialog(panel,
                "Choose the edge to delete:",
                "Delete Edge",
                JOptionPane.PLAIN_MESSAGE,
                UIManager.getIcon("OptionPane.errorIcon"),
                selectionValues,
                selectionValues[0]);
        if (result != null) {
            Integer intResult = Integer.parseInt(result.split(":")[0]);
            System.out.println(intResult);
            panel.getModel().removeEdge(edges.get(intResult)); //delete the chosen edge
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}