package project.gameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import project.game;
import project.entity.character;

import java.io.IOException;

public class GameOverState implements GameStateBase {
    private BufferedImage bg[] = new BufferedImage[2];
    private BufferedImage mn[] = new BufferedImage[20];

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
            mn[2] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/cloud.png"));
            mn[3] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/exit.png"));
            mn[4] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/menu.png"));
            mn[9] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/start.png"));

            mn[5] = ImageIO.read(getClass().getResourceAsStream("../../assets/gameOverName.png"));
            mn[6] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead1.png"));
            mn[7] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead2.png"));
            mn[8] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead3.png"));
            mn[10] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead4.png"));
            mn[11] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead5.png"));
            mn[12] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooDead6.png"));
            mn[13] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooWin1.png"));
            mn[14] = ImageIO.read(getClass().getResourceAsStream("../../assets/endState/deadState/CapooWin2.png"));

            bg[0] = ImageIO.read(getClass().getResourceAsStream("../../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../../assets/Background OOP1.png"));

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
        g.drawImage(mn[2], 0, game.getScreenHeight() / 2 - 150, null);
        g.drawImage(mn[2], game.getScreenWidth() - mn[2].getWidth(), game.getScreenHeight() / 2 - 150, null);
        // g.drawImage(mn[1], (game.getScreenWidth() - mn[1].getWidth()) / 2,
        // game.getScreenHeight()-250, null);

        g.drawImage(mn[3], (game.getScreenWidth() - mn[3].getWidth()) / 2, 400, null);

        g.drawImage(mn[4], (game.getScreenWidth() - mn[4].getWidth()) / 2, 300, null);

        g.drawImage(mn[6], 50, 200, null);
        g.drawImage(mn[7], 830, 200, null);
        g.drawImage(mn[8], 230, 470, null);
        g.drawImage(mn[10], 650, 450, null);
        g.drawImage(mn[11], 150, 330, null);
        g.drawImage(mn[12], 750, 300, null);

        // Retry

        // Back to the titel Screen

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

        if (new Rectangle((game.getScreenWidth() - mn[4].getWidth()) / 2, 300, getButtonWidth(), getButtonHeight())
                .contains(mx, my)) {
            // gamepanel.gamePlay = null;
            character.isDie = false;
            // gamepanel.overState = null;
            // gamepanel.levelState = new LevelState(gamepanel);
            gamepanel.getGameStateManager().setState(1);
            gamepanel.getGameStateManager().getGamePlay().RestartGamePlay();
        } else if (new Rectangle((game.getScreenWidth() - mn[3].getWidth()) / 2, 400, getButtonWidth(),
                getButtonHeight()).contains(mx, my)) {

            System.exit(0);
        }
    }

    public void update() {

    }

}
