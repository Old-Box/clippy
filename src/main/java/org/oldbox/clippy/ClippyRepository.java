package org.oldbox.clippy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClippyRepository {

    public static final int DATABASE_UPDATED = 1337;

    private ArrayList<ActionListener> actionListeners;
    private Map<String, Category> database;

    public ClippyRepository() {
        this.database = new HashMap<>();
        this.actionListeners = new ArrayList<>();
    }

    public boolean categoryExists(String name) {
        return this.database.containsKey(name);
    }

    public void addCategory(String name, String color) {
        if (this.categoryExists(name)) {
            throw new InvalidParameterException();
        }
        this.database.put(name, new Category(name, color));
        this.triggerListeners(DATABASE_UPDATED, "Category added");
    }

    public Category getCategory(String name) {
        return this.database.get(name);
    }

    public void addEntryToCategory(String categoryName, NoteEntry entry) {
        this.getCategory(categoryName).getEntries().add(entry);
    }

    public Set<String> getCategories() {
        return this.database.keySet();
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
