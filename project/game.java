package project;

import javax.swing.JFrame;
public class game {

    public static void main(String[] args) {        

        KeyHandle key=new KeyHandle();
        JFrame panel=new JFrame("Capoo Game");
        panel.setContentPane(new GamePanel(key));

        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(1200,800);
        panel.setVisible(true);
        panel.setResizable(false);
        panel.setLocationRelativeTo(null); 
        panel.addKeyListener(key);
        panel.setFocusable(true);


    }
}
