package graphEditor;

import graphEditor.controller.ButtonBar;
import graphEditor.controller.MenuBar;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;
import graphEditor.controller.SelectionController;
import graphEditor.controller.WindowClosing;
import graphEditor.controller.GraphLoaderController;
import graphEditor.view.GraphPanel;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;

class GraphEditor {

    public static void main(String[] args) {
        GraphEditor mainThread = new GraphEditor();
        mainThread.run(args);
    }

    private void run(String[] args) {
        FlatDarculaLaf.setup();
        GraphLoaderController graphLoader = new GraphLoaderController(args); //new loader
        GraphModel graphModel = graphLoader.load(); //the graphModel is loaded using the loader

        GraphFrame mainFrame = new GraphFrame("Graph editor" + (args.length == 0 ? " [untitled project]" : (" [" + args[0] + "]"))); //set the default window title
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //the default close operation is managed by the WindowClosing controller
        mainFrame.setSize(new Dimension(800, 600)); //set the initial dimensions
        GraphPanel mainPanel = new GraphPanel(graphModel); //attach the model to the panel
        mainFrame.setJMenuBar(new MenuBar(mainFrame, mainPanel)); //create the menubar with the items and attach it to the frame
        mainFrame.addWindowListener(new WindowClosing(mainPanel)); //attach the WindowClosing controller
        SelectionController sc = new SelectionController(graphModel, mainPanel); //create the mouse system
        mainPanel.addMouseListener(sc); //attach the mouse listener (clicks, presses, releases)
        mainPanel.addMouseMotionListener(sc); //attach the mouse movement listeners (movements, drags)
        mainFrame.getContentPane().add(new ButtonBar(mainPanel), BorderLayout.NORTH); //create a button bar and attach it to the frame
        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER); //add the main panel to the frame
        mainFrame.setPreferredSize(new Dimension(800, 600)); //set the preferred size
        mainFrame.pack(); //compress everything to fit
        mainFrame.setLocationRelativeTo(null); //set on the center of the screen
        mainFrame.setVisible(true); //in other words, show it (draw it)
    }
}