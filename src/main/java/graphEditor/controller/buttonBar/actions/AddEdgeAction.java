package graphEditor.controller.buttonBar.actions;

import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class AddEdgeAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public AddEdgeAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
    }

    private void fixEnabled() {
        //if no vertex is selected, you can't add an edge
        setEnabled(panel.getModel().getSelected() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getModel().getVertices().size() > 1) { //if there's more than one vertices, then we can make an edge
            panel.getModel().setAdditionMode(true); //turn on the addition of edges (wait for a click on a second vertex)
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }

}
