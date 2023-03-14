package project;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private BufferedImage bg[] = new BufferedImage[2];
    private ArrayList<Lava> lava= new ArrayList<Lava>();
    private ArrayList<Fish> fish= new ArrayList<Fish>();
    private ArrayList<Box> box = new ArrayList<Box>(); 
    private int score=0;

    public Map() {
        init();
    }
    
    public void draw(Graphics g){
    	
        g.drawImage(bg[0],0,0, null);
        for (int i=0;i<lava.size();i++){
            lava.get(i).draw(g);
        }
        
        g.drawImage(bg[1],0,0, null);
        for (int i=0;i<fish.size();i++){
            fish.get(i).draw(g);
        }

        g.drawImage(bg[1],0,0, null);
        for (int i=0;i<box.size();i++){
            box.get(i).draw(g);
        }
        
    }
    public void update (){
        for (int i=0;i<lava.size();i++){
            lava.get(i).update();
        }
        for (int i=0;i<fish.size();i++){
            fish.get(i).update();
        }
        for (int i=0;i<box.size();i++){
            box.get(i).update();
        }
    }
    public void init(){
        try {
            bg[0]=ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            bg[1]=ImageIO.read(getClass().getResourceAsStream("../assets/Background OOP1.png"));

            lava.add(new Lava(609,522));
            lava.add(new Lava(500,134));

            fish.add(new Fish(500,480));
            fish.add(new Fish(450,80));

            box.add(new Box(200, 215));
            box.add(new Box(609, 625));
            box.add(new Box(700, 335));
            
        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }
    public BufferedImage getBackground(){
        return bg[1];
    } 
    public boolean isCollision(int x, int y){
    	String direct = null;
    	
        for (int i=0;i<lava.size();i++){
            if (collision.isCharacterCollisionObject(x,y,lava.get(i).getImage(),lava.get(i).getX(),lava.get(i).getY())){
                System.out.println("true"); 
                return true;
            }
            for (int j=0;j<fish.size();j++){
                if (collision.isCharacterCollisionObject(x,y,fish.get(j).getImage(),fish.get(j).getX(),fish.get(j).getY())){
                	System.out.println("ate fish");
                	score++;
                    fish.remove(j);
                    return true;
                }
            }
        }
        for (int i=0;i<box.size();i++){
        	if (collision.isCharacterCollisionObjectBox(x,y, box.get(i).getImage(),box.get(i).getX(), box.get(i).getY())){
        		 int boxCenterX = box.get(i).getX() + box.get(i).getImage().getWidth() / 2;
        		    int charCenterX = x + box.get(i).getImage().getWidth() / 2; // Use box width
        		    int boxCenterY = box.get(i).getY() + box.get(i).getImage().getHeight() / 2;
        		    int charCenterY = y + box.get(i).getImage().getHeight() / 2; // Use box height

        		    int deltaX = boxCenterX - charCenterX;
        		    int deltaY = boxCenterY - charCenterY;

        		    if (Math.abs(deltaX) > Math.abs(deltaY)) {
        		        if (deltaX > 0) {
        		        	box.get(i).setDirection("right");
        		            System.out.println("right");
        		            return true;
        		        } else {
        		        	box.get(i).setDirection("left");
        		            System.out.println("left");
        		            return true;
        		        }
        		    } else {
        		        if (deltaY > 0) {
        		        	box.get(i).setDirection("down");
        		            System.out.println("down");
        		            return true;
        		        } else {
        		        	box.get(i).setDirection("up");
        		            System.out.println("up");
        		            return true;
        		        }
        		    }
        	}
        }
		return false;
    }
    
    public int getMapWidth(){
        return bg[1].getWidth();
    }
    public int getMapHeight(){
        return bg[1].getHeight(); 
    }
}
