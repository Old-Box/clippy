package org.oldbox.clippy.frames;

import javax.swing.*;

public class AddCategoryFrame extends JFrame{
    private JTextField categoryName;
    private JButton addButton;
    private JPanel panel;

    public AddCategoryFrame() {
        super();

        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(200, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(this.panel);
    }
}
