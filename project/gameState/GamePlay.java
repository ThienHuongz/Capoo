package project.gameState;

import java.awt.Graphics;
import java.io.File;

import project.Map;
import project.SoundEffect;
import project.collision;
import project.entity.Box;
import project.entity.character;
import project.Base;
import project.entity.object;

import java.util.ArrayList;

public class GamePlay implements Base {
    private character c;
    private Map map;
    public static int currentLevel = 1;

    public GamePlay(GamePanel gamepanel) {
        this.c = new character(gamepanel.getKey());
        this.map = new Map(c);

        File myObj = new File("assets/UserSavedGame/User1.map");
        if (myObj.isFile()) {
            map.loadUserSavedGame("../assets/UserSavedGame/User1.map");
        }
    }

    public void init() {
    }

    public void update() {
        // Check Collision of Object
        isCollision(c.getX(), c.getY(), c.getDirect());
        map.update();
        c.update();
    }

    public void draw(Graphics g) {
        map.draw(g);
        c.draw(g);

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

    // x y direction of character
    public void isCollision(int x, int y, String direct) {
        if (forLoopCollision(map.getLava(), x, y, 0) || forLoopCollision(map.getThorn(), x, y, 0)) {
            character.isDie = true;
            SoundEffect.play(5);
        }
        if (forLoopCollision(map.getFish(), x, y, 1)) {
            map.setScore(map.getScore() + 1);
            SoundEffect.play(5);
        }
        if (forLoopCollision(map.getTime(), x, y, 1)) {
            map.getTimeCount().countdownTime = map.getTimeCount().countdownTime + map.getTimeCount().plusSecond;
            SoundEffect.play(5);
        }
        if (collision.isCharacterCollisionObject(x, y, map.getGate().getImage(), map.getGate().getX(),
                map.getGate().getY())) {
            Map.checkTouch = true;
            SoundEffect.play(6);
        }

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

    public void SaveUserData(String address) {
        map.SaveUserData(address);
    }

    public void DeleteUserData(String address) {
        map.DeleteUserData(address);
    }
}
