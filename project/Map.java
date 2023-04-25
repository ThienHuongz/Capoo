package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.entity.Fish;
import project.entity.Lava;
import project.entity.ObjectTime;
import project.entity.Timer;
import project.entity.character;
import project.entity.Thorn;
import project.entity.Gate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Map implements Base {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava = new ArrayList<Lava>();
    private ArrayList<Fish> fish = new ArrayList<Fish>();
    private ArrayList<Thorn> thorn = new ArrayList<Thorn>();
    private ArrayList<ObjectTime> time = new ArrayList<ObjectTime>();
    private BufferedImage progressBar[] = new BufferedImage[2];
    private character c;

    private Gate gate;
    private Timer timeCount;
    private int score = 0;

    public Map() {
        init();

    }

    public Map(character c) {
        init();
        this.c = c;
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
            fish.add(new Fish(350, 320));
            fish.add(new Fish(900, 190));
            fish.add(new Fish(450, 80));

            time.add(new ObjectTime(200, 400));
            time.add(new ObjectTime(250, 630));

            thorn.add(new Thorn(500, 655));
            thorn.add(new Thorn(35, 150, 1));

            gate = new Gate(860, 65);
            timeCount = new Timer();

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }

    public void loadUserSavedGame(String address) {
        try {

            InputStream in = getClass().getResourceAsStream(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            score = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String delims = "\\s++";

            String[] tokens = line.split(delims);
            c.setX(Integer.parseInt(tokens[0]));
            c.setY(Integer.parseInt(tokens[1]));

            for (int i = 0; i < score; i++) {
                fish.remove(0);
            }

            br.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void SaveUserData(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println(score);
            writer.println(c.getX() + " " + c.getY());

            // for (int i = 0; i < fish.size(); i++) {
            // writer.println(fish.get(i).getX()+" "+fish.get(i).getY());
            // }

            // for (int i = 0; i < box.size; i++) {
            // writer.println(box.get(i).getX()+" "+box.get(i).getY());
            // }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteUserData(String address) {
        File myObj = new File(address);
        myObj.delete();
    }

    public BufferedImage getBackground() {
        return bg[1];
    }

    public ArrayList<ObjectTime> getTime() {
        return time;
    }

    public ArrayList<Thorn> getThorn() {
        return thorn;
    }

    public ArrayList<Fish> getFish() {
        return fish;
    }

    public ArrayList<Lava> getLava() {
        return lava;
    }

    public Gate getGate() {
        return gate;
    }

    public Timer getTimeCount() {
        return timeCount;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
