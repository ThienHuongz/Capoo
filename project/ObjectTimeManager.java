package project;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ObjectTimeManager {
	
	GamePanel gp;
	public BufferedImage time[] = new BufferedImage[1];
	
	ObjectTimeManager(){
		getTimeImage();
	}
	
	public void getTimeImage() {
		try{
			
			time[0] = ImageIO.read(getClass().getResourceAsStream("/assets/time/timePlus_sprite_01.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(time[0], 40,70,null); 
		g.drawImage(time[0], 700,600,null); 
		g.drawImage(time[0], 200,400,null);
		g.drawImage(time[0], 600,300,null);
		g.drawImage(time[0], 200,200,null);
	}
}
