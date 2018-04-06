package org.oldbox.clippy;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class NoteEntry {

    @Getter
    private Date timestamp;

    @Getter
    @Setter
    private String data;

    public NoteEntry(String data) {
        this.timestamp = new Date();
        this.data = data;
    }
}
