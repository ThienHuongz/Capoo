/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the Fish class that extends the object class in a Java game and is used to create fish objects.
*/
package project.entity;

public class Fish extends object {
    private static final int totalImageOfFish = 24;

    public Fish(int x, int y) {
        super(x, y, totalImageOfFish, "fish");

    }
}
