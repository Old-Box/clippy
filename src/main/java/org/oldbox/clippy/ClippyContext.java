package org.oldbox.clippy;

import lombok.Getter;

public class ClippyContext {

    @Getter
    private ClippyRepository repository;

    private static ClippyContext ctx = null;

    public static ClippyContext getInstance() {
       if(ctx == null) {
           ctx = new ClippyContext();
       }

       return ctx;
    }


    public ClippyContext() {
        this.repository = new ClippyRepository();
    }


}
