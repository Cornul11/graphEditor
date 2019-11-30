package graphEditor.controller.menuBar.file.items;

import graphEditor.controller.menuBar.file.actions.ExitAction;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExitItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
    }

    public ExitItem(String name, GraphPanel panel) {
        super(new ExitAction(name, panel));
        setItemProperties();
    }
}