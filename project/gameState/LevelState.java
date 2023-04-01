package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.SoundEffect;
import project.game;
import project.Base;
import java.io.IOException;

public class LevelState implements Base{
    private BufferedImage ls[] = new BufferedImage[10];
    private SoundEffect sound = new SoundEffect();

    private boolean levelButton[] = new boolean[3];
    GamePanel gamepanel;

    public LevelState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();
    }

    public void init() {
        try {
            ls[0] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/level_menu1.png"));
            ls[1] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/setting_menu.png"));
            ls[2] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/stars.png"));
            ls[3] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/unlock_level1.png"));
            ls[4] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/unstar.png"));
            ls[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/background.png"));
            ls[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/Background OOP1.png"));


        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        g.drawImage(ls[5], 0, 0, null);
        g.drawImage(ls[6], 0, 0, null);
        g.drawImage(ls[0], (game.getScreenWidth() - ls[0].getWidth()) / 2, 70, null);
        for (int i=1;i<3;i++){
            g.drawImage(ls[3], 292+(90*i), 165, null);
        }


    }

    public int getButtonWidth() {
        return ls[3].getWidth();
    }

    public int getButtonHeight() {
        return ls[3].getHeight();
    }

    public void mouse_move(int mx, int my) {
        for (int i=0;i<3;i++){
            if (!(new Rectangle(292+(90*i), 165, getButtonWidth(), getButtonHeight()).contains(mx, my))) {
                levelButton[i] = true;
            } else {
                if (levelButton[i]) {
                    sound.SetClip(2);
                    sound.play();
                }
                levelButton[i] = false;
            }
        }


    }

    public void mouse_click(int mx, int my) {
        if ((new Rectangle(292, 165, getButtonWidth(), getButtonHeight()).contains(mx, my))) {
            sound.SetClip(3);
            sound.play();
            SoundEffect.StopBGM();
            SoundEffect.playBGM(1);
            gamepanel.levelState = null;
            gamepanel.gamePlay = new GamePlay(gamepanel);
        }

    }



    public void update(){}
}
