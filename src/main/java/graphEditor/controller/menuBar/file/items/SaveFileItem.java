package graphEditor.controller.menuBar.file.items;

import graphEditor.controller.menuBar.file.actions.SaveFileAction;
import graphEditor.view.GraphPanel;

import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SaveFileItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        setEnabled(false);
        /*
        by the default, when the button is created, the model has not been changed, thus there's nothing to save, so
        we disable the button in the beginning
         */
    }

    public SaveFileItem(String name, GraphPanel panel) {
        super(new SaveFileAction(name, panel));
        setItemProperties();
    }
}