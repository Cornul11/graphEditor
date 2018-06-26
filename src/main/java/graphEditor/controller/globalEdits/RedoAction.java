package graphEditor.controller.globalEdits;

import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class RedoAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public RedoAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
        fixEnabled();
    }

    public RedoAction(String name, Icon img, GraphPanel panel) {
        super(name, img);
        this.panel = panel;
        panel.getModel().addObserver(this);
        fixEnabled();
    }

    private void fixEnabled() {
        if (panel.getModel().getUndoManager().canRedo()) { //enable the button only if there is something to be redone
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.getModel().getUndoManager().redo(); //redo the undone action
        panel.getModel().update(); //notify all the observers
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}