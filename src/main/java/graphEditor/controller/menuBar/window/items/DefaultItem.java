package graphEditor.controller.menuBar.window.items;

import graphEditor.controller.menuBar.window.actions.DefaultAction;
import graphEditor.view.GraphFrame;

import javax.swing.JMenuItem;

public class DefaultItem extends JMenuItem {
    public DefaultItem(GraphFrame frame) {
        super(new DefaultAction(frame));
    }
}