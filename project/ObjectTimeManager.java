package project;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ObjectTimeManager {
	
	GamePanel gp;
	private BufferedImage time[] = new BufferedImage[20];
	
	private static final int totalImageOfTime = 16;
    private static final int animation = (int)(GamePanel.getFPS()/totalImageOfTime)+10;
    private int counterStep=0,step = 0;
	
	
	ObjectTimeManager(int x, int y){
		super(x,y);
		getTimeImage();
	}
	
	public void getTimeImage() {
		try{
			for (int i=0;i<totalImageOfTime;i++){
				time[i] = ImageIO.read(getClass().getResourceAsStream("/assets/time/time_"+(i+1)+".png"));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
//		g.drawImage(time[0], 40,70,null); 
//		g.drawImage(time[0], 700,600,null); 
//		g.drawImage(time[0], 200,400,null);
//		g.drawImage(time[0], 600,300,null);
//		g.drawImage(time[0], 200,200,null);
		g.drawImage(time[step], super.getX(), super.getY(), null);
	}
	
	public void update(){
        counterStep++;
        if (counterStep > animation){
            step++;
            counterStep=0;
            if (step >= totalImageOfTime-1 ){
                step=1;
            }
            else step++;
        }
        else counterStep++;   
    }
	
    public BufferedImage getImage(){
        return time[step];
    }
}
