package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.SoundEffect;
import project.game;

import java.io.IOException;
public class MenuState {
    private BufferedImage mn[] = new BufferedImage[10];
    private SoundEffect sound=new SoundEffect();
    private SoundEffect soundBGM=new SoundEffect();

    private boolean startButton=false, exitButton=false;
    GamePanel gamepanel;

    public MenuState(GamePanel gamepanel){
        playBGM(4);
        this.gamepanel=gamepanel;
        init();
    }

    public void init(){
        try {
            mn[0]=ImageIO.read(getClass().getResourceAsStream("../../assets/start1.png"));
            mn[1]=ImageIO.read(getClass().getResourceAsStream("../../assets/start2.png"));
            mn[2]=ImageIO.read(getClass().getResourceAsStream("../../assets/exit1.png"));
            mn[3]=ImageIO.read(getClass().getResourceAsStream("../../assets/exit2.png"));
            mn[4]=ImageIO.read(getClass().getResourceAsStream("../../assets/Layer 1.png"));
            mn[5]=ImageIO.read(getClass().getResourceAsStream("../../assets/name1.png"));


        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }
    public void draw(Graphics g){
        g.drawImage(mn[4],0,0, null);
        if  (startButton) {
            g.drawImage(mn[0],370,350, null);
        }
        else {
            g.drawImage(mn[1],370,350, null);
        }
        if  (exitButton) {
            g.drawImage(mn[2],370,490, null);
        }
        else {
            g.drawImage(mn[3],370,490, null);
        }
        g.drawImage(mn[5],(game.getScreenWidth()-mn[5].getWidth())/2,140, null);

    }
    public int getButtonWidth(){
        return mn[0].getWidth();
    }
    public int getButtonHeight(){
        return mn[0].getHeight();
    }
    public void mouse_move(int mx, int my){
        if (!(new Rectangle(370, 350, getButtonWidth(), getButtonHeight()).contains(mx, my))){

            startButton=true;

        }
        else {
            if (startButton){
                sound.SetClip(2);
                sound.play();
            }
            startButton=false;
        }
        if (!(new Rectangle(370, 490, getButtonWidth(), getButtonHeight()).contains(mx, my))){
            exitButton=true;
        }
        else {
            if (exitButton){
                sound.SetClip(2);
                sound.play();
            }
            exitButton=false;
        }
    }
    public void mouse_click(int mx,int my){
        if (new Rectangle(370, 350, getButtonWidth(), getButtonHeight()).contains(mx, my)){
            sound.SetClip(3);
            sound.play();
            soundBGM.pause();
            playBGM(1);
            gamepanel.mn=null;
            gamepanel.gamePlay=new GamePlay(gamepanel);
        }
        if (new Rectangle(370, 490, getButtonWidth(), getButtonHeight()).contains(mx, my)){
            System.exit(0);
        }
    }
    public void playBGM(int i){
        soundBGM.SetClip(i);
        soundBGM.play();
        soundBGM.loop();
    }
}
