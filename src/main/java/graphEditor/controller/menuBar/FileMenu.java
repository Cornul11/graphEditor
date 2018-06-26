package graphEditor.controller.menuBar;

import graphEditor.view.GraphFrame;
import graphEditor.controller.menuBar.file.items.NewFileItem;
import graphEditor.controller.menuBar.file.items.OpenFileItem;
import graphEditor.controller.menuBar.file.items.SaveFileItem;
import graphEditor.controller.menuBar.file.items.ExportItem;
import graphEditor.controller.menuBar.file.items.ExitItem;
import graphEditor.view.GraphPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
    final private GraphPanel panel;
    final private GraphFrame frame;

    private void setUpMenu() {
        getAccessibleContext().setAccessibleDescription("The main file menuBar");

        //create the items for the menu with the needed arguments
        JMenuItem newItem = new NewFileItem("New file", panel);
        JMenuItem openItem = new OpenFileItem("Open file", panel, frame);
        JMenuItem saveItem = new SaveFileItem("Save file", panel);
        JMenuItem exportItem = new ExportItem("Export to GraphViz", panel);
        JMenuItem exitItem = new ExitItem("Exit", panel);

        add(newItem);
        add(openItem);
        add(saveItem);
        add(exportItem);
        addSeparator(); //separator for improving the UX/UI
        add(exitItem);
    }

    public FileMenu(String name, GraphPanel panel, GraphFrame frame) {
        super(name);
        this.panel = panel;
        this.frame = frame;
        setUpMenu();
    }
}