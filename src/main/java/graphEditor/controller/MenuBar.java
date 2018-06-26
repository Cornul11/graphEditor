package graphEditor.controller;

import graphEditor.view.GraphFrame;
import graphEditor.controller.menuBar.FileMenu;
import graphEditor.controller.menuBar.EditMenu;
import graphEditor.controller.menuBar.WindowMenu;
import graphEditor.view.GraphPanel;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
    public MenuBar(GraphFrame frame, GraphPanel panel) {
        add(new FileMenu("File", panel, frame));
        add(new EditMenu("Edit", panel));
        add(new WindowMenu("Window", frame));
    }
}
