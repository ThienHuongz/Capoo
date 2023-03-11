package project;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
public class MenuState {
    private BufferedImage mn[] = new BufferedImage[10];

    public MenuState(){
        init();
    }

    public void init(){
        try {
            mn[0]=ImageIO.read(getClass().getResourceAsStream("../assets/start1.png"));
            mn[1]=ImageIO.read(getClass().getResourceAsStream("../assets/start2.png"));
            mn[2]=ImageIO.read(getClass().getResourceAsStream("../assets/exit1.png"));
            mn[3]=ImageIO.read(getClass().getResourceAsStream("../assets/exit2.png"));
            mn[4]=ImageIO.read(getClass().getResourceAsStream("../assets/menu.jpg"));

        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }
    public void draw(Graphics g){
        g.drawImage(mn[4],0,0, null);
        g.drawImage(mn[0],350,300, null);
        g.drawImage(mn[1],350,420, null);
        g.drawImage(mn[2],350,540, null);


    }
}
