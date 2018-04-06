package org.oldbox.clippy;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClippyContext {

    private Map<String, ArrayList<NoteEntry>> database;

    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
       if(ctx == null) {
           ctx = new ClippyContext();
       }

       return ctx;
    }


    public ClippyContext() {
        this.database = new HashMap<>();
    }

    public boolean categoryExists(String name) {
        return this.database.containsKey(name);
    }

    public void addCategory(String name) {
        if (this.categoryExists(name)) {
            throw new InvalidParameterException();
        }
        this.database.put(name, new ArrayList<>());
    }

    public ArrayList<NoteEntry> getCategory(String name) {
        return this.database.get(name);
    }

    public void addEntryToCategory(String categoryName, NoteEntry entry) {
        this.getCategory(categoryName).add(entry);
    }

}
