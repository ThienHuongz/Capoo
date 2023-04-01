package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.entity.Fish;
import project.entity.Lava;
import project.entity.ObjectTime;
import project.entity.Timer;
import project.entity.character;
import project.entity.object;
import project.entity.Thorn;
import project.entity.Gate;

import java.io.IOException;
import java.util.ArrayList;

public class Map implements Base{

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava = new ArrayList<Lava>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Thorn> thorn = new ArrayList<Thorn>();
    private ArrayList<ObjectTime> time = new ArrayList<ObjectTime>();

    private Gate gate;
    private Timer timeCount;

    private int score = 0;

    public Map() {
        init();
    }

    public void draw(Graphics g) {
        g.drawImage(bg[0], 0, 0, null);

        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).draw(g);
        }

        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).draw(g);
        }

        for (int i = 0; i < time.size(); i++) {
            time.get(i).draw(g);
        }
        for (int i = 0; i < thorn.size(); i++) {
            thorn.get(i).draw(g);
        }

        gate.draw(g);

        g.drawImage(bg[1], 0, 0, null);
        timeCount.draw(g);

    }

    public void update() {

        for (int i = 0; i < lava.size(); i++) {
            lava.get(i).update();
        }
        for (int i = 0; i < fish.size(); i++) {
            fish.get(i).update();
        }

        timeCount.update();

    }

    public void init() {
        try {
            bg[0] = ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../assets/Background OOP1.png"));

            lava.add(new Lava(609, 522));
            lava.add(new Lava(500, 134));

            fish.add(new Fish(500, 480));
            fish.add(new Fish(450, 80));

            time.add(new ObjectTime(250, 630));
            time.add(new ObjectTime(700, 600));
            // time.add(new ObjectTime(200,400));
            // time.add(new ObjectTime(600,300));
            // time.add(new ObjectTime(250,630));
            // time.add(new ObjectTime(200,200));

            thorn.add(new Thorn(500, 655));
            thorn.add(new Thorn(35, 150,1));

            gate = new Gate(860, 65);
            timeCount = new Timer();

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    // Bounded Type Parameters
    // a method that operates on "object" might only want to accept instances of
    // "object" or its subclasses
    public <T extends object> boolean forLoopCollision(ArrayList<T> obj, int x, int y, int type) {
        for (int i = 0; i < obj.size(); i++) {
            if (collision.isCharacterCollisionObject(x, y, obj.get(i).getImage(), obj.get(i).getX(),
                    obj.get(i).getY())) {
                if (type == 1) {
                    obj.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    public int isCollision(int x, int y) {
        if (forLoopCollision(lava, x, y, 0) || forLoopCollision(thorn, x, y, 0)) {
            character.isDie = true;
            return 1;
        }
        if (forLoopCollision(fish, x, y, 1)) {
            score++;
            return 1;
        }
        if (forLoopCollision(time, x, y, 1)) {
            timeCount.countdownTime = timeCount.countdownTime + timeCount.plusSecond;
            return 1;
        }
        if (collision.isCharacterCollisionObject(x, y, gate.getImage(), gate.getX(),
                gate.getY()) ) {
            if (!gate.checkTouch){
                gate.setStep(1);
                gate.checkTouch = true;
                return 2;
            }
        }else{
            if (gate.checkTouch){
                gate.setStep(0);
                gate.checkTouch = false;
                return 3;
            }
        } 
        return 0;
    }

    public int getMapWidth() {
        return bg[1].getWidth();
    }

    public int getMapHeight() {
        return bg[1].getHeight();
    }

    public BufferedImage getBackground() {
        return bg[1];
    }



}
