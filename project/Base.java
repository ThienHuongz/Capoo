/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the Base interface in a Java project that likely serves as a template for different 
 game components such as game states, game objects, and game screens.
*/
package project;

public interface Base {
        void init();

        void draw(java.awt.Graphics g);

        void update();
}
