package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.entity.*;

import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Map implements Base {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava;
    private ArrayList<Fish> fish;
    private ArrayList<Thorn> thorn;
    private ArrayList<ObjectTime> time;
    private Gate gate;
    private ArrayList<Box> box;
    private BufferedImage progressBar[] = new BufferedImage[2];
    private character c;

    private Timer timeCount;
    public static int score = 0;
    public static boolean checkTouch = false;

    public Map() {
        loadImage();
    }

    public Map(character c) {
        this.c = c;
        init();
        loadImage();
    }

    public void loadImage() {
        try {
            bg[0] = ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            bg[1] = ImageIO.read(getClass().getResourceAsStream("../assets/Background OOP1.png"));
            progressBar[0] = ImageIO.read(getClass().getResourceAsStream("../assets/level/ProgressBar.png"));
            progressBar[1] = ImageIO.read(getClass().getResourceAsStream("../assets/level/unstar.png"));
        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
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
        for (int i = 0; i < box.size(); i++) {
            box.get(i).draw(g);
        }

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

        timeCount.update();

    }

    public void init() {

        lava = new ArrayList<Lava>();
        lava.add(new Lava(609, 522));
        lava.add(new Lava(500, 134));

        fish = new ArrayList<Fish>();
        fish.add(new Fish(500, 480));
        fish.add(new Fish(350, 320));
        fish.add(new Fish(900, 190));
        fish.add(new Fish(450, 80));

        time = new ArrayList<ObjectTime>();
        time.add(new ObjectTime(200, 400));
        time.add(new ObjectTime(250, 630));

        thorn = new ArrayList<Thorn>();
        thorn.add(new Thorn(500, 655));
        thorn.add(new Thorn(35, 150, 1));

        gate = new Gate(860, 65);

        box = new ArrayList<Box>();
        box.add(new Box(480, 310));
        box.add(new Box(550, 168));
        score = 0;

        timeCount = new Timer();

    }

    public void loadUserSavedGame(String address) {
        try {

            InputStream in = getClass().getResourceAsStream(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            score = Integer.parseInt(br.readLine());
            String delims = "\\s++";

            String line = br.readLine();
            String[] tokens = line.split(delims);
            c.setX(Integer.parseInt(tokens[0]));
            c.setY(Integer.parseInt(tokens[1]));

            int fishSize = Integer.parseInt(br.readLine());
            fish = new ArrayList<Fish>();
            for (int i = 0; i < fishSize; i++) {
                line = br.readLine();
                tokens = line.split(delims);
                fish.add(new Fish(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }

            int timeSize = Integer.parseInt(br.readLine());
            time = new ArrayList<ObjectTime>();
            for (int i = 0; i < timeSize; i++) {
                line = br.readLine();
                tokens = line.split(delims);
                time.add(new ObjectTime(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }

            int boxSize = Integer.parseInt(br.readLine());
            box = new ArrayList<Box>();
            for (int i = 0; i < boxSize; i++) {
                line = br.readLine();
                tokens = line.split(delims);
                box.add(new Box(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
            }
            timeCount.setCountDownTime(Integer.parseInt(br.readLine()));

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

            writer.println(fish.size());
            for (int i = 0; i < fish.size(); i++) {
                writer.println(fish.get(i).getX() + " " + fish.get(i).getY());
            }
            writer.println(time.size());
            for (int i = 0; i < time.size(); i++) {
                writer.println(time.get(i).getX() + " " + time.get(i).getY());
            }

            writer.println(box.size());
            for (int i = 0; i < box.size(); i++) {
                writer.println(box.get(i).getX() + " " + box.get(i).getY());
            }

            writer.println(timeCount.getCountDownTime());

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

    public Gate getGate() {
        return gate;
    }

    public void setScore(int score) {
        Map.score = score;
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

    public ArrayList<Box> getBox() {
        return box;
    }

    public Timer getTimeCount() {
        return timeCount;
    }

    public int getMapWidth() {
        return bg[1].getWidth();
    }

    public int getMapHeight() {
        return bg[1].getHeight();
    }

    public ArrayList<Lava> getLava() {
        return lava;
    }

    public ArrayList<Fish> getFish() {
        return fish;
    }

    public int getScore() {
        return score;
    }
}