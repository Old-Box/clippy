package org.oldbox.clippy.tray.menuitems;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GenerateReportItem extends MenuItem {

    public GenerateReportItem() {
        super("Generate report");

        this.addActionListener(e -> {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = getSaveFileDialog();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File report = fileChooser.getSelectedFile();
                System.out.println("Save report as: " + report.getAbsolutePath());
            }
        });
    }

    private JFileChooser getSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where report is saved");
        fileChooser.setSelectedFile(new File("report.txt"));
        return fileChooser;
    }
}
