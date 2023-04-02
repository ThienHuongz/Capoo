package project.gameState;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.SoundEffect;
import project.game;
import project.Base;
import java.io.IOException;

public class LevelState implements Base {
    private BufferedImage ls[] = new BufferedImage[10];

    private boolean levelButton[] = new boolean[7];
    GamePanel gamepanel;

    public LevelState(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        init();
    }

    public void init() {
        try {
            ls[0] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/level_menu1.png"));
            // ls[1] =
            // ImageIO.read(getClass().getResourceAsStream("../../assets/level/setting_menu.png"));
            // ls[2] =
            // ImageIO.read(getClass().getResourceAsStream("../../assets/level/stars.png"));
            ls[3] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/unlock_level1.png"));
            ls[4] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/unstar.png"));
            ls[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/background.png"));
            ls[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/Background OOP1.png"));
            ls[7] = ImageIO.read(getClass().getResourceAsStream("../../assets/level/choose.png"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        g.drawImage(ls[5], 0, 0, null);
        g.drawImage(ls[6], 0, 0, null);
        g.drawImage(ls[0], (game.getScreenWidth() - ls[0].getWidth()) / 2, 70, null);
        for (int i = 1; i < 3; i++) {
            g.drawImage(ls[3], 292 + (90 * i), 165, null);
        }
        for (int i = 0 + (GamePlay.currentLevel -1); i < 5; i++) {
            if (levelButton[i]) {
                g.drawImage(ls[7], 292 + (90 * i) - 20, 145, null);
            }

        }

    }

    public int getButtonWidth() {
        return ls[3].getWidth();
    }

    public int getButtonHeight() {
        return ls[3].getHeight();
    }

    public void mouse_move(int mx, int my) {
        for (int i = 0; i < 5; i++) {
            if (!(new Rectangle(292 + (90 * i), 165, getButtonWidth(), getButtonHeight()).contains(mx, my))) {
                levelButton[i] = false;
            } else {
                if (!levelButton[i]) {
                    SoundEffect.play(2);
                }
                levelButton[i] = true;
            }
        }

    }

    public void mouse_click(int mx, int my) {
        for (int i = 0; i < 5; i++) {
            if ((new Rectangle(292 + (90 * i), 165, getButtonWidth(), getButtonHeight()).contains(mx, my))) {
                if (GamePlay.currentLevel >= (i + 1)) {
                    SoundEffect.play(3);
                    SoundEffect.StopBGM();
                    SoundEffect.playBGM(1);
                    gamepanel.levelState = null;
                    gamepanel.gamePlay = new GamePlay(gamepanel);
                } else {
                    SoundEffect.play(8);
                }
            }
        }

    }

    public void update() {
    }
}
