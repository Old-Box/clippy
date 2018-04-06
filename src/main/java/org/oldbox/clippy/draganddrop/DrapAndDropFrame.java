package org.oldbox.clippy.draganddrop;

import javax.swing.*;

public class DrapAndDropFrame extends JFrame {
    private JTextArea droppableTextArea;
    private JPanel panel;

    public DrapAndDropFrame() {
        super();
        this.setTitle("Drag and Drop");
        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(this.panel);
    }
}
