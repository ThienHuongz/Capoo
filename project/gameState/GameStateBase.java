/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: 
This code defines the interface GameStateBase in a Java game that provides a template for implementing the 
different game states such as the game menu, game over screen, and gameplay.
*/
package project.gameState;
import project.Base;
public interface GameStateBase extends Base{

    void mouse_click(int mx, int my);
    void mouse_move(int mx, int my);
    
}
