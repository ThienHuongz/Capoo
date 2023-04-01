package project.gameState;

import java.awt.Graphics;

import project.Map;
import project.entity.character;
import project.Base;

public class GamePlay implements Base{
    private character c;
    private Map map;

    public GamePlay(GamePanel gamepanel) {
        this.map = new Map();
        this.c = new character(gamepanel.getKey(), map);
    }
    public void init(){}
    public void update() {
        map.update();
        c.update();
    }

    public void draw(Graphics g) {
        map.draw(g);
        c.draw(g);
    }
}
