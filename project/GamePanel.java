package project;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
    
    private boolean IsRun=true;
    private static int FPS = 60;  // Frame per second
    private Thread thread;
    
    private SoundEffect sound = new SoundEffect();
    private MouseHandle mouseKey ;

    public MenuState mn;
    public GamePlay gamePlay;
    public KeyHandle key=new KeyHandle();
    
    public GamePanel() {
        super();

        // respond to keyboard events of game panel
        this.setFocusable(true);
        mouseKey = new MouseHandle(this);

        mn = new MenuState(this);
        this.addMouseListener(mouseKey);
        this.addMouseMotionListener(mouseKey);
        this.addKeyListener(key);

        thread=new Thread(this);
        // call run method
        thread.start();
        playBGM();
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

        if (gamePlay!=null) gamePlay.update();


    }

    public void paintComponent( Graphics g){
        //to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);

        if (mn!=null) mn.draw(g);
        if (gamePlay!=null) gamePlay.draw(g);


    }

    public static int getFPS(){
        return FPS;
    }
    public void playBGM(){
        sound.SetClip(1);
        sound.play();
        sound.loop();
    }
}  
