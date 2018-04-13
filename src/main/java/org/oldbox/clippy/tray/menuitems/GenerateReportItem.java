package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.frames.GenerateReportFrame;

import java.awt.*;

public class GenerateReportItem extends MenuItem {

    public GenerateReportItem() {
        super("Generate report");

        this.addActionListener(event -> new GenerateReportFrame());
    }
}
