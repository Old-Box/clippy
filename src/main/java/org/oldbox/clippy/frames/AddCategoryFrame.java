package org.oldbox.clippy.frames;

import org.oldbox.clippy.ClippyContext;

import javax.swing.*;
import java.security.InvalidParameterException;

public class AddCategoryFrame extends JFrame{
    private JTextField categoryName;
    private JButton addButton;
    private JPanel panel;

    public AddCategoryFrame() {
        super();

        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(200, 120);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(this.panel);

        this.addButton.addActionListener(e -> {

            String categoryName = this.categoryName.getText();

            ClippyContext ctx = ClippyContext.getInstance();

            try {
                ctx.getRepository().addCategory(categoryName, "#000000");
                this.setVisible(false);
                this.dispose();
            } catch (InvalidParameterException exception) {
                JOptionPane.showMessageDialog(this, "Category already exists");
            }

            // a note for now, alternate way of closing window with event
            //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
    }
}
