package project;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class character {
    private int x,y, speed,step=0,counterStep=0;
    private KeyHandle key;
    private BufferedImage b[] = new BufferedImage[7];
    private boolean isRight=true,isJump=false;
    
    public character(KeyHandle key){
        x=100;
        y=100;
        speed=3;
        this.key=key;
        init();
    }
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void init(){
        try {
            for (int i=0;i<7;i++){
                b[i]=ImageIO.read(getClass().getResourceAsStream("../assets/walk_"+i+".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g){
        if (isRight == true){
            // reverse image
            g.drawImage(b[step], x+b[step].getWidth(), y,-b[step].getWidth(),b[step].getHeight() ,null);
        }
        else{
            g.drawImage(b[step], x, y, null);
        }

    }
    public void update(){
        if (key.isKeyA() == true){
            isRight=false;
            x-=speed;
        }
        if (key.isKeyW() == true){
            if (isJump == false) isJump=true;
            y-=speed;
        }
        if (key.isKeyS() == true){
            y+=speed;
        }
        if (key.isKeyD() == true){
            isRight=true;
            x+=speed;
        }
        if (key.isKeySpace() == true){
            if (isJump == false) isJump=true;
            y-=speed;
        }
        counterStep++;
        if (counterStep >10){
            step++;
            counterStep=0;
            if (step >=7){
                step=0;
            }
            else step++;
        }
        else counterStep++;
    }
}
