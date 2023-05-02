package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends object {
    private BufferedImage image;
    private String direction;

    public Box(int x, int y) {
        super(x, y);
        init();
    }

    public void init() {
        try {
            image = ImageIO.read(new File("assets/testBox.png"));
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

        // if (getX() < 900 && getX() > 100) {
        // int newX = this.getX() + dx;
        // int newY = this.getY() + dy;

        // this.setX(newX);
        // this.setY(newY);
        // }

        int newX = this.getX() + dx;
        int newY = this.getY() + dy;

        this.setX(newX);
        this.setY(newY);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direct) {
        direction = direct;
    }

    public void update() {
        // THE DIRECTION AND MOVE THE BOX
        if (direction == "right") {
            direction = "";
            move(3, 0);
        }

        if (direction == "left") {
            direction = "";
            move(-3, 0);
        }

        if (direction == "down") {

        }

        if (direction == "up") {

        }
    }
}