package org.oldbox.clippy.frames;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class GenerateReportFrame extends JFrame{
    private JTextField destinationTextField;
    private JButton chooseFileButton;
    private JButton generateButton;
    private JButton cancelButton;
    private JPanel panel;
    private JPanel selectDestinationPanel;
    private JPanel actionPanel;

    public GenerateReportFrame() {
        this.setTitle("Generate report");
        this.setEnabled(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        chooseFileButton.addActionListener(e -> chooseReportDestination());
        cancelButton.addActionListener(e -> this.dispose());
        generateButton.addActionListener(e -> generateReport());

        this.add(panel);
        this.pack();
    }

    private void chooseReportDestination() {
        JFrame fileDialogFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where report is saved");
        fileChooser.setSelectedFile(new File("report.txt"));
        int result = fileChooser.showSaveDialog(fileDialogFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            destinationTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        fileDialogFrame.dispose();
    }

    private void generateReport() {
        String reportContent = getReportContent();
        if (destinationTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a destination!");
            return;
        }
        try {
            writeReportContentToFile(destinationTextField.getText(), reportContent);
            JOptionPane.showMessageDialog(null, "Successfully generated report!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to write report."); //TODO: Add more elaborated error message!
        } finally {
            this.dispose();
        }
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

    private void writeReportContentToFile(String filepath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(content);
        writer.close();
    }
}
