package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.Category;
import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.draganddrop.DragAndDropFrame;

import java.awt.*;

public class CategoryItem extends MenuItem {

    private String categoryName;

    public CategoryItem(String name) {
        super(name);
        this.categoryName = name;

        this.addActionListener(e -> {
            ClippyContext ctx = ClippyContext.getInstance();
            Category category = ctx.getRepository().getCategory(this.categoryName);
            new DragAndDropFrame(category);
        });
    }

}
