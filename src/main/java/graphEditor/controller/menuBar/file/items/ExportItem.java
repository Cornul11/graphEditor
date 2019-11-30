package graphEditor.controller.menuBar.file.items;

import graphEditor.controller.menuBar.file.actions.ExportAction;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExportItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
    }

    public ExportItem(String name, GraphPanel panel) {
        super(new ExportAction(name, panel));
        setItemProperties();
    }
}