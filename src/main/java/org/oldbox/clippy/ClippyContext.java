package org.oldbox.clippy;

import javax.swing.*;
import java.nio.file.FileSystemException;

public class ClippyContext {


    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
        if(ctx == null) {
            try {
                ctx = new ClippyContext();
            } catch (FileSystemException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unable to create or open the save file");
                System.exit(1);
            }
        }

        return ctx;
    }

    private ClippyRepository repository;

    public ClippyContext() throws FileSystemException {
        this.repository = new ClippyRepository();
    }

    public ClippyRepository getRepository() {
        return repository;
    }

}
