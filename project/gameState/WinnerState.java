package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.Map;
import project.game;

import java.io.IOException;

public class WinnerState implements GameStateBase {
    private BufferedImage mn[] = new BufferedImage[20];
    private BufferedImage bg[] = new BufferedImage[2];

    // private boolean startButton = false, exitButton = false;
    GamePanel gamepanel;
    private int xSpeed = 2;
    private int ySpeed = 1;
    private int xCapoo1 = 120;
    private int yCapoo1 = 330;
    private int xCapoo2 = 750;
    private int yCapoo2 = 300;

    public WinnerState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();

    }

    public WinnerState() {

    }

    public void init() {
        try {
        	
        	bg[0] = ImageIO.read(getClass().getResourceAsStream("../../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../../assets/Background OOP1.png"));
        	
        	mn[0] = ImageIO.read(getClass().getResourceAsStream("/assets/start1.png"));
            mn[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/name1.png"));
            mn[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star1.png"));
            mn[7] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star2.png"));
            mn[8] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/Star3.png"));
            mn[9] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooWin1.png"));
            mn[10] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooWin2.png"));
        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        
    	g.setColor(new Color(0, 0, 0, 150));
    	
    	g.drawImage(bg[0], 0, 0, null);
        g.drawImage(bg[1], 0, 0, null);
    	
    	g.fillRect(0, 0, game.getScreenWidth(), game.getScreenHeight());

        g.drawImage(mn[5], (game.getScreenWidth() - mn[5].getWidth()) / 2, 50, null);

        if (Map.score == 1 || Map.score == 0) {
            g.drawImage(mn[6], (game.getScreenWidth() - mn[6].getWidth()) / 2, 200, null);
        } else if (Map.score == 2 || Map.score == 3) {
            g.drawImage(mn[7], (game.getScreenWidth() - mn[6].getWidth()) / 2, 200, null);
        } else if (Map.score == 4) {
            g.drawImage(mn[8], (game.getScreenWidth() - mn[6].getWidth()) / 2, 200, null);
        }
        
        g.drawImage(mn[9], getxCapoo1(), getyCapoo1(), null);
        g.drawImage(mn[10], getxCapoo2() , getyCapoo2(), null);

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
        if (Map.score == 1 || Map.score == 0) {
            create_rectangle(6, mx, my);
        } else if (Map.score == 2 || Map.score == 3) {
            create_rectangle(7, mx, my);
        } else if (Map.score == 4) {
            create_rectangle(8, mx, my);
        }
    }

    public void create_rectangle(int i, int mx, int my) {
        if (new Rectangle((game.getScreenWidth() - mn[i].getWidth()) / 2, 450, getButtonWidth(), getButtonHeight())
                .contains(mx, my)) {
            gamepanel.getGameStateManager().setState(1);
            gamepanel.getGameStateManager().getGamePlay().RestartGamePlay();

        } else if (new Rectangle((game.getScreenWidth() - mn[i].getWidth()) / 2 + +mn[i].getWidth() / 3, 480,
                getButtonWidth(), getButtonHeight()).contains(mx, my)) {
            gamepanel.getGameStateManager().setState(2);
            gamepanel.getGameStateManager().getGamePlay().RestartGamePlay();

        } else if (new Rectangle((game.getScreenWidth() - mn[i].getWidth()) / 2 + mn[i].getWidth() * 2 / 3, 450,
                getButtonWidth(), getButtonHeight()).contains(mx, my)) {
            gamepanel.getGameStateManager().setState(2);
            gamepanel.getGameStateManager().getGamePlay().RestartGamePlay();

        }
    }

    public void update() {
    	if(xCapoo1 > game.getScreenWidth() - mn[9].getWidth() -10 || xCapoo1 < 10) {
    		xSpeed = xSpeed * (-1);
    	}
    	xCapoo1 = xCapoo1 + xSpeed;
    	
    	if(yCapoo1 > game.getScreenHeight() - mn[9].getHeight() -10 || yCapoo1 < 10) {
    		ySpeed = ySpeed * (-1);
    	}
    	yCapoo1 = yCapoo1 + ySpeed;
    	
    	if(xCapoo2 > game.getScreenWidth() - mn[10].getWidth() -10 || xCapoo2 < 10) {
    		xSpeed = xSpeed * (-1);
    	}
    	xCapoo2 = xCapoo2 - xSpeed;
    	
    	if(yCapoo2 > game.getScreenHeight() - mn[10].getHeight() -10 || yCapoo2 < 10) {
    		ySpeed = ySpeed * (-1);
    	}
    	yCapoo2 = yCapoo2 - ySpeed;
    }
    
    public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getxCapoo1() {
		return xCapoo1;
	}

	public void setxCapoo1(int xCapoo1) {
		this.xCapoo1 = xCapoo1;
	}

	public int getyCapoo1() {
		return yCapoo1;
	}

	public void setyCapoo1(int yCapoo1) {
		this.yCapoo1 = yCapoo1;
	}

	public int getxCapoo2() {
		return xCapoo2;
	}

	public void setxCapoo2(int xCapoo2) {
		this.xCapoo2 = xCapoo2;
	}

	public int getyCapoo2() {
		return yCapoo2;
	}

	public void setyCapoo2(int yCapoo2) {
		this.yCapoo2 = yCapoo2;
	}

}
