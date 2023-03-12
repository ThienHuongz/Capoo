package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Lava extends object{
    private BufferedImage lava[] = new BufferedImage[20];

    private static final int totalImageOfLava = 18;
    private static final int animation = (int)(GamePanel.getFPS()/totalImageOfLava)+10;
    private int counterStep=0,step = 0;

    public Lava(int x,int y){
        super(x,y);
        init();


    }
    public void init(){
        try {
            for (int i=0;i<totalImageOfLava;i++){
                lava[i]=ImageIO.read(getClass().getResourceAsStream("../assets/lava/lava_"+(i+1)+".png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g){
        // g.drawImage(lava[step], 609, 522, null);
        // g.drawImage(lava[step], 500, 134, null);
        g.drawImage(lava[step], super.getX(), super.getY(), null);

    }
    public void update(){
        counterStep++;
        if (counterStep > animation){
            step++;
            counterStep=0;
            if (step >= totalImageOfLava-1 ){
                step=1;
            }
            else step++;
        }
        else counterStep++;   
    }
    public BufferedImage getImage(){
        return lava[step];
    }

}
