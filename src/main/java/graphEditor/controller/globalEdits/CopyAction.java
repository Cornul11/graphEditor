package graphEditor.controller.globalEdits;

import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction {
    final private GraphPanel panel;

    public CopyAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        setEnabled(true);
    }

    public CopyAction(String name, Icon img, GraphPanel panel) {
        super(name, img);
        this.panel = panel;
        setEnabled(true); //the button is always turned on because we can always copy a new vertex
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphVertex selected = panel.getModel().getSelected();
        if (selected != null) {
            panel.getCopyPasteBuffer().copy(panel.getModel(), panel.getModel().getSelected()); //set the copied vertex
        }
    }
}