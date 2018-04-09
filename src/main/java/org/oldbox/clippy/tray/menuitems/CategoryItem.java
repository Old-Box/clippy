package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.Category;
import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.frames.DragAndDropFrame;

import javax.swing.*;
import java.awt.*;
import java.nio.file.FileSystemException;

public class CategoryItem extends MenuItem {

    private String categoryName;

    public CategoryItem(String name) {
        super(name);
        this.categoryName = name;

        this.addActionListener(e -> {
            ClippyContext ctx = ClippyContext.getInstance();
            try {
                Category category = ctx.getRepository().getCategory(this.categoryName);
                new DragAndDropFrame(category);
            } catch (FileSystemException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unable to read from save file");
            }
        });
    }

}
