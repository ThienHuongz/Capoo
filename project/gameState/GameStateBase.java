package project.gameState;

public interface GameStateBase {
    void init();

    void draw(java.awt.Graphics g);

    void update();

    void mouse_click(int mx, int my);
    void mouse_move(int mx, int my);
    
}
