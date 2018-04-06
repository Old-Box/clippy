package org.oldbox.clippy;

import lombok.Getter;

import java.util.ArrayList;

public class Category {

    @Getter
    private String name;
    @Getter
    private String color;
    @Getter
    private ArrayList<NoteEntry> entries;

    public Category(String name, String color) {
        this.name = name;
        this.color = color;
        this.entries = new ArrayList<>();
    }


}
