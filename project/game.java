package project;

import javax.swing.*;
import javax.swing.JFrame;

public class game {

    public static void main(String[] args) {
        JFrame panel = new JFrame("Capoo Game");

        // set a custom Container object as the content pane of the JFrame.
        panel.setContentPane(new GamePanel());

        // if close window -> stop the program
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setSize(1015, 739);

        // show the window on screen
        panel.setVisible(true);

        // can not resize window
        panel.setResizable(false);

        // set the location of a Window | null -> center of the component
        panel.setLocationRelativeTo(null);

        // add logo
        panel.setIconImage(new ImageIcon("assets/logo.png").getImage());
    }
}
