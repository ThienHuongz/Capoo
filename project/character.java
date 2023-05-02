package project;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class character {
    private int x;
    private int y;
    private int speed;
    private int step = 0;
    private int counterStep = 0;
    private int d = 0;
    private KeyHandle key;
    private BufferedImage walk[] = new BufferedImage[7], jump[] = new BufferedImage[7];
    private boolean isRight = true, isJump = false, checkJump = true;
    private Map map;
    private SoundEffect sound = new SoundEffect();

    private static final int totalImageOfCharacter = 7;
    private static final int animation = (int) (GamePanel.getFPS() / (int) (totalImageOfCharacter - 3));
    private static final int gravity = 3;
    private static final int heightOfJump = 35; // 30*speed = 30 * 3 = 90

    public character() {
        init();
    }

    public character(KeyHandle key, Map map) {
        x = 100;
        y = 100;
        speed = 3;
        this.key = key;
        this.map = map;
        init();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < map.getMapWidth()) {
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < map.getMapHeight()) {
            this.y = y;
        }
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getImageCharacter() {
        return walk[0];
    }

    public void init() {
        try {
            for (int i = 0; i < totalImageOfCharacter; i++) {
                walk[i] = ImageIO.read(getClass().getResourceAsStream("../assets/walk_" + i + ".png"));
                jump[i] = ImageIO.read(getClass().getResourceAsStream("../assets/jump" + i + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (isRight == true) {
            // reverse image
            if (isJump) {
                g.drawImage(jump[step], x + jump[step].getWidth(), y, -jump[step].getWidth(), jump[step].getHeight(),
                        null);
            } else {
                g.drawImage(walk[step], x + walk[step].getWidth(), y, -walk[step].getWidth(), walk[step].getHeight(),
                        null);
            }
        } else {
            if (isJump) {
                g.drawImage(jump[step], x, y, null);
            } else {
                g.drawImage(walk[step], x, y, null);
            }
        }

    }

    public void update() {

        if (key.isKeyA() == true || key.isKeyW() == true || key.isKeyS() == true || key.isKeyD() == true
                || key.isKeySpace() == true || isJump) {
            if (key.isKeyA() == true) {
                if (!(collision.isCharacterCollision(x - speed, y, "left"))) {
                    setX(x - speed);
                } else if (!(collision.isCharacterCollision(x, y, "left-up"))
                        && (collision.isCharacterCollision(x, y, "down"))) {
                    setX(x - speed);
                    setY(y - speed);
                }
                map.checkCollisionBox(x, y, "left");
                isRight = false;
            }

            if ((key.isKeyW() == true || key.isKeySpace() == true)) {
                if (isJump == false && (collision.isCharacterCollision(x, y, "down"))
                        || isJump == false && map.standingOnBox == 1) {
                    isJump = true;
                    sound.SetClip(0);
                    sound.play();
                }
                map.checkCollisionBox(x, y, "up");
            }
            if (key.isKeyD() == true) {
                if (!(collision.isCharacterCollision(x + speed, y, "right"))) {
                    setX(x + speed);
                } else if (!(collision.isCharacterCollision(x, y, "right-up"))
                        && (collision.isCharacterCollision(x, y, "down"))) {
                    setX(x + speed);
                    setY(y - speed);
                }
                map.checkCollisionBox(x, y, "right");
                isRight = true;
            }
            if (isJump && d < heightOfJump && checkJump) {
                if (!(collision.isCharacterCollision(x, y - speed, "jump"))) {
                    d++;
                    setY(y - speed);
                } else {
                    checkJump = false;
                }
            } else if (isJump && d < (heightOfJump * 2) && checkJump) {
                if (!(collision.isCharacterCollision(x, y, "up"))) {
                    d++;
                    setY(y + speed);
                } else {
                    d = 0;
                    isJump = false;
                }
            } else if (d >= (heightOfJump * 2)) {
                isJump = false;
                d = 0;
            }
            if (checkJump == false && d > 0) {
                if (!(collision.isCharacterCollision(x, y + speed, "down"))) {
                    d--;
                    setY(y + speed);
                } else {
                    d = 0;
                }
            } else if (checkJump == false && d == 0) {
                isJump = false;
                checkJump = true;
            }

            // CHARACTER IMG
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

        // GRAVITY
        if (!isJump) {
            if (!(collision.isCharacterCollision(x, y, "down"))) {
                setY(y + gravity);
            }
        }

        // Standing on the box
        if (map.standingOnBox == 1 && isJump == true) {
            setY(y - speed);
        }

        if (map.standingOnBox == 1) {
            setY(map.boxUpper);
        }

        if (map.isCollision(x, y)) {
            sound.SetClip(2);
            sound.play();
        }

    }
}
