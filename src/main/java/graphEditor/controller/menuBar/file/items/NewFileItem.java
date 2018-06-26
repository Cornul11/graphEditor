package graphEditor.controller.menuBar.file.items;

import graphEditor.controller.menuBar.file.actions.NewFileAction;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewFileItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
    }

    public NewFileItem(String name, GraphPanel panel) {
        super(new NewFileAction(name, panel));
        setItemProperties();
    }
}