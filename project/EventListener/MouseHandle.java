/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the MouseHandle class in a Java game that implements the MouseAdapter interface. 
 The class is used for handling mouse events within the game.
*/
package project.EventListener;

import java.awt.event.*;

import project.gameState.GamePanel;

public class MouseHandle extends MouseAdapter {
    GamePanel gamepanel;

    private int mx, my;

    public MouseHandle(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void mousePress() {

    }

    public void mouseRelease() {

    }

    public void mouseMove() {
        gamepanel.getGameStateManager().mouse_move(mx, my);
    }

    public void mouseClick() {
        gamepanel.getGameStateManager().mouse_click(mx, my);
        if (!gamepanel.IsRun){
            gamepanel.mouse_click(mx,my);
        }

    }

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        mousePress();
    }

    public void mouseReleased(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        mouseRelease();

    }

    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        mouseMove();
    }

    public void mouseClicked(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        mouseClick();
    }

}
