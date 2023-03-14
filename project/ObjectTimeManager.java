package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ObjectTimeManager extends object{
	
	GamePanel gp;
	private BufferedImage time[] = new BufferedImage[2];
	
	// private static final int totalImageOfTime = 16;
    // private static final int animation = (int)(GamePanel.getFPS()/totalImageOfTime)+10;
    // private int counterStep=0,step = 0;
	
	
	public ObjectTimeManager(int x, int y){
		super(x,y);
		getTimeImage();
	}
	
	public void getTimeImage() {
		try{
			// for (int i=0;i<totalImageOfTime;i++){
			// 	time[i] = ImageIO.read(getClass().getResourceAsStream("/assets/time/time_"+(i+1)+".png"));
			// }
				time[0] = ImageIO.read(getClass().getResourceAsStream("/assets/time/time_1.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g) {
//		g.drawImage(time[0], 40,70,null); 
//		g.drawImage(time[0], 700,600,null); 
//		g.drawImage(time[0], 200,400,null);
//		g.drawImage(time[0], 600,300,null);
//		g.drawImage(time[0], 200,200,null);
		g.drawImage(time[0], super.getX(), super.getY(), null);
	}
	
	public void update(){
        // counterStep++;
        // if (counterStep > animation){
        //     step++;
        //     counterStep=0;
        //     if (step >= totalImageOfTime-1 ){
        //         step=1;
        //     }
        //     else step++;
        // }
        // else counterStep++;   
    }
	
    public BufferedImage getImage(){
        return time[0];
    }
}
