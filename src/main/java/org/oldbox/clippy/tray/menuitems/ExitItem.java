package org.oldbox.clippy.tray.menuitems;

import java.awt.*;

public class ExitItem extends MenuItem {

    public ExitItem() {
        super("Exit");

        this.addActionListener(actionEvent -> System.exit(0));
    }

}
