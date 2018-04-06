package org.oldbox.clippy;

import org.oldbox.clippy.tray.SystemTrayIcon;

import java.awt.*;
import java.net.URL;
import java.util.MissingResourceException;
import javax.swing.*;

public class App {


    private void startSystemTray() throws AWTException {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException();
        }

        SystemTray tray = SystemTray.getSystemTray();

        Image img = createImage("images/oldbox.png", "Clippy");

        SystemTrayIcon trayIcon = new SystemTrayIcon(img);

        tray.add(trayIcon);

    }

    private static Image createImage(String path, String description) {
        URL imageURL = SystemTrayIcon.class.getClassLoader().getResource(path);

        if (imageURL == null) {
            throw new MissingResourceException("Cannot find image", App.class.getName(), path);
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    private void run() throws AWTException {
        this.startSystemTray();
    }


    public static void main(String[] args) throws AWTException {
        new App().run();
    }
}
