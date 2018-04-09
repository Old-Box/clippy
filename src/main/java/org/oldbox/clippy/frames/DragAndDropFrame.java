package org.oldbox.clippy.frames;

import org.oldbox.clippy.Category;
import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.ClippyRepository;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;
import java.awt.*;

public class DragAndDropFrame extends JFrame {
    private JTextArea droppableTextArea;
    private JPanel panel;
    private JButton saveButton;
    private JLabel categoryLabel;
    private JLabel messageLabel;
    private JScrollBar scrollBar1;

    public DragAndDropFrame(Category category) {
        super();
        configureFrame();

        updateCategoryLabel(category);
        this.saveButton.addActionListener(e -> saveButtonPressed());

        this.add(this.panel);
    }

    private void configureFrame() {
        this.setTitle("Drag and Drop");
        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void updateCategoryLabel(Category category) {
        this.categoryLabel.setText(category.getName());
        this.categoryLabel.setForeground(category.getColor());
    }

    private void saveButtonPressed() {
        String content = droppableTextArea.getText();
        String category = categoryLabel.getText();
        NoteEntry entry = new NoteEntry(content);
        ClippyRepository repository = ClippyContext.getInstance().getRepository();
        repository.addEntryToCategory(category, entry);
        droppableTextArea.setText(""); // If this is not desired automatically, then a check box could be added for automatic clean up.
        showSavedMessage();
    }

    private void showSavedMessage() {
        messageLabel.setText("Entry saved");
        messageLabel.setForeground(Color.GREEN);
        messageLabel.updateUI();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                messageLabel.setText("");
                messageLabel.updateUI();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBackground(new Color(-12434878));
        panel.setEnabled(true);
        saveButton = new JButton();
        saveButton.setText("Save");
        panel.add(saveButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryLabel = new JLabel();
        categoryLabel.setBackground(new Color(-16777216));
        categoryLabel.setEnabled(true);
        categoryLabel.setForeground(new Color(-1));
        categoryLabel.setText("CATEGORY HERE...");
        panel.add(categoryLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        messageLabel = new JLabel();
        messageLabel.setText("");
        panel.add(messageLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        droppableTextArea = new JTextArea();
        scrollPane1.setViewportView(droppableTextArea);
        scrollBar1 = new JScrollBar();
        panel.add(scrollBar1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        categoryLabel.setLabelFor(droppableTextArea);
        scrollPane1.setVerticalScrollBar(scrollBar1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
