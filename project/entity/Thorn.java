package project.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import project.gameState.GamePanel;

public class Thorn extends object{
    private BufferedImage thorn[] = new BufferedImage[2];
    private int type;

    public Thorn(int x,int y,int type){
        super(x,y);
        this.type=type;
        init();


    }
    public void init(){
        try {
            thorn[0]=ImageIO.read(getClass().getResourceAsStream("../../assets/thorn/thorn_1.png"));
            thorn[1]=ImageIO.read(getClass().getResourceAsStream("../../assets/thorn/thorn_2.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics g){
        // g.drawImage(thorn[step], 609, 522, null);
        if(this.type == 2) g.drawImage(thorn[0], super.getX(), super.getY(), null);
        else g.drawImage(thorn[1], super.getX(), super.getY(), null);

    }
    public void update(){
   
    }
    public BufferedImage getImage(){
        return thorn[0];
    }

}
