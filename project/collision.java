package project;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import project.entity.character;

public class collision {

    public static BufferedImage character = new character().getImage();
    public static BufferedImage bg = new Map().getBackground();

    public static boolean isCollision(BufferedImage image1, int x1, int y1, BufferedImage image2, int x2, int y2) {
        // Calculate the bounds of the images
        // TOP LEFT X
        int image1X = x1;
        // TOP LEFT Y
        int image1Y = y1;
        // BOTTOM RIGHT X
        int image1Width = x1 + image1.getWidth();
        // BOTTOM RIGHT Y
        int image1Height = y1 + image1.getHeight();

        int image2X = x2;
        int image2Y = y2;
        int image2Width = x2 + image2.getWidth();
        int image2Height = y2 + image2.getHeight();

        // Check for intersection between the bounds
        if (image1X < image2Width &&
                image1Width > image2X &&
                image1Y < image2Height &&
                image1Height > image2Y) {
            // Calculate the intersection rectangle

            // TOP LEFT X Y
            int intersectionX = Math.max(image1X, image2X);
            int intersectionY = Math.max(image1Y, image2Y);
            // Width,height intersection rectangle
            int intersectionWidth = Math.min(image1Width, image2Width) - intersectionX;
            int intersectionHeight = Math.min(image1Height, image2Height) - intersectionY;
            System.out.println(intersectionWidth + " " + intersectionHeight);

            // Check for intersection between the images
            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    // vị trí giao nhau ở ảnh 1
                    int pixel1 = image1.getRGB(x - x1, y - y1);
                    // vị trí giao nhau ở ảnh 2
                    int pixel2 = image2.getRGB(x - x2, y - y2);

                    // 1 int -> 4 bytes -> 32 bit
                    // |alpha| - | red |- |green| - |blue|
                    // |31 24| |23 16| |15 8| |7 0|
                    // move right 24 bit then &0xff to keep only the last 8 bits -> get color alpha
                    // &0xff to ensures that the resulting value is in the range of 0-255, which is
                    // the valid range for alpha values.

                    // Alpha - trong suốt = 0
                    // nếu kp alpha -> colision -> true
                    if (((pixel1 >> 24) & 0xff) != 0 && ((pixel2 >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean isCharacterCollision(int x1, int y1, String direct) {
        int intersectionX = x1;
        int intersectionY = y1;
        int intersectionHeight = character.getHeight() - 10;
        int intersectionWidth = character.getWidth() - 10;
        int pixel;

        switch (direct) {
            case "left":
                for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                    pixel = bg.getRGB(intersectionX, y);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
            case "left-up":
                pixel = bg.getRGB(intersectionX - 2, y1 + (character.getHeight() / 2));
                if ((((pixel >> 24) & 0xff) != 0)) {
                    return true;
                }
                break;
            case "right":
                for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                    pixel = bg.getRGB(x1 + intersectionWidth, y);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
            case "right-up":
                pixel = bg.getRGB(intersectionX + character.getWidth() + 5, y1);
                if (((pixel >> 24) & 0xff) != 0) {
                    return true;
                }
                break;
            case "jump":
                intersectionX = x1 + 10;
                intersectionWidth = character.getWidth() - 30;

                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    pixel = bg.getRGB(x, y1 + 20);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
            case "down":
                intersectionX = x1 + 10;
                intersectionWidth = character.getWidth() - 20;
                intersectionHeight = character.getHeight() - 5;

                for (int x = intersectionX; x < intersectionX + intersectionWidth - 10; x++) {
                    pixel = bg.getRGB(x, y1 + intersectionHeight);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public static boolean isCharacterCollisionObject(int x1, int y1, BufferedImage image2, int x2, int y2) {
        int image1X = x1 + 20;
        int image1Y = y1;
        int image1Width = x1 + character.getWidth() - 20;
        int image1Height = y1 + character.getHeight();

        int image2X = x2;
        int image2Y = y2;
        int image2Width = x2 + image2.getWidth();
        int image2Height = y2 + image2.getHeight() - 22;

        if (image1X < image2Width &&
                image1Width > image2X &&
                image1Y < image2Height &&
                image1Height > image2Y) {

            int intersectionX = Math.max(image1X, image2X);
            int intersectionY = Math.max(image1Y, image2Y);

            int intersectionWidth = Math.min(image1Width, image2Width) - intersectionX;
            int intersectionHeight = Math.min(image1Height, image2Height) - intersectionY;

            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    int pixel1 = character.getRGB(x - x1, y - y1);
                    int pixel2 = image2.getRGB(x - x2, y - y2);
                    if (((pixel1 >> 24) & 0xff) != 0 && ((pixel2 >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCharacterCollisionBox(int x1, int y1, String direct, BufferedImage image2, int x2, int y2) {
        switch (direct) {
            case "left":
                if (new Rectangle(x1, y1, character.getWidth() / 2, character.getHeight())
                        .intersects(new Rectangle(x2 + image2.getWidth() - 3, y2 + 5, 5, image2.getHeight()))) {

                    return true;
                }
                break;
            case "right":
                if (new Rectangle(x1, y1, character.getWidth(), character.getHeight())
                        .intersects(new Rectangle(x2, y2 + 5, 1, image2.getHeight()))) {
                    return true;
                }
                break;
            // case "jump":
            // if (new Rectangle(x1, y1 + character.getHeight() - 1, character.getWidth(),
            // 1)
            // .intersects(new Rectangle(x2+1, y2, 1, 1))) {
            // return true;
            // }
            // break;
            case "down":
                if (new Rectangle(x1, y1 + character.getHeight() - 3, character.getWidth() - 3, 3)
                        .intersects(new Rectangle(x2, y2, image2.getWidth(), 1))) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static boolean isBoxCollisionBg(String direct, BufferedImage image2, int x2, int y2) {
        int intersectionX = x2;
        int intersectionY = y2;
        int intersectionHeight = image2.getHeight();
        int intersectionWidth = image2.getWidth();
        int pixel;
        switch (direct) {
            case "left":
                for (int y = intersectionY; y < intersectionY + intersectionHeight - 5; y++) {
                    pixel = bg.getRGB(intersectionX, y);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
            case "right":
                for (int y = intersectionY; y < intersectionY + intersectionHeight - 5; y++) {
                    pixel = bg.getRGB(x2 + intersectionWidth, y);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
            case "down":
                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    pixel = bg.getRGB(x, y2 + intersectionHeight);
                    if (((pixel >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
                break;
        }
        return false;

    }

}
