package project;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.collision.CollisionDirection;

import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava = new ArrayList<Lava>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Box> box = new ArrayList<Box>();
    private ArrayList<Rectangle> blockage = new ArrayList<Rectangle>();

    private int score = 0;
    int boxUpper;
    int standingOnBox = 0;

    public Map() {
        init();
    }

    public void draw(Graphics g) {

        g.drawImage(bg[0], 0, 0, null);
        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).draw(g);
        }

        g.drawImage(bg[1], 0, 0, null);
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).draw(g);
        }

        g.drawImage(bg[1], 0, 0, null);
        for (int i = 0; i < box.size(); i++) {
            box.get(i).draw(g);
        }

        // draw blockage right here
        g.setColor(Color.BLUE);
        blockage.add(new Rectangle(54, 172, 100, 100));

        g.fillRect(54, 172, 100, 100);

    }

    public void update() {
        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).update();
        }
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).update();
        }
        for (int i = 0; i < box.size(); i++) {
            box.get(i).update();
        }
    }

    public void init() {
        try {
            bg[0] = ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../assets/Background OOP1.png"));

            lava.add(new Lava(609, 522));
            lava.add(new Lava(500, 134));

            fish.add(new Fish(500, 480));
            fish.add(new Fish(450, 80));

            box.add(new Box(240, 215));
            box.add(new Box(609, 625));
            box.add(new Box(700, 335));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public BufferedImage getBackground() {
        return bg[1];
    }

    public boolean isCollision(int x, int y) {
        for (int i = 0; i < lava.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, lava.get(i).getImage(), lava.get(i).getX(),
                    lava.get(i).getY())) {
                return true;
            }
            for (int j = 0; j < fish.size(); j++) {
                if (collision.isCharacterCollisionObject(x, y, fish.get(j).getImage(), fish.get(j).getX(),
                        fish.get(j).getY())) {
                    score++;
                    fish.remove(j);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkCollisionBox(int x, int y, String direct) {
        for (int i = 0; i < box.size(); i++) {
            if (collision.isCharacterCollisionObjectBox(x, y, box.get(i).getImage(), box.get(i).getX(),
                    box.get(i).getY())) {
                CollisionDirection collisionDirection = collision.getCharacterCollisionDirection(x, y,
                        box.get(i).getImage(), box.get(i).getX(),
                        box.get(i).getY());
                if (collisionDirection == CollisionDirection.UP) {
                    boxUpper = (box.get(i).getY() - 50);
                    direct = "up";
                    standingOnBox = 1;

                    // Adjust character position to be above the box
                    y = box.get(i).getY() - box.get(i).getImage().getHeight();
                } else if (collisionDirection == CollisionDirection.LEFT) {
                    direct = "left";
                    box.get(i).setDirection(direct);

                    // Adjust character position to be to the left of the box
                    x = box.get(i).getX() - box.get(i).getImage().getWidth();
                } else if (collisionDirection == CollisionDirection.RIGHT) {
                    direct = "right";
                    box.get(i).setDirection(direct);

                    // Adjust character position to be to the right of the box
                    x = box.get(i).getX() + box.get(i).getImage().getWidth();
                }
                return true;
            } else {
                standingOnBox = 0;
            }
        }
        return false;
    }

    public int getMapWidth() {
        return bg[1].getWidth();
    }

    public int getMapHeight() {
        return bg[1].getHeight();
    }
}
