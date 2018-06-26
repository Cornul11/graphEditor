package graphEditor.controller.menuBar.file.actions;

import graphEditor.view.GraphFrame;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class SaveFileAction extends AbstractAction implements Observer {
    final private GraphPanel panel;

    public SaveFileAction(String name, GraphPanel panel) {
        super(name);
        this.panel = panel;
        panel.getModel().addObserver(this); //we need this because we need to check whether the model has changed
        fixEnabled();
    }

    private void fixEnabled() {
        if (panel.getModel().isChanged()) { //if the model has changed, then it would make sense to save the file, so...
            setEnabled(true); //...we activate the item in the menu
        } else {
            setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getModel().getFilename() != null) {
            panel.getModel().save(panel.getModel().getFilename());
            JOptionPane.showMessageDialog(null,
                    "The file was saved!",
                    "Successfully saved",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            String s = (String) JOptionPane.showInputDialog(
                    panel,
                    "Set the file name:\n",
                    "File name not set",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "new.txt");
            if (s != null) {
                panel.getModel().setFilename(s); //update the filename in the model
                GraphFrame frame = (GraphFrame) SwingUtilities.getWindowAncestor(panel); //get the frame of the panel
                frame.updateTitle(s); //update the title with the new filename
                panel.getModel().save(panel.getModel().getFilename()); //save into the new file
                JOptionPane.showMessageDialog(null,
                        "The file was saved!",
                        "Successfully saved",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        fixEnabled();
    }
}