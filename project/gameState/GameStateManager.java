/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: This code defines the GameStateManager class in a Java game that manages the different game states such 
 as the game menu, game over screen, and gameplay.
*/
package project.gameState;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameStateManager {
    
    private ArrayList<GameStateBase> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int LEVELSTATE = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVERSTATE = 3;
    public static final int GAMEWINNERSTATE = 4;

    public GameStateManager(GamePanel pn){
        gameStates = new ArrayList<GameStateBase>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(pn));
        gameStates.add(new LevelState(pn));
        gameStates.add(new GamePlay(pn));
        gameStates.add(new GameOverState(pn));
        gameStates.add(new WinnerState(pn));
    }

    public void setState(int state){
        currentState = state;
        gameStates.get(currentState).init();
    }
    public int getCurrentState(){
        return currentState;
    }
    public GamePlay getGamePlay(){
        return (GamePlay) gameStates.get(2);
    }
    public void update(){
        gameStates.get(currentState).update();
    }
    public void draw(Graphics g){
        gameStates.get(currentState).draw(g);
    }

    public void mouse_click(int mx,int my){
        gameStates.get(currentState).mouse_click(mx, my);
    }
    public void mouse_move(int mx,int my){
        gameStates.get(currentState).mouse_move(mx, my);
    }
}
