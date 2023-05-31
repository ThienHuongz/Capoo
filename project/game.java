/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the main method of the game, which creates a JFrame object, 
 sets it as the content pane for a custom GamePanel object, and displays the game window on screen. 
 The game window is fixed in size and cannot be resized by the user.
*/
package project;

import javax.swing.*;
import javax.swing.JFrame;

import project.EventListener.WindowHandle;
import project.gameState.GamePanel;

public class game {

    private static final int ScreenWidth = 1015, ScreenHeight = 739;

    public static void main(String[] args) {

        JFrame panel = new JFrame("Capoo Game");
        
        WindowHandle wh = WindowHandle.getInstance();

        panel.addWindowListener(wh);
        // set a custom Container object as the content pane of the JFrame.
        panel.setContentPane(new GamePanel(wh));

        // if close window -> stop the program
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setSize(ScreenWidth, ScreenHeight);

        // show the window on screen
        panel.setVisible(true);

        // can not resize window
        panel.setResizable(false);

        // set the location of a Window | null -> center of the component
        panel.setLocationRelativeTo(null);

        // add logo
        panel.setIconImage(new ImageIcon("assets/logo.png").getImage());

    }

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static int getScreenHeight() {
        return ScreenHeight;
    }
}
