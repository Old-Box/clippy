package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.frames.DatabaseContentFrame;

import java.awt.*;

public class DatabaseContentItem extends MenuItem {

   public DatabaseContentItem() {
       super("Show saved data");
       this.addActionListener(e -> new DatabaseContentFrame());
   }
}
