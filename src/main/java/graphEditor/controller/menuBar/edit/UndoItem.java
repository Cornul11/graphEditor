package graphEditor.controller.menuBar.edit;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import graphEditor.controller.globalEdits.UndoAction;
import graphEditor.view.GraphPanel;

public class UndoItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
    }

    public UndoItem(GraphPanel g) {
        super(new UndoAction("Undo", g)); //set the action attached to the item
        setItemProperties();
    }
}