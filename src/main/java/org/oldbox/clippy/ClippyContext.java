package org.oldbox.clippy;

import org.oldbox.clippy.storage.GsonFileBackend;
import org.oldbox.clippy.storage.StorageBackend;

import javax.swing.*;
import java.nio.file.FileSystemException;

public class ClippyContext {


    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
        if(ctx == null) {
            try {
                StorageBackend storageBackend = new GsonFileBackend();
                ctx = new ClippyContext(storageBackend);
            } catch (FileSystemException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unable to create or open the save file");
                System.exit(1);
            }
        }

        return ctx;
    }

    private ClippyRepository repository;

    public ClippyContext(StorageBackend storageBackend) {
        this.repository = new ClippyRepository(storageBackend);
    }

    public ClippyRepository getRepository() {
        return repository;
    }

}
