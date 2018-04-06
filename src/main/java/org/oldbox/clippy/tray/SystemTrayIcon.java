package org.oldbox.clippy.tray;

import org.oldbox.clippy.tray.menuitems.AddCategoryItem;
import org.oldbox.clippy.tray.menuitems.ExitItem;

import java.awt.*;

public class SystemTrayIcon extends TrayIcon {

    private static PopupMenu createPopupMenu() {
        PopupMenu menu = new PopupMenu();

        menu.add(new AddCategoryItem());
        menu.add(new ExitItem());

        return menu;
    }


    public SystemTrayIcon(Image img) {
        super(img);

        this.setPopupMenu(createPopupMenu());
    }
}
