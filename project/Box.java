package project;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends object {
    private BufferedImage image;

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

    public Rectangle getBoundingBox() {
        return new Rectangle(super.getX(), super.getY(), image.getWidth(), image.getHeight());
    }
    
    public BufferedImage getImage(){
        return image;
    }
}