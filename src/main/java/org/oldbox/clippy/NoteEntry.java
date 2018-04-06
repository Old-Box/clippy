package org.oldbox.clippy;



import java.util.Date;

public class NoteEntry {

    private Date timestamp;
    private String data;

    public NoteEntry(String data) {
        this.timestamp = new Date();
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
