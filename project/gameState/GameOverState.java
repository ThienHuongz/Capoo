package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.SoundEffect;
import project.game;
import project.entity.character;

import java.io.IOException;
import java.util.Map;

public class GameOverState {
    private BufferedImage mn[] = new BufferedImage[10];
//
//    private Map map;
//    
//    private boolean startButton = false, exitButton = false;
    GamePanel gamepanel;

    public GameOverState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();
        
    }

    public GameOverState() {
    	
    }

    public void init() {
        try {
            mn[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/name1.png"));
            mn[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star1.png"));
            mn[7] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/2star.png"));
            mn[8] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/3star.png"));
            mn[9] = ImageIO.read(getClass().getResourceAsStream("/assets/gameTimePanel2.png"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {


        g.drawImage(mn[5], (game.getScreenWidth() - mn[5].getWidth()) / 2, 50, null);
        
        g.drawImage(mn[6], (game.getScreenWidth() - mn[6].getWidth())/2 ,200 , null);
        

    }

    public int getButtonWidth() {
        return mn[0].getWidth();
    }

    public int getButtonHeight() {
        return mn[0].getHeight();
    }

    public void mouse_move(int mx, int my) {

    }

    public void mouse_click(int mx, int my) {
        if (new Rectangle((game.getScreenWidth()- mn[6].getWidth())/2, 450, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
        	gamepanel.gamePlay = null;
        	character.isDie = false;
            gamepanel.overState = null;
            gamepanel.mn = new MenuState(gamepanel);
        }
        if (new Rectangle((game.getScreenWidth()- mn[6].getWidth())/2 + + mn[6].getWidth()/3, 480, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
            gamepanel.gamePlay = null;
            character.isDie = false;
            gamepanel.overState = null;
            gamepanel.levelState = new LevelState(gamepanel);
        }
         
        if (new Rectangle((game.getScreenWidth()- mn[6].getWidth())/2 + mn[6].getWidth()*2/3, 450, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
        	gamepanel.overState = null;
        	character.isDie = false;
        	gamepanel.gamePlay = null;
        	gamepanel.mn = new MenuState(gamepanel);
        }
    }
    
    public void update() {
    	
    }

}

    
