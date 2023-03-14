package project;

import javax.swing.*;
import javax.swing.JFrame;

import project.gameState.GamePanel;
public class game {
    
    private static final int ScreenWidth=1015, ScreenHeight=739;
    public static int getScreenWidth(){
        return ScreenWidth;
    }
    public static int getScreenHeight(){
        return ScreenHeight;
    }
    
    public static void main(String[] args) {        
        JFrame panel=new JFrame("Capoo Game");
        
        //set a custom Container object as the content pane of the JFrame.
        panel.setContentPane(new GamePanel());

        // if close window -> stop the program
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setSize(ScreenWidth,ScreenHeight);

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
