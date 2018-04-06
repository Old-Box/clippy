package org.oldbox.clippy.tray;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.tray.menuitems.AddCategoryItem;
import org.oldbox.clippy.tray.menuitems.CategoryItem;
import org.oldbox.clippy.tray.menuitems.DatabaseContentItem;
import org.oldbox.clippy.tray.menuitems.ExitItem;

import java.awt.*;
import java.util.Set;

public class SystemTrayIcon extends TrayIcon {

    private PopupMenu menu;

    private void populateMenu(Set<String> categories) {
        menu.removeAll();

        if (categories != null && categories.size() > 0) {
            for (String name: categories) {
                menu.add(new CategoryItem(name));
            }
            menu.addSeparator();
        }
        menu.add(new AddCategoryItem());
        menu.add(new DatabaseContentItem());
        menu.add(new ExitItem());
    }

    public SystemTrayIcon(Image img) {
        super(img);
        this.setToolTip("Clippy");
        menu = new PopupMenu();

        ClippyContext ctx = ClippyContext.getInstance();
        Set<String> categories = ctx.getRepository().getCategories();
        populateMenu(categories);

        ctx.addListener(e -> {
            Set<String> updatedCategories = ctx.getRepository().getCategories();

            this.populateMenu(updatedCategories);
        });

        this.setPopupMenu(menu);
    }
}
