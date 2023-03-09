package project;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Map {

    public BufferedImage bg[] = new BufferedImage[2];
    public Map() {
        init();
    }
    public void draw(Graphics g){
        g.drawImage(bg[0],0,0, null);
        g.drawImage(bg[1],0,0, null);
    }
    public void init(){
        try {
            bg[0]=ImageIO.read(getClass().getResourceAsStream("/assets/background.png"));
            bg[1]=ImageIO.read(getClass().getResourceAsStream("/assets/Background OOP.png"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }
    public BufferedImage getBackground(){
        return bg[1];
    } 

}
