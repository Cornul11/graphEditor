package graphEditor.controller.menuBar.edit;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import graphEditor.controller.globalEdits.PasteAction;
import graphEditor.view.GraphPanel;

public class PasteItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK)); //the classic Ctrl+V
    }

    public PasteItem(GraphPanel g) {
        super(new PasteAction("Paste", g)); //set the action attached to the item
        setItemProperties();
    }
}