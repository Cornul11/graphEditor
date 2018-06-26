package graphEditor.controller.menuBar.window.items;

import graphEditor.controller.menuBar.window.actions.SetDefaultAction;
import graphEditor.view.GraphFrame;

import javax.swing.JMenuItem;

public class SetDefaultItem extends JMenuItem {
    public SetDefaultItem(GraphFrame frame) {
        super(new SetDefaultAction(frame));
    }
}