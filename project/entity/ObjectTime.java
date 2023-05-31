/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the ObjectTime class that extends the object class in a Java game and is used 
 to create ObjectTime objects.
*/
package project.entity;

public class ObjectTime extends object {
	private static final int totalImageOfObjectTime = 1;

	public ObjectTime(int x, int y) {
		super(x, y, totalImageOfObjectTime, "time");
	}

}
