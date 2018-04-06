package org.oldbox.clippy;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClippyContext {

    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
        if(ctx == null) {
            ctx = new ClippyContext();
        }

        return ctx;
    }

    public static final int DATABASE_UPDATED = 1337;


    private ClippyRepository repository;

    private ArrayList<ActionListener> listeners;

    public void triggerListeners() {
        for(ActionListener a: listeners) {
            a.actionPerformed(new ActionEvent(this, DATABASE_UPDATED, "database was updated"));
        }
    }

    public void addListener(ActionListener listener) {
        this.listeners.add(listener);
    }

    public ClippyContext() {
        this.repository = new ClippyRepository();
        this.listeners = new ArrayList<>();
    }


    public ClippyRepository getRepository() {
        return repository;
    }
}
