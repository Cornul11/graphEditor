package graphEditor.controller.menuBar.file.actions;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;

public class ExportAction extends AbstractAction {
    final private GraphPanel panel;

    public ExportAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String) JOptionPane.showInputDialog(
                panel,
                "Enter the name of the exported file:\n",
                "Export file",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (s != null && s.length() > 0) {
            ArrayList<String> lines = new ArrayList<>();
            lines.add("graph " + s.replace(".gv", "") + " {");
            ArrayList<GraphVertex> vertices = panel.getModel().getVertices();
            for (GraphVertex vertex : vertices) {
                lines.add(String.format("\t\"%s\";", vertex.getName()));
            }
            ArrayList<GraphEdge> edges = panel.getModel().getEdges();
            for (GraphEdge edgeIter : edges) {
                ArrayList<GraphVertex> edgeVertices = edgeIter.getVertices();
                lines.add(String.format("\t\"%s\" -- \"%s\";", edgeVertices.get(0).getName(), edgeVertices.get(1).getName()));
            }
            lines.add("}");
            Path file = Paths.get(s);
            try {
                Files.write(file, lines, Charset.forName("UTF-8")); //write to file the content
                JOptionPane.showMessageDialog(null,
                        "The graph was exported!",
                        "Successfully exported",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ioEx) {
                System.err.println("An unexpected error occurred!");
                ioEx.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "You need to input a valid filename!",
                    "Failure",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}