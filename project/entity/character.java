package project.entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.awt.Graphics;

import project.gameState.GamePanel;

import project.Base;
import project.SoundEffect;
import project.collision;
import project.game;
import project.EventListener.KeyHandle;

public class character implements Base {
    private int x, y, speed, step = 0, counterStep = 0, d = 0;
    private KeyHandle key;
    private BufferedImage walk[] = new BufferedImage[7], jump[] = new BufferedImage[7], die[] = new BufferedImage[7],
            stun;
    private boolean isRight = true, isJump = false;
    private String direct = "";
    public static boolean isDie = false, isStun = false, isCollisionBox = false, isCollisionBoxDown = false;

    private static final int totalImageOfCharacter = 7;
    private static final int animation = (int) (GamePanel.getFPS() / (totalImageOfCharacter - 3));
    private static final int gravity = 3;
    private static final int heightOfJump = 25; // 30*speed = 30 * 3 = 90

    public character() {
        init();
    }

    public character(KeyHandle key) {
        x = 100;
        y = 530;
        speed = 4;
        this.key = key;
        init();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < game.getScreenWidth()) {
            this.x = x;
        }
    }

    public void setY(int y) {
        if (y < game.getScreenHeight()) {
            this.y = y;
        }
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getImage() {
        return walk[0];
    }

    public String getDirect() {
        return direct;
    }

    public void setIsCollisionBox(boolean check) {
        this.isCollisionBox = check;
    }

    public void init() {
        try {
            for (int i = 0; i < totalImageOfCharacter; i++) {
                walk[i] = ImageIO.read(getClass().getResourceAsStream("../../assets/walk_" + i + ".png"));
                jump[i] = ImageIO.read(getClass().getResourceAsStream("../../assets/jump" + i + ".png"));
                die[i] = ImageIO.read(getClass().getResourceAsStream("../../assets/die" + i + ".png"));

            }
            stun = ImageIO.read(getClass().getResourceAsStream("../../assets/stun.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (isRight == true) {
            if (isDie) {
                g.drawImage(stun, x + 10, y - 45, null);
                g.drawImage(die[step], x + die[step].getWidth(), y, -die[step].getWidth(), die[step].getHeight(), null);
            }
            // reverse image
            else if (isJump) {
                g.drawImage(jump[step], x + jump[step].getWidth(), y, -jump[step].getWidth(), jump[step].getHeight(),
                        null);
            } else {
                g.drawImage(walk[step], x + walk[step].getWidth(), y, -walk[step].getWidth(), walk[step].getHeight(),
                        null);
            }
        } else {
            if (isDie) {
                g.drawImage(stun, x + 10, y - 45, null);

                g.drawImage(die[step], x, y, null);
            } else if (isJump) {
                g.drawImage(jump[step], x, y, null);
            } else {
                g.drawImage(walk[step], x, y, null);
            }
        }

    }

    public void setDirection() {
        direct = "";
        if (key.isKeyA()) {
            direct = "left";
        }
        if (key.isKeyD()) {
            direct = "right";
        }
    }

    public void update() {
        setDirection();
        if (!isCollisionBox) {
            if (key.isKeyA() == true || key.isKeyW() == true || key.isKeyS() == true || key.isKeyD() == true
                    || key.isKeySpace() == true || isJump) {
                // MOVE LEFT
                if (key.isKeyA() == true) {
                    if (!(collision.isCharacterCollision(x - speed, y, "left"))) {
                        setX(x - speed);

                    }
                    // MOVE LEFT UP
                    else if (!(collision.isCharacterCollision(x, y, "left-up"))
                            && (collision.isCharacterCollision(x, y, "down"))) {
                        setX(x - speed);
                        setY(y - speed);
                    }
                    isRight = false;
                }
                // SET JUMP
                if ((key.isKeyW() == true || key.isKeySpace() == true)) {
                    if (isJump == false && ((collision.isCharacterCollision(x, y, "down")) || isCollisionBoxDown)) {
                        // direct="jump";
                        isJump = true;
                        SoundEffect.play(0);
                    }
                }
                // MOVE RIGHT
                if (key.isKeyD() == true) {
                    if (!(collision.isCharacterCollision(x + speed, y, "right"))) {
                        setX(x + speed);
                    }
                    // MOVE RIGHT UP
                    else if (!(collision.isCharacterCollision(x, y, "right-up"))
                            && (collision.isCharacterCollision(x, y, "down"))) {
                        setX(x + speed);
                        setY(y - speed);
                    }
                    isRight = true;
                }
                // JUMP UP
                if (isJump && d < heightOfJump && (!(collision.isCharacterCollision(x, y - speed, "jump")))) {
                    d++;
                    setY(y - speed);
                } else {
                    d = 0;
                    isJump = false;
                }

                // CHARACTER ANIMATION
                counterStep++;
                if (counterStep > animation) {
                    step++;
                    counterStep = 0;
                    if (step >= totalImageOfCharacter) {
                        step = 0;
                    } else
                        step++;
                } else
                    counterStep++;
            }
        }
        // GRAVITY
        if (!isJump) {
            if (!(collision.isCharacterCollision(x, y, "down")) && !isCollisionBoxDown) {
                setY(y + gravity);
            }
        }

    }
}
