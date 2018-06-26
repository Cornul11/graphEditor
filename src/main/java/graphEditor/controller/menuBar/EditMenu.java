package graphEditor.controller.menuBar;

import graphEditor.controller.menuBar.edit.RedoItem;
import graphEditor.controller.menuBar.edit.UndoItem;
import graphEditor.controller.menuBar.edit.CopyItem;
import graphEditor.controller.menuBar.edit.PasteItem;
import graphEditor.view.GraphPanel;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EditMenu extends JMenu {

    private void setUpMenu(GraphPanel panel) {
        getAccessibleContext().setAccessibleDescription("The main edit menuBar");

        //create the items
        JMenuItem undoItem = new UndoItem(panel);
        JMenuItem redoItem = new RedoItem(panel);
        JMenuItem copyItem = new CopyItem(panel);
        JMenuItem pasteItem = new PasteItem(panel);

        //add them to the menu
        add(undoItem);
        add(redoItem);
        addSeparator(); //separator for improving the UX
        add(copyItem);
        add(pasteItem);
    }

    public EditMenu(String name, GraphPanel panel) {
        super(name);
        setUpMenu(panel);
    }
}