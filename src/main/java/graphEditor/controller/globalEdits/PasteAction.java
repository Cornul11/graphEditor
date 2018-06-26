package graphEditor.controller.globalEdits;

import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;
import graphEditor.controller.undoredo.NewNodeEdit;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class PasteAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public PasteAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        setEnabled(false);
        panel.getModel().addObserver(this);
    }

    public PasteAction(String name, Icon img, GraphPanel panel) {
        super(name, img);
        this.panel = panel;
        setEnabled(false);
        panel.getModel().addObserver(this);
    }

    private void fixEnabled() {
        if (panel.getCopyPasteBuffer().paste() != null) { //enable the button only in the case there is a vertex in the buffer
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphVertex toBePasted = panel.getCopyPasteBuffer().paste();
        if (toBePasted != null) {
            toBePasted.setX(toBePasted.getX() + 50); //to avoid overlapping the vertices
            toBePasted.setY(toBePasted.getY() + 50);
            panel.getModel().addVertex(toBePasted); //add the vertex from the buffer to the model
            panel.getModel().getUndoManager().addEdit(new NewNodeEdit(toBePasted, panel.getModel())); //undo/redo system helper
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}
