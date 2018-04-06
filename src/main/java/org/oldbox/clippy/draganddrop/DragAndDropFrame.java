package org.oldbox.clippy.draganddrop;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.ClippyRepository;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;

public class DragAndDropFrame extends JFrame {
    private JTextArea droppableTextArea;
    private JPanel panel;
    private JButton saveButton;
    private JLabel categoryLabel;

    public DragAndDropFrame() {
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
        NoteEntry entry = new NoteEntry(content);
        ClippyRepository repository = ClippyContext.getInstance().getRepository();
        repository.addEntryToCategory(category, entry);
    }
}
