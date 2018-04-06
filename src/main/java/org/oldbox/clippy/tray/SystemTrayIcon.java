package org.oldbox.clippy.tray;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.tray.menuitems.AddCategoryItem;
import org.oldbox.clippy.tray.menuitems.CategoryItem;
import org.oldbox.clippy.tray.menuitems.DatabaseContentItem;
import org.oldbox.clippy.tray.menuitems.ExitItem;

import java.awt.*;
import java.util.Set;

public class SystemTrayIcon extends TrayIcon {

    private static PopupMenu createPopupMenu(Set<String> categories) {
        PopupMenu menu = new PopupMenu();

        if (categories != null) {
            for (String name: categories) {
                menu.add(new CategoryItem(name));
            }
            menu.addSeparator();
        }

        menu.add(new AddCategoryItem());
        menu.add(new DatabaseContentItem());
        menu.add(new ExitItem());

        return menu;
    }


    public SystemTrayIcon(Image img) {
        super(img);

        ClippyContext ctx = ClippyContext.getInstance();

        ctx.addListener(e -> {
            Set<String> categories = ctx.getRepository().getCategories();

            this.setPopupMenu(createPopupMenu(categories));
        });

        this.setPopupMenu(createPopupMenu(null));
    }
}
