package org.oldbox.clippy.frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.oldbox.clippy.Category;
import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenerateReportFrame extends JFrame {
    private JTextField destinationTextField;
    private JButton chooseFileButton;
    private JButton generateButton;
    private JButton cancelButton;
    private JPanel panel;
    private JPanel selectDestinationPanel;
    private JPanel actionPanel;
    private JScrollPane listScrollPane;
    private JList categoryList;
    private JPanel categorySelectionPanel;
    private JCheckBox selectAllCheckBox;

    private int categoryCount;


    public GenerateReportFrame() {
        this.setTitle("Generate report");
        this.setEnabled(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        try {
            Set<String> categories = ClippyContext.getInstance().getRepository().getCategories();

            categoryCount = categories.size();
            DefaultListModel model = new DefaultListModel();

            categories.forEach(category -> model.addElement(category));
            categoryList = new JList(model);
            categoryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            categoryList.setLayoutOrientation(JList.VERTICAL);
            categoryList.setVisibleRowCount(-1);
            listScrollPane.setViewportView(categoryList);
            listScrollPane.setSize(getScrollPaneDimension(categories));


        } catch (FileSystemException e) {
            e.printStackTrace();
        }

        chooseFileButton.addActionListener(e -> chooseReportDestination());
        cancelButton.addActionListener(e -> this.dispose());
        generateButton.addActionListener(e -> generateReport());

        this.add(panel);
        this.pack();
    }

    private Dimension getScrollPaneDimension(Set<String> categories) {
        int height = categories.size() * 10;
        int width = 0;
        for (String category : categories) {
            if (category.length() > width) {
                width = category.length();
            }
        }
        width *= 10;
        return new Dimension(width, height);
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
        if (destinationTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a destination!");
            return;
        }

        String reportContent = "";
        if (selectAllCheckBox.isSelected() || categoryList.getSelectedValuesList().size() == categoryCount) {
            reportContent = getContentForAllCategories();
        } else {
            reportContent = getContentForSelectedCategories(categoryList.getSelectedValuesList());
        }

        if (reportContent.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There was no content available for the report.\nPlease add entries first!");
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

    private String getContentForAllCategories() {
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

    private void appendContentFromAllCategories(ClippyContext ctx, StringBuilder builder) throws FileSystemException {
        for (String categoryName : ctx.getRepository().getCategories()) {
            appendCategoryHeader(builder, categoryName);
            appendEntries(builder, ctx.getRepository().getCategory(categoryName).getEntries());
            builder.append("--------------")
                    .append(System.lineSeparator());
        }
    }

    private String getContentForSelectedCategories(List selectedCategoryNames) {
        List<String> names = new ArrayList<>();
        selectedCategoryNames.forEach(name -> names.add((String)name));
        //for (Object o : selectedCategoryNames) {
        //    names.add((String) o);
        //}
        ClippyContext ctx = ClippyContext.getInstance();
        List<Category> categories = new ArrayList<>();
        try {
            categories = ctx.getRepository().getCategories(names);
        } catch (FileSystemException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage()); //TODO: Need to improve exception logic in UI and repository
        }
        return getContentForCategories(categories);
    }

    private String getContentForCategories(List<Category> categories) {
        StringBuilder builder = new StringBuilder();
        for (Category category : categories) {
            appendCategoryHeader(builder, category.getName());
            appendEntries(builder, category.getEntries());
            builder.append("--------------")
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }


    private void appendCategoryHeader(StringBuilder builder, String categoryName) {
        builder.append(categoryName)
                .append(System.lineSeparator())
                .append(System.lineSeparator());
    }

    private void appendEntries(StringBuilder builder, List<NoteEntry> entries) {
        for (NoteEntry entry : entries) {
            builder.append(entry.getTimestamp())
                    .append(System.lineSeparator())
                    .append(entry.getData())
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }
    }

    private void writeReportContentToFile(String filepath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(content);
        writer.close();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(5, 2, new Insets(10, 10, 10, 10), -1, -1));
        selectDestinationPanel = new JPanel();
        selectDestinationPanel.setLayout(new GridLayoutManager(1, 4, new Insets(10, 10, 10, 10), -1, -1));
        panel.add(selectDestinationPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Destination");
        selectDestinationPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        selectDestinationPanel.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        destinationTextField = new JTextField();
        selectDestinationPanel.add(destinationTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, -1), new Dimension(150, -1), null, 0, false));
        chooseFileButton = new JButton();
        chooseFileButton.setText("Choose file");
        selectDestinationPanel.add(chooseFileButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayoutManager(1, 3, new Insets(10, 10, 10, 10), -1, -1));
        panel.add(actionPanel, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        generateButton = new JButton();
        generateButton.setMargin(new Insets(2, 14, 2, 14));
        generateButton.setText("Generate");
        actionPanel.add(generateButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        actionPanel.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        actionPanel.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categorySelectionPanel = new JPanel();
        categorySelectionPanel.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), -1, -1));
        panel.add(categorySelectionPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        listScrollPane = new JScrollPane();
        categorySelectionPanel.add(listScrollPane, new GridConstraints(1, 1, 2, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), new Dimension(200, 150), new Dimension(300, -1), 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Select categories to include in report");
        categorySelectionPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        categorySelectionPanel.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        categoryList = new JList();
        categorySelectionPanel.add(categoryList, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectAllCheckBox = new JCheckBox();
        selectAllCheckBox.setLabel("Select All");
        selectAllCheckBox.setText("Select All");
        categorySelectionPanel.add(selectAllCheckBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label1.setLabelFor(destinationTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
