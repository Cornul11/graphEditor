package graphEditor.controller;

import graphEditor.model.GraphModel;

import java.io.File;

public class GraphLoaderController {
    final private String[] inputArgs;

    public GraphLoaderController(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }

    public GraphModel load() {
        GraphModel graphModel = null;
        if (inputArgs.length != 0) {
            File f = new File(inputArgs[0]);
            if (f.exists()) {
                if (f.length() != 0) {
                    graphModel = new GraphModel(inputArgs[0]); //load the model if no problems encountered
                } else {
                    System.err.println(inputArgs[0] + ": Is empty");
                    System.exit(0);
                }
            } else if (f.isDirectory()) {
                System.err.println(inputArgs[0] + ": Is a directory");
                System.exit(0);
            } else {
                System.err.println(inputArgs[0] + ": No such file or directory");
                System.exit(0);
            }
        } else { //if no argument is provided, then load a blank model
            graphModel = new GraphModel(null, true);
        }
        return graphModel;
    }
}
