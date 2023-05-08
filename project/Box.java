package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends object {
    private BufferedImage image;
    private String direction = "";

    public Box(int x, int y) {
        super(x, y);
        init();
    }

    public void init() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/testBox.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, super.getX(), super.getY(), null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void move(int dx, int dy) {
        int newX = this.getX() + dx;
        int newY = this.getY() + dy;
        if (newX < collision.bg.getWidth() - image.getWidth() - 25 && newX >= 25) {
            this.setX(newX);
        }
        if (newY < collision.bg.getHeight() - image.getHeight() - 25 && newY >= 25) {
            this.setY(newY);
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direct) {
        direction = direct;
    }

    public void update() {
        // THE DIRECTION AND MOVE THE BOX
        if (!collision.isBoxCollisionBg("down", image, super.getX(), super.getY())) {
            direction = "down";
        }
        switch (direction) {
            case "right":
                if (!collision.isBoxCollisionBg("right", image, super.getX(), super.getY())) {
                    move(3, 0);
                }
                break;
            case "left":
                if (!collision.isBoxCollisionBg("left", image, super.getX(), super.getY())) {
                    move(-3, 0);
                }
                break;
            case "down":
                move(0, 3);
                break;
            default:

        }
        direction = "";

    }
}