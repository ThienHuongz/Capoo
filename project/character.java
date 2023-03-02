package project;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
public class character implements object{
    private int x,y, speed,step=0,counterStep=0, d=0;
    private KeyHandle key;
    private BufferedImage walk[] = new BufferedImage[7],jump[] = new BufferedImage[7];
    private boolean isRight=true,isJump=false,checkJump=true;
    private Map map;

    private static final int totalImageOfCharacter=7;
    private static final int animation = (int)(GamePanel.getFPS()/(int) (totalImageOfCharacter-3));

    public character(KeyHandle key, Map map){
        x=100;
        y=500;
        speed=3;
        this.key=key;
        this.map=map;
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
                walk[i]=ImageIO.read(getClass().getResourceAsStream("../assets/walk_"+i+".png"));
                jump[i]=ImageIO.read(getClass().getResourceAsStream("../assets/jump"+i+".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g){
        if (isRight == true){
            // reverse image
            if (isJump){
                g.drawImage(jump[step], x+jump[step].getWidth(), y,-jump[step].getWidth(),jump[step].getHeight() ,null);
            }
            else{
                g.drawImage(walk[step], x+walk[step].getWidth(), y,-walk[step].getWidth(),walk[step].getHeight() ,null);
            }
        }
        else{
            if (isJump){
                g.drawImage(jump[step], x, y, null);
            }
            else{
                g.drawImage(walk[step], x, y, null);
            }
        }

    }
    public void update(){
        
        if (key.isKeyA() == true || key.isKeyW() == true || key.isKeyS() == true || key.isKeyD() == true || key.isKeySpace() == true || isJump){

            if (key.isKeyA() == true ){
                if (!(collision.isCharacterCollisionA(walk[0],x-speed,y,map.bg[1]))){
                    isRight=false;
                    x-=speed;
                }
                
            }
            if ((key.isKeyW() == true || key.isKeySpace() == true ) ){
                if (isJump == false) isJump=true;
            }
            if (key.isKeyS() == true){
                // if (!collision.isCharacterCollision(walk[0],x,y+speed,map.bg[1])){
                    y+=speed;
                // }
            }
            // if (!collision.isCharacterCollision(walk[0],x,y,map.bg[1])){
            //     y+=speed;
            // }
            if (key.isKeyD() == true ){
                if (!(collision.isCharacterCollisionD(walk[0],x+speed,y,map.bg[1]))){
                    isRight=true;
                    x+=speed;
                }
            }
            if (isJump && d<30 && checkJump){
                if (!(collision.isCharacterCollisionJump(walk[0],x,y-speed+20,map.bg[1]))){
                    d++;
                    y-=speed;
                }
                else{
                    checkJump=false;
                }
            }
            else if (isJump && d<60 && checkJump){
                d++;
                y+=speed;
            }
            else if (d>=50){
                isJump=false;
                d=0;
            }
            if (checkJump == false && d>0){
                if (!(collision.isCharacterCollisionDown(walk[0],x,y+speed,map.bg[1]))){
                    d--;
                    y+=speed;
                }
                else{
                    d=0;
                }
            }
            else if (checkJump == false && d==0){
                isJump=false;
                checkJump=true;
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
}
