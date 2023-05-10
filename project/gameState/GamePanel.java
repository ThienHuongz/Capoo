package project.gameState;

import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.Map;
import project.game;
import project.EventListener.KeyHandle;
import project.EventListener.MouseHandle;
import project.EventListener.WindowHandle;
import project.entity.character;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public boolean IsRun = true;
    public static int gameOverNumber = 1;
    private static int FPS = 60; // Frame per second
    private Thread thread;
    private KeyHandle key = new KeyHandle();
    private MouseHandle mouseKey;
    private GameStateManager gameState;

    private BufferedImage[] pause = new BufferedImage[2];
    private WindowHandle wh;

    public GamePanel(WindowHandle wh) {

        super();
        this.wh = wh;
        // respond to keyboard events of game panel
        this.setFocusable(true);
        mouseKey = new MouseHandle(this);
        gameState = new GameStateManager(this);

        this.addMouseListener(mouseKey);
        this.addMouseMotionListener(mouseKey);
        this.addKeyListener(key);

        try {
            pause[0] = ImageIO.read(getClass().getResourceAsStream("../../assets/pauseMenu.png"));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR");
        }
        thread = new Thread(this);

        // call run method
        thread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; // 1 giây/ 60
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        // int count=0;

        while (true) {
            IsPause();
            repaint();

            while (IsRun) {
                IsWindowDeactivated();
                update();
                // call paintcomponent
                repaint();
                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    timer += remainingTime;

                    // sleep chạy theo mili giây
                    remainingTime /= 1000000;

                    if (remainingTime < 0) {
                        remainingTime = 0;
                    }

                    Thread.sleep((long) remainingTime);

                    nextDrawTime += drawInterval;

                    // count++;
                    // if (timer >= 1000000000){
                    // // System.out.println("FPS: "+count);
                    // timer=0;
                    // count=0;
                    // }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IsPause();
            }
        }

    }

    public void IsPause() {
        if (key.isKeyEsc() == true && character.isDie != true && gameState.getCurrentState() == 2) {
            IsRun = !IsRun;
            key.setKeyEsc(false);
        }
    }

    public void IsWindowDeactivated() {
        if (wh.IsWindowDeactivated) {
            wh.IsWindowDeactivated = false;
            if (character.isDie != true && gameState.getCurrentState() == 2) {
                IsRun = false;
            }
        }
    }

    public void update() {
        if (gameState.getCurrentState() == 2 && character.isDie != true) {
            gameState.update();
            if (wh.IsWindowClosing) {
                gameState.getGamePlay().SaveUserData("assets/UserSavedGame/User1.map");
            }
        }
        // if (character.isDie != true && winnerState == null && gamePlay != null) {
        // gamePlay.update();

        // }

    }

    public void paintComponent(Graphics g) {
        // to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);
        gameState.draw(g);
        // if (mn != null)
        // mn.draw(g);

        if (!IsRun && gameState.getGamePlay() != null) {
            g.drawImage(pause[0], 250, 230, null);
        }

        // if (character.isDie == true && overState == null) {
        // // gamePlay = null;
        // overState = new GameOverState(this);
        // character.isDie = false;
        // }

        // if (Map.checkTouch == true && winnerState == null) {
        // winnerState = new WinnerState(this);
        // }

        // if (overState != null) {
        // overState.draw(g);
        // }

        // if (winnerState != null) {
        // winnerState.draw(g);
        // }

        // if (levelState != null)
        // levelState.draw(g);

    }

    public GameStateManager getGameStateManager() {
        return gameState;
    }

    public KeyHandle getKey() {
        return key;
    }

    public static int getFPS() {
        return FPS;
    }

    public void mouse_click(int mx, int my) {
        // RESUME
        if (new Rectangle(330, 295, 155, 55).contains(mx, my)) {
            IsRun = true;
            key.setKeyEsc(false);

            // gamePlay=new GamePlay(this);
        }
        // RESTART
        if (new Rectangle(540, 295, 155, 55).contains(mx, my)) {
            IsRun = true;
            key.setKeyEsc(false);

            gameState.getGamePlay().DeleteUserData("assets/UserSavedGame/User1.map");
            gameState.getGamePlay().RestartGamePlay();
            gameState.setState(2);
            // gamePlay=new GamePlay(this);
        }
        // LEVEL STATE
        if (new Rectangle(330, 365, 155, 55).contains(mx, my)) {
            IsRun = true;
            key.setKeyEsc(false);
            gameState.getGamePlay().SaveUserData("assets/UserSavedGame/User1.map");
            // gamePlay = null;
            // levelState = new LevelState(this);
            gameState.setState(1);
        }
        // MENU STATE
        if (new Rectangle(410, 420, 155, 55).contains(mx, my)) {
            IsRun = true;
            // System.exit(0);
            // gamePlay = null;
            // mn = new MenuState(this);
            gameState.setState(0);
        }
    }

}
