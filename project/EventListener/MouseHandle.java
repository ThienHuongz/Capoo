package project.EventListener;

import java.awt.event.*;

import project.entity.character;
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
    public void mouseMove(){
    	if (gamepanel.mn != null) { gamepanel.mn.mouse_move(mx,my);}
            
        if(gamepanel.overState != null) {
        	gamepanel.overState.mouse_move(mx,my);
        }
    }

    public void mouseClick(){
            if (gamepanel.mn != null) { gamepanel.mn.mouse_click(mx,my);}
            
            if(gamepanel.overState != null) {
            	gamepanel.overState.mouse_click(mx,my);
            }
            if (gamepanel.levelState != null) {
                gamepanel.levelState.mouse_click(mx, my);
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
