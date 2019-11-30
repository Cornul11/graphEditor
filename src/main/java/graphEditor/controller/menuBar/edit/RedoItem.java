package graphEditor.controller.menuBar.edit;

import graphEditor.controller.globalEdits.RedoAction;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
    }

    public RedoItem(GraphPanel panel) {
        super(new RedoAction("Redo", panel)); //set the action attached to the item
        setItemProperties();
    }
}