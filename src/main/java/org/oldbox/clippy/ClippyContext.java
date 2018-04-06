package org.oldbox.clippy;

import lombok.Getter;

public class ClippyContext {

    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
        if(ctx == null) {
            ctx = new ClippyContext();
        }

        return ctx;
    }

    @Getter
    private ClippyRepository repository;

    public ClippyContext() {
        this.repository = new ClippyRepository();
    }


}
