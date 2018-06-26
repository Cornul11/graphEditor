package graphEditor.controller.menuBar.window.items;

import graphEditor.controller.menuBar.window.actions.FullScreenAction;
import graphEditor.view.GraphFrame;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FullScreenItem extends JMenuItem {
    private void setItemProperties() {
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.ALT_MASK));
    }

    public FullScreenItem(GraphFrame frame) {
        super(new FullScreenAction(frame));
        setItemProperties();
    }
}