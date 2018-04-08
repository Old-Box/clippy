package org.oldbox.clippy;

<<<<<<< HEAD
import java.awt.*;
=======
import org.oldbox.clippy.storage.StorageBackend;

>>>>>>> Moved from in-memory storage to StorageBackend
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClippyRepository {

    public static final int DATABASE_UPDATED = 1337;

    private ArrayList<ActionListener> actionListeners;

    private StorageBackend storageBackend;

    public ClippyRepository() throws FileSystemException {
        this.actionListeners = new ArrayList<>();
        this.storageBackend = new GsonFileBackend();
    }

    private Map<String, Category> getDatabase() throws FileSystemException {
        Map<String, Category> database;

        try {
            database = storageBackend.loadDatabase();
        } catch (FileNotFoundException e) {
            database = new HashMap<>();
        }

        return database;
    }

    public boolean categoryExists(String name) throws FileSystemException {
        return this.getDatabase().containsKey(name);
    }

    public void addCategory(String name, Color color) throws FileSystemException {
        Map<String, Category> database = this.getDatabase();

        if (this.categoryExists(name)) {
            throw new InvalidParameterException();
        }
        database.put(name, new Category(name, color));
        this.storageBackend.saveDatabase(database);
        this.triggerListeners(DATABASE_UPDATED, "Category added");
    }

    public Category getCategory(String name) throws FileSystemException {
        return this.getDatabase().get(name);
    }

    public void addEntryToCategory(String categoryName, NoteEntry entry) throws FileSystemException {
        Map<String, Category> database = this.getDatabase();
        database.get(categoryName).getEntries().add(entry);
        this.storageBackend.saveDatabase(database);
    }

    public Set<String> getCategories() throws FileSystemException {
        return this.getDatabase().keySet();
    }


    public void addActionListener(ActionListener listener) {
        this.actionListeners.add(listener);
    }

    private void triggerListeners(int code, String cmd) {
        for(ActionListener a: actionListeners) {
            a.actionPerformed(new ActionEvent(this, code, cmd));
        }
    }

}
