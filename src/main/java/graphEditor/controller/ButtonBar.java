package graphEditor.controller;

import graphEditor.controller.buttonBar.buttons.NewNodeButton;
import graphEditor.controller.buttonBar.buttons.DeleteNodeButton;
import graphEditor.controller.buttonBar.buttons.AddEdgeButton;
import graphEditor.controller.buttonBar.buttons.DeleteEdgeButton;
import graphEditor.controller.buttonBar.buttons.RenameButton;
import graphEditor.controller.buttonBar.buttons.UndoButton;
import graphEditor.controller.buttonBar.buttons.RedoButton;
import graphEditor.controller.buttonBar.buttons.CopyButton;
import graphEditor.controller.buttonBar.buttons.PasteButton;
import graphEditor.view.GraphPanel;

import javax.swing.JToolBar;

public class ButtonBar extends JToolBar {
    public ButtonBar(GraphPanel panel) {
        //add the buttons to the bar
        add(new NewNodeButton("new node", panel));
        add(new DeleteNodeButton("delete node", panel));
        add(new AddEdgeButton("add edge", panel));
        add(new DeleteEdgeButton("delete edge", panel));
        add(new RenameButton("rename", panel));
        add(new UndoButton(panel));
        add(new RedoButton(panel));
        add(new CopyButton(panel));
        add(new PasteButton(panel));
    }
}