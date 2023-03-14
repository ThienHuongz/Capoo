package project;
import javax.swing.*;
import java.awt.*;

public class GamePlay {
    private character c;
    private Map map=new Map();
    private GamePanel gamepanel;

    public GamePlay(GamePanel gamepanel){
        this.gamepanel=gamepanel;
        c = new character(gamepanel.getKey(),map);        

    }
    public void update(){
        c.update();
    }

    public void draw( Graphics g){
        map.draw(g);
        c.draw((Graphics2D) g);

    }
}
