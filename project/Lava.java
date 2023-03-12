package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Lava implements object {
    private BufferedImage lava[] = new BufferedImage[20];

    private static final int totalImageOfLava = 18;
    private static final int animation = (int)(GamePanel.getFPS()/(int) (totalImageOfLava-3));

    private int counterStep=0,step = 0;
    public Lava(){

    }
    public void init(){
        try {

            for (int i=0;i<totalImageOfLava;i++){
                lava[i]=ImageIO.read(getClass().getResourceAsStream("../assets/lava/lava_sprite_0"+i+".png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g){
        g.drawImage(lava[step], 100, 200, null);

    }
    public void update(){
        counterStep++;
        if (counterStep > animation){
            step++;
            counterStep=0;
            if (step >= totalImageOfLava ){
                step=0;
            }
            else step++;
        }
        else counterStep++;
        
    }
}
