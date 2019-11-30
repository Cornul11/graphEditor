package graphEditor.controller.menuBar.edit;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import graphEditor.controller.globalEdits.CopyAction;
import graphEditor.view.GraphPanel;

public class CopyItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK)); //the classic Ctrl+C
    }

    public CopyItem(GraphPanel g) {
        super(new CopyAction("Copy", g)); //set the action attached to the item
        setItemProperties();
    }
}