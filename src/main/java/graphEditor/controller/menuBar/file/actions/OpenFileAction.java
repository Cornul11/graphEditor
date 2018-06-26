package graphEditor.controller.menuBar.file.actions;

import graphEditor.controller.SelectionController;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphPanel;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class OpenFileAction extends AbstractAction {
    final private GraphPanel panel;
    final private GraphFrame frame;

    public OpenFileAction(String name, GraphPanel panel, GraphFrame frame) {
        super(name);
        this.panel = panel;
        this.frame = frame;
        setEnabled(true);
    }

    private void showOpeningDialog() {
        String s = (String) JOptionPane.showInputDialog(
                panel,
                "Enter the name of the file to open:\n",
                "Open file",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (s != null) {
            if (s.contains(".gv")) { //if an GraphViz file is opened (imported) then choose a different way of loading
                panel.getModel().loadGraphViz(s);
            } else {
                panel.getModel().load(s);
            }
            frame.updateTitle("Graph editor" + " [" + s + "]");
            SelectionController sc = new SelectionController(panel.getModel(), panel);
            panel.addMouseMotionListener(sc);
            panel.addMouseListener(sc);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getModel().isChanged()) {
            String[] options = {"Yes",
                    "No",
                    "Cancel"};
            int result = JOptionPane.showOptionDialog(panel, "Do you want to save this file before opening another one?",
                    "Warning",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]); //by default we press cancel (unintentional press on open file)
            if (result == JOptionPane.OK_OPTION) { //save the file if yes, then open another file
                AbstractAction saveFile = new SaveFileAction(null, panel);
                saveFile.actionPerformed(null);
                showOpeningDialog();
            } else if (result == JOptionPane.NO_OPTION) { //go straight to opening the file, without saving
                showOpeningDialog();
            }
        } else { //no need to save, open the file
            showOpeningDialog();
        }
    }
}