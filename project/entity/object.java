package project.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static project.gameState.GamePanel.getFPS;

public abstract class object {
    private BufferedImage obj[] = new BufferedImage[30];
    private String objName;
    private int x, y, totalImage, animation;
    private int counterStep = 0, step = 0;

    public object(int x, int y, int totalImage, String objName) {
        this.x = x;
        this.y = y;
        this.objName = objName;
        this.totalImage = totalImage;
        this.animation = (int) (getFPS() / totalImage) + 10;
        init();

    }

    public void init() {
        try {
            for (int i = 0; i < totalImage; i++) {
                obj[i] = ImageIO.read(getClass()
                        .getResourceAsStream("../../assets/" + objName + "/" + objName + "_" + (i + 1) + ".png"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update() {
        counterStep++;
        if (counterStep > animation) {
            step++;
            counterStep = 0;
            if (step >= totalImage - 1) {
                step = 1;
            } else
                step++;
        } else
            counterStep++;
    }

    public void draw(Graphics g) {
        g.drawImage(obj[step], x, y, null);
    }

    public BufferedImage getImage() {
        return obj[step];
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
