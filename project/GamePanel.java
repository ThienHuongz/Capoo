package project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private boolean IsRun = true;
    private static int FPS = 60; // Frame per second
    private Thread thread;
    private Map map = new Map();
    KeyHandle key = new KeyHandle();
    private character c;
    private SoundEffect sound = new SoundEffect();

    public GamePanel() {
        super();

        // respond to keyboard events of game panel
        this.setFocusable(true);

        this.addKeyListener(key);

        c = new character(key, map);

        thread = new Thread(this);
        // call run method
        thread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / FPS; // 1 giây/ 60
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int count = 0;

        while (IsRun) {
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

                count++;
                if (timer >= 1000000000) {
                    // System.out.println("FPS: "+count);
                    timer = 0;
                    count = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        isCollision(c.getX(), c.getY(), c.getDirect());
        c.update();
        map.update();
    }

    public void isCollision(int x, int y, String direct) {

        // IF COLIISION DONT MOVE CHARACTER
        ArrayList<Box> box = map.getBox();
        c.setIsCollisionBox(false);

        if (direct != "") {
            for (int i = 0; i < box.size(); i++) {
                if (collision.isCharacterCollisionBox(x, y, direct, box.get(i).getImage(), box.get(i).getX(),
                        box.get(i).getY())) {
                    box.get(i).setDirection(direct);
                    c.setIsCollisionBox(true);
                    break;
                }
            }
        }

        // GRAVITY CHARACTER TO BOX
        character.isCollisionBoxDown = false;
        for (int i = 0; i < box.size(); i++) {
            if (collision.isCharacterCollisionBox(x, y, "down", box.get(i).getImage(), box.get(i).getX(),
                    box.get(i).getY())) {
                character.isCollisionBoxDown = true;
                break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        // to ensure that any necessary pre-painting operations are performed
        super.paintComponent(g);
        map.draw(g);
        c.draw((Graphics2D) g);

    }

    public static int getFPS() {
        return FPS;
    }

}
