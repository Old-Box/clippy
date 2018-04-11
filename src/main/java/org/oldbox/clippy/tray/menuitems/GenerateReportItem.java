package org.oldbox.clippy.tray.menuitems;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GenerateReportItem extends MenuItem {

    public GenerateReportItem() {
        super("Generate report");

        this.addActionListener(event -> {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = getSaveFileDialog();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File report = fileChooser.getSelectedFile();
                System.out.println("Save report as: " + report.getAbsolutePath());
                writeReportContentToFile(report.getAbsolutePath(), "Test");
            }
        });
    }

    private void writeReportContentToFile(String filepath, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JFileChooser getSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where report is saved");
        fileChooser.setSelectedFile(new File("report.txt"));
        return fileChooser;
    }
}
