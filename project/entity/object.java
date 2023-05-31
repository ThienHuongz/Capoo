/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines an abstract object class that implements the Base interface in a Java game. 
 It represents a game object that can be rendered on screen and updated over time.
*/
package project.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import static project.gameState.GamePanel.getFPS;

import project.Base;
public abstract class object implements Base{
    private BufferedImage obj[] = new BufferedImage[30];
    private String objName, folderName;
    private int x, y, totalImage, animation;
    private int counterStep = 0, step = 0;


    public object(int x, int y){
        this.x = x;
        this.y = y;
    }
    public object(int x, int y, int totalImage, String objName) {
        this.x = x;
        this.y = y;
        this.objName = objName;
        this.folderName = objName;
        this.totalImage = totalImage;
        this.animation = (int) (getFPS() / totalImage) + 10;
        init();

    }

    // Overloading
    public object(int x, int y, int totalImage, String objName, String folderName) {
        this.x = x;
        this.y = y;
        this.objName = objName;
        this.folderName = folderName;
        this.totalImage = totalImage;
        this.animation = (int) (getFPS() / totalImage) + 10;
        init();

    }

    public void init() {
        try {
            for (int i = 0; i < totalImage; i++) {
                obj[i] = ImageIO.read(getClass()
                        .getResourceAsStream("/assets/" + folderName + "/" + objName + "_" + (i + 1) + ".png"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update() {
        counterStep++;
        if (counterStep > animation) {
            step++;
            counterStep = 0;
            if (step >= totalImage - 1) {
                step = 1;
            } else
                step++;
        } else
            counterStep++;
    }

    public void draw(Graphics g) {
        g.drawImage(obj[step], x, y, null);
    }

    public BufferedImage getImage() {
        return obj[step];
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

    public void setStep(int step) {
        this.step = step;
    }
}
