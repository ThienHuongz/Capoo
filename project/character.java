package project;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
public class character {
    private int x,y, speed,step=0,counterStep=0, d=0;
    private KeyHandle key;
    private BufferedImage b[] = new BufferedImage[7];
    private boolean isRight=true,isJump=false;

    private static final int totalImageOfCharacter=7;
    private static final int animation = (int)(GamePanel.getFPS()/ totalImageOfCharacter);

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
            for (int i=0;i<totalImageOfCharacter;i++){
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
        if (key.isKeyW() == true || key.isKeySpace() == true){
            if (isJump == false) isJump=true;
        }
        if (key.isKeyS() == true){
            y+=speed;
        }
        if (key.isKeyD() == true){
            isRight=true;
            x+=speed;
        }

        if (isJump && d<20){
            d++;
            y-=speed;
        }
        else if (isJump && d<40){
            d++;
            y+=speed;
        }
        else {
            isJump=false;
            d=0;
        }

        counterStep++;
        if (counterStep > animation){
            step++;
            counterStep=0;
            if (step >= totalImageOfCharacter ){
                step=0;
            }
            else step++;
        }
        else counterStep++;
    }
}
