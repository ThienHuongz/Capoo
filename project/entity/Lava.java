/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the Lava class that extends the object class in a Java game and is used to create Lava objects.
*/
package project.entity;

public class Lava extends object {
    private static final int totalImageOfLava = 18;

    public Lava(int x, int y) {
        super(x, y, totalImageOfLava, "lava");
    }

}
