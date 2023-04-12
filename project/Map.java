package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.entity.Fish;
import project.entity.Lava;
import project.entity.ObjectTime;
import project.entity.Timer;

import project.entity.character;
import project.gameState.GamePanel;
import project.gameState.GamePlay;
import project.gameState.MenuState;
import project.gameState.GameOverState;
import project.entity.Thorn;
import project.entity.Gate;


import java.io.IOException;
import java.util.ArrayList;

public class Map implements Base {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava = new ArrayList<Lava>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Thorn> thorn = new ArrayList<Thorn>();
    private ArrayList<ObjectTime> time = new ArrayList<ObjectTime>();
    private BufferedImage progressBar[] = new BufferedImage[2];

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


        g.drawImage(progressBar[0], 15, 15, null);
        for (int i = 0 + score; i < 4; i++) {
            g.drawImage(progressBar[1], 24 + (33 * i), 22, null);

        }
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
            progressBar[0] = ImageIO.read(getClass().getResourceAsStream("../assets/level/ProgressBar.png"));
            progressBar[1] = ImageIO.read(getClass().getResourceAsStream("../assets/level/unstar.png"));
            lava.add(new Lava(609, 522));
            lava.add(new Lava(500, 134));

            fish.add(new Fish(500, 480));
            fish.add(new Fish(450, 80));
            fish.add(new Fish(900, 190));
            fish.add(new Fish(350, 320));


            time.add(new ObjectTime(700,600));
            time.add(new ObjectTime(200,400));
            time.add(new ObjectTime(600,300));
            time.add(new ObjectTime(250,630));
            time.add(new ObjectTime(200,200));


            thorn.add(new Thorn(500, 655));
            thorn.add(new Thorn(35, 150, 1));

            gate = new Gate(860, 65);
            timeCount = new Timer();

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
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
    public ArrayList<ObjectTime> getTime(){
        return time;
    }
    public ArrayList<Thorn> getThorn(){
        return thorn;
    }
    public ArrayList<Fish> getFish(){
        return fish;
    }
    public ArrayList<Lava> getLava(){
        return lava;
    }
    public Gate getGate(){
        return gate;
    }
    public Timer getTimeCount(){
        return timeCount;
    }
    public void setScore(int score){
        this.score=score;
    }
    public int getScore(){
        return score;
    }
}
