package graphEditor.controller.menuBar.file.items;

import graphEditor.controller.menuBar.file.actions.OpenFileAction;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class OpenFileItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
    }

    public OpenFileItem(String name, GraphPanel panel, GraphFrame frame) {
        super(new OpenFileAction(name, panel, frame));
        setItemProperties();
    }
}