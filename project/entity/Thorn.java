/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the Thorn class that extends the object class in a Java game and is used to create Thorn objects.
*/
package project.entity;

public class Thorn extends object {
    private static final int totalImageOfThorn = 1;

    public Thorn(int x, int y) {
        super(x, y, totalImageOfThorn, "thorn");
    }

    public Thorn(int x, int y, int type) {
        super(x, y, totalImageOfThorn, "thorn2", "thorn");
    }

}
