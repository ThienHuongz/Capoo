package project;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    
    private boolean IsRun=true;
    private static int FPS = 60;  // Frame per second
    private Thread thread;
    private Map map=new Map();
    KeyHandle key=new KeyHandle();
    private character c;

    public GamePanel() {
        super();

        // respond to keyboard events of game panel
        this.setFocusable(true);

        this.addKeyListener(key);

        c = new character(key,map);        

        thread=new Thread(this);
        // call run method
        thread.start();
    }

    public void run(){

        double drawInterval = 1000000000/FPS; // 1 giây/ 60 
        double nextDrawTime = System.nanoTime()+drawInterval;
        long timer =0;
        int count=0;
        
        while(IsRun){
            update();
            // call paintcomponent
            repaint();
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                timer+=remainingTime;
                
                // sleep chạy theo mili giây
                remainingTime/=1000000;

                if (remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime+=drawInterval;

                count++;
                if (timer >= 1000000000){
                    // System.out.println("FPS: "+count);
                    timer=0;
                    count=0;
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    

    public void update(){
        // if(key.isKeyEsc() == true) {
        //     IsRun=false;
        // }
        c.update();

    }

    public void paintComponent( Graphics g){
        //to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);
        map.draw(g);
        c.draw((Graphics2D) g);

    }

    public static int getFPS(){
        return FPS;
    }
}  
