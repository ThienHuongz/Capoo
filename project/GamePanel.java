package project;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    
    private boolean IsRun=true;
    private int FPS = 60; 
    private Thread thread;
    
    public GamePanel() {
        thread=new Thread(this);
        thread.start();
    }

    public void run(){

        double drawInterval = 1000000000/FPS; // 1 giây/60 
        double nextDrawTime = System.nanoTime()+drawInterval;
        
        while(IsRun){
            update();
            repaint();
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime/=1000000;
                if (remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
        
    }

    private void update(){
    }

    public void paintComponent( Graphics g){
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(100, 100, 300, 300);
        g2.dispose();
    }


    

}  
