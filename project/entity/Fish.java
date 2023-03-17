package project.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.gameState.GamePanel;

public class Fish extends object {

    private static final int totalImageOfFish = 24;
    private static final int animation = (int) (GamePanel.getFPS() / totalImageOfFish) + 10;
    private BufferedImage fish[] = new BufferedImage[25];

    private int counterStep = 0, step = 0;

    public Fish(int x, int y) {
        super(x, y);
        init();

    }

    public void init() {
        try {
            for (int i = 0; i < totalImageOfFish; i++) {
                fish[i] = ImageIO.read(getClass().getResourceAsStream("../../assets/Fish/fish_" + (i + 1) + ".png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        // g.drawImage(fish[step], 500, 480, null);
        // g.drawImage(fish[step], 450, 80, null);
        g.drawImage(fish[step], super.getX(), super.getY(), null);

    }

    public void update() {
        counterStep++;
        if (counterStep > animation) {
            step++;
            counterStep = 0;
            if (step >= totalImageOfFish - 1) {
                step = 0;
            } else
                step++;
        } else
            counterStep++;
    }

    public BufferedImage getImage() {
        return fish[step];
    }
}
