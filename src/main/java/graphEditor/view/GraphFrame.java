package graphEditor.view;

import javax.swing.JFrame;
import java.awt.Dimension;

public class GraphFrame extends JFrame {
    private Dimension defaultDimension = new Dimension(800, 600);
    private int defaultPosX, defaultPosY;

    public GraphFrame(String title) {
        this.setTitle(title);
    }

    public void updateTitle(String s) {
        this.setTitle(s); //update the title with the new filename
    }

    public int getDefaultPosX() {
        return defaultPosX;
    }

    public void setDefaultPosX(int defaultPosX) {
        this.defaultPosX = defaultPosX;
    }

    public int getDefaultPosY() {
        return defaultPosY;
    }

    public void setDefaultPosY(int defaultPosY) {
        this.defaultPosY = defaultPosY;
    }

    public Dimension getDefaultDimension() {
        return defaultDimension;
    }

    public void setDefaultDimension(Dimension dimension) {
        this.defaultDimension = dimension;
    }
}