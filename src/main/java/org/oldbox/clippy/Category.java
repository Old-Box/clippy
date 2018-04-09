package org.oldbox.clippy;



import java.awt.*;
import java.util.ArrayList;

public class Category {


    private String name;
    private Color color;
    private ArrayList<NoteEntry> entries;

    public Category(String name, Color color) {
        this.name = name;
        this.color = color;
        this.entries = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<NoteEntry> getEntries() {
        return entries;
    }
}
