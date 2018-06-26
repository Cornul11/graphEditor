package graphEditor.controller.menuBar;

import graphEditor.controller.menuBar.window.items.DefaultItem;
import graphEditor.controller.menuBar.window.items.FullScreenItem;
import graphEditor.controller.menuBar.window.items.SetDefaultItem;
import graphEditor.view.GraphFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class WindowMenu extends JMenu {
    final private GraphFrame frame;

    private void setUpMenu() {
        getAccessibleContext().setAccessibleDescription("The main window menuBar");

        //create the items for the menu with the needed arguments
        JMenuItem defaultItem = new DefaultItem(frame);
        JMenuItem fullScreenItem = new FullScreenItem(frame);
        JMenuItem setDefaultItem = new SetDefaultItem(frame);

        add(defaultItem);
        add(setDefaultItem);
        add(fullScreenItem);
    }

    public WindowMenu(String name, GraphFrame frame) {
        super(name);
        this.frame = frame;
        setUpMenu();
    }
}