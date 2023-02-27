package project;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Map {
    private int width, height;
    private boolean[][] walkable;
    private BufferedImage bg;
    public Map() {
        init();
    }
    public void draw(Graphics g){
        g.drawImage(bg,0,0, null);
    }
    public void init(){
        try {
            bg=ImageIO.read(getClass().getResourceAsStream("../assets/background.png"));
            width = bg.getWidth();
            height = bg.getHeight();
            walkable = new boolean[width][height];

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    Color color = new Color(bg.getRGB(x, y));
                    boolean isWalkable =  color.getAlpha() != 0;
                    walkable[x][y]=isWalkable;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading map from file: " + e.getMessage());
        }
    }
    public void setWalkable(int x, int y, boolean isWalkable) {
        walkable[x][y] = isWalkable;
    }

    public boolean isWalkable(int x, int y) {
        if (x < 0 || x >= walkable.length || y < 0 || y >= walkable[0].length) {
            return false;
        }
        return walkable[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    
}
