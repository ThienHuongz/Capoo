package project;

import java.awt.image.BufferedImage;

public class collision {
    // public static boolean Right=false,Top=false,Left=false,Bottom=false;
    public static boolean isCollision(BufferedImage image1, int x1, int y1, BufferedImage image2, int x2, int y2) {
        // Calculate the bounds of the images
    //TOP LEFT X
        int image1X = x1;
    //TOP LEFT Y
        int image1Y = y1;
    // BOTTOM RIGHT X
        int image1Width = x1 + image1.getWidth();
    // BOTTOM RIGHT Y
        int image1Height = y1 + image1.getHeight();

        int image2X = x2;
        int image2Y = y2;
        int image2Width = x2 + image2.getWidth();
        int image2Height = y2 + image2.getHeight();

        // // Adjust the bounds based on image size
        // if (image1.getWidth() != image2.getWidth()) {
        //     if (image1.getWidth() < image2.getWidth()) {
        //         // adjust the width of image1 equal to height of image2
        //         image1Width = x1 + image1.getWidth() * (image2.getHeight() - y2) / image1.getHeight();
        //     } else {
        //         image2Width = x2 + image2.getWidth() * (image1.getHeight() - y1) / image2.getHeight();
        //     }
        // }
        // if (image1.getHeight() != image2.getHeight()) {
        //     if (image1.getHeight() < image2.getHeight()) {
        //         // adjust the height of image1 equal to width of image2
        //         image1Height = y1 + image1.getHeight() * (image2.getWidth() - x2) / image1.getWidth();
        //     } else {
        //         image2Height = y2 + image2.getHeight() * (image1.getWidth() - x1) / image2.getWidth();
        //     }
        // }

        // Check for intersection between the bounds
        if (image1X < image2Width &&
                image1Width > image2X &&
                image1Y < image2Height &&
                image1Height > image2Y) {
            // Calculate the intersection rectangle

            //TOP LEFT X Y
            int intersectionX = Math.max(image1X, image2X);
            int intersectionY = Math.max(image1Y, image2Y);
            // Width,height intersection rectangle
            int intersectionWidth = Math.min(image1Width, image2Width) - intersectionX;
            int intersectionHeight = Math.min(image1Height, image2Height) - intersectionY;
            System.out.println( intersectionWidth +" "+intersectionHeight);
    
            // Check for intersection between the images
            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    // vị trí giao nhau ở ảnh 1
                    int pixel1 = image1.getRGB(x - x1, y - y1);
                    // vị trí giao nhau ở ảnh 2
                    int pixel2 = image2.getRGB(x - x2, y - y2);
                    
                    // 1 int -> 4 bytes -> 32 bit
                    // |alpha| - | red |- |green| - |blue|
                    // |31 24|   |23 16|  |15  8|   |7  0|
                    // move right 24 bit then &0xff to keep only the last 8 bits -> get color alpha 
                    // &0xff to ensures that the resulting value is in the range of 0-255, which is the valid range for alpha values.

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
    public static boolean isCharacterCollision(BufferedImage image1, int x1, int y1, BufferedImage image2) {
        int intersectionX =  x1;
        int intersectionY = y1;
        int intersectionWidth = image1.getWidth()-10;
        int intersectionHeight = image1.getHeight()-5 ;
            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    int pixel2 = image2.getRGB(x, y);
                    if (((pixel2 >> 24) & 0xff) != 0) {
                        return true;
                    }
                }
            }
        
    
        return false;
    }
    public static boolean isCharacterCollisionA(BufferedImage image1, int x1, int y1, BufferedImage image2) {

        int intersectionX =  x1;
        int intersectionY = y1;

        int intersectionHeight = image1.getHeight()-5 ;

            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                    int pixel2 = image2.getRGB(intersectionX, y);
                    if (((pixel2 >> 24) & 0xff) != 0) {
                        return true;
                    
                }
            }
        
    
        return false;
    }
    public static boolean isCharacterCollisionD(BufferedImage image1, int x1, int y1, BufferedImage image2) {

        int intersectionY = y1;
        int intersectionWidth = image1.getWidth()-10;
        int intersectionHeight = image1.getHeight()-5 ;

            for (int y = intersectionY; y < intersectionY + intersectionHeight; y++) {
                    int pixel2 = image2.getRGB(x1 + intersectionWidth, y);
                    if (((pixel2 >> 24) & 0xff) != 0) {
                        return true;
                    }
            }
        return false;
    }
    public static boolean isCharacterCollisionJump(BufferedImage image1, int x1, int y1, BufferedImage image2) {

        int intersectionX =  x1;
        int intersectionWidth = image1.getWidth()-10;

            for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    int pixel2 = image2.getRGB(x, y1);

                    if ( ((pixel2 >> 24) & 0xff) != 0)  {
                        return true;
                    }
            }
        return false;
    }
    public static boolean isCharacterCollisionDown(BufferedImage image1, int x1, int y1, BufferedImage image2) {

        int intersectionX =  x1;
        int intersectionWidth = image1.getWidth()-10;
        int intersectionHeight = image1.getHeight()-5 ;

            for (int x = intersectionX; x < intersectionX + intersectionWidth; x++) {
                    int pixel1 = image2.getRGB(x, intersectionHeight);

                    if (((pixel1 >> 24) & 0xff) != 0 )  {
                        return true;
                    }
            }
        return false;
    }
}
