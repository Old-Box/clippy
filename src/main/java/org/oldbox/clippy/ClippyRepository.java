package org.oldbox.clippy;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClippyRepository {

    private Map<String, Category> database;

    public ClippyRepository() {
        this.database = new HashMap<>();
    }

    public boolean categoryExists(String name) {
        return this.database.containsKey(name);
    }

    public void addCategory(String name, String color) {
        if (this.categoryExists(name)) {
            throw new InvalidParameterException();
        }
        this.database.put(name, new Category(name, color));
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
}
