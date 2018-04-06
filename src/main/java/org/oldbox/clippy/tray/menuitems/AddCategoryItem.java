package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.frames.AddCategoryFrame;

import java.awt.*;

public class AddCategoryItem extends MenuItem {

    public AddCategoryItem() {
        super("Add category");

        this.addActionListener(actionEvent -> {
           new AddCategoryFrame();
        });
    }
}
