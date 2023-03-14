package project;
import java.awt.Graphics;

public class GamePlay {
    private character c;
    private Map map=new Map();

    public GamePlay(GamePanel gamepanel){
        c = new character(gamepanel.getKey(),map);        
    }
    public void update(){
        map.update();
        c.update();
    }

    public void draw( Graphics g){
        map.draw(g);
        c.draw(g);
    }
}
