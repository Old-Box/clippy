package org.oldbox.clippy;



import java.util.ArrayList;

public class Category {


    private String name;
    private String color;
    private ArrayList<NoteEntry> entries;

    public Category(String name, String color) {
        this.name = name;
        this.color = color;
        this.entries = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<NoteEntry> getEntries() {
        return entries;
    }
}
