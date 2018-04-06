package org.oldbox.clippy.tray.menuitems;

import javax.swing.*;
import java.awt.*;

public class CategoryItem extends MenuItem {

    private String categoryName;

    public CategoryItem(String name) {
        super(name);
        this.categoryName = name;

        this.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Category: " + this.categoryName + " should open");
        });
    }

}
