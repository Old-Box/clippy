package org.oldbox.clippy;

public class ClippyContext {

    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
        if(ctx == null) {
            ctx = new ClippyContext();
        }

        return ctx;
    }



    private ClippyRepository repository;


    public ClippyContext() {
        this.repository = new ClippyRepository();
    }


    public ClippyRepository getRepository() {
        return repository;
    }
}
