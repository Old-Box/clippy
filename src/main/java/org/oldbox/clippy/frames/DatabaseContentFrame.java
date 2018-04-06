package org.oldbox.clippy.frames;

import org.oldbox.clippy.ClippyContext;
import org.oldbox.clippy.NoteEntry;

import javax.swing.*;

public class DatabaseContentFrame extends JFrame {
    private JTextArea textArea1;
    private JPanel panel1;


    private static String getDatabaseDataAsText() {

        ClippyContext ctx = ClippyContext.getInstance();

        StringBuilder data = new StringBuilder();

        for (String categoryName : ctx.getRepository().getCategories()) {
            data.append(categoryName).append("\n");

            for (NoteEntry entry : ctx.getRepository().getCategory(categoryName).getEntries()) {
                data.append(entry.getTimestamp()).append(" ").append(entry.getData()).append("\n");
            }

            data.append("\n");
        }

        return data.toString();
    }

    public DatabaseContentFrame() {

        this.setEnabled(true);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.textArea1.setText(getDatabaseDataAsText());

        this.add(panel1);
    }

}
