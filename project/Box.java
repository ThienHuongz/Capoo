package project;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Box extends object {
    private BufferedImage image;
    private boolean isPushed;
    private String direction;
    
    public Box(int x, int y) {
        super(x, y);
        this.isPushed = false;
        init();
    }

    public void init() {
        try {
            image = ImageIO.read(new File("assets/testBox.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, super.getX(), super.getY(), null);
    }

    public Rectangle getBoundingBox() {
    	int width = image.getWidth();
        int height = image.getHeight();
    	
    	Rectangle solidArea = new Rectangle(super.getX() + 5, super.getY() + height - 10, width - 10, 10);
        
        return solidArea;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public void move(int dx, int dy) {
    	int newX = this.getX() + dx;
        int newY = this.getY() + dy;
        
        this.setX(newX);
        this.setY(newY);
    }
    
    public boolean isPushed() {
        return isPushed;
    }

    public void setPushed(boolean pushed) {
        isPushed = pushed;
    }
    
    public String getDirection() {
    	return direction;
    }
    
    public void setDirection(String direct) {
    	direction = direct;
    }
    
    public void update(){
    	//THE DIRECTION AND MOVE THE BOX 
    	if (direction == "right") {
    		move(3, 0);
    	}
    	
    	if (direction == "left") {
    		move(-3, 0);
    	}
    	
    	if (direction == "down") {
    		
    	}
    	
    	if (direction == "up") {
    		
    	}
    }
}