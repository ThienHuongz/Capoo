/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the Gate class that extends the object class in a Java game and is used to create Gate objects.
*/
package project.entity;

public class Gate extends object {
    private static final int totalImageOfGate = 2;
//    public boolean checkTouch = false;

    public Gate(int x, int y) {
        super(x, y, totalImageOfGate, "gate");
    }

}
