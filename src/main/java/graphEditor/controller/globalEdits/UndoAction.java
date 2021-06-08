package graphEditor.controller.globalEdits;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import graphEditor.view.GraphPanel;

public class UndoAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public UndoAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this);
        fixEnabled();
    }

    public UndoAction(String name, Icon img, GraphPanel panel) {
        super(name, img);
        this.panel = panel;
        panel.getModel().addObserver(this);
        fixEnabled();
    }

    private void fixEnabled() {
        //enabled the button only if we have something to undo
        setEnabled(panel.getModel().getUndoManager().canUndo());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.getModel().getUndoManager().undo(); //undo an action that has been done
        panel.getModel().update(); //notify all the observers
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}