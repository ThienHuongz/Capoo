package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import project.Map;
import project.SoundEffect;
import project.game;
import project.entity.character;

import java.io.IOException;


public class GameOverState {
    private BufferedImage mn[] = new BufferedImage[10];
  
    GamePanel gamepanel;


    public GameOverState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();
        
    }

    public GameOverState() {
    	
    }

    public void init() {
        try {
        	mn[0] = ImageIO.read(getClass().getResourceAsStream("/assets/start1.png"));
        	mn[1] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/game_over.png"));
        	mn[2] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/decorate.png"));
        	mn[3] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/exit.png"));
        	mn[4] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/menu.png"));
        	mn[9] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/start.png"));
        	
            mn[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/name1.png"));
            mn[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star1.png"));
            mn[7] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star2.png"));
            mn[8] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star3.png"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {

     
        g.setColor( new Color(0,0,0,150));
        g.fillRect(0, 0, game.getScreenWidth(), game.getScreenHeight());
        
        g.drawImage(mn[5], (game.getScreenWidth() - mn[5].getWidth()) / 2, 50, null);
        g.drawImage(mn[2],  0, game.getScreenHeight()/2, null);
        // Exit
        g.drawImage(mn[3], (game.getScreenWidth() - mn[3].getWidth()) / 2, 500, null);
        // Menu
        g.drawImage(mn[4], (game.getScreenWidth() - mn[4].getWidth()) / 2, 400, null);
        //Start
        g.drawImage(mn[9], (game.getScreenWidth() - mn[9].getWidth()) / 2, 300, null);
        
        g.drawImage(mn[1], 100, 200, null);
        g.drawImage(mn[1], 600, 200, null);
        
        //Retry
        
        
        
        //Back to the titel Screen

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
        if (new Rectangle((game.getScreenWidth()- mn[4].getWidth())/2, 400, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
        	gamepanel.gamePlay = null;
        	character.isDie = false;
            gamepanel.overState = null;
            gamepanel.mn = new MenuState(gamepanel);
        } 
        else if (new Rectangle((game.getScreenWidth()- mn[9].getWidth())/2, 300, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
            gamepanel.gamePlay = null;
            character.isDie = false;
            gamepanel.overState = null;
            gamepanel.levelState = new LevelState(gamepanel);
        }        
        else if (new Rectangle((game.getScreenWidth()- mn[3].getWidth())/2, 500, getButtonWidth(), getButtonHeight()).contains(mx, my)) {
//        	gamepanel.overState = null;
//        	character.isDie = false;
//        	gamepanel.gamePlay = null;
//        	gamepanel.mn = new MenuState(gamepanel);
        	System.exit(0);
        }
    }
    
    public void update() {
    	
    }

}

    
