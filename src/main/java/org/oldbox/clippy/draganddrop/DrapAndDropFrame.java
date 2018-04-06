package org.oldbox.clippy.draganddrop;

import javax.swing.*;

public class DrapAndDropFrame extends JFrame {
    private JTextArea droppableTextArea;
    private JPanel panel;
    private JButton saveButton;
    private JLabel categoryLabel;

    public DrapAndDropFrame() {
        super();
        this.setTitle("Drag and Drop");
        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.saveButton.addActionListener(e -> saveButtonPressed());

        this.add(this.panel);
    }

    private void saveButtonPressed() {
        String content = droppableTextArea.getText();
        String category = categoryLabel.getText();
        //TODO: Save data here!
    }
}
