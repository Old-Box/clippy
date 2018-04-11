package org.oldbox.clippy.tray.menuitems;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.FileSystemException;

public class GenerateReportItem extends MenuItem {

    public GenerateReportItem() {
        super("Generate report");

        this.addActionListener(event -> {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = getSaveFileDialog();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {

                String reportContent = getReportContent();
                File report = fileChooser.getSelectedFile();
                writeReportContentToFile(report.getAbsolutePath(), reportContent);
            }
        });
    }

    private String getReportContent() {
        ClippyContext ctx = ClippyContext.getInstance();
        StringBuilder data = new StringBuilder();

        try {
            appendContentFromAllCategories(ctx, data);
        } catch (FileSystemException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read from save file");
        }
        return data.toString();
    }

    private void appendContentFromAllCategories(ClippyContext ctx, StringBuilder data) throws FileSystemException {
        for (String categoryName : ctx.getRepository().getCategories()) {
            data.append(categoryName)
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());

            for (NoteEntry entry : ctx.getRepository().getCategory(categoryName).getEntries()) {
                data.append(entry.getTimestamp())
                        .append(System.lineSeparator())
                        .append(entry.getData())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            }

            data.append("--------------")
                    .append(System.lineSeparator());
        }
    }

    private void writeReportContentToFile(String filepath, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to write report.");
        }
    }

    private JFileChooser getSaveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where report is saved");
        fileChooser.setSelectedFile(new File("report.txt"));
        return fileChooser;
    }
}
