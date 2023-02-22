package project;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener {
        public void keyTyped(KeyEvent e) {
            // Invoked when a key has been typed.
        }
        public void keyPressed(KeyEvent e) {
            // Invoked when a key has been pressed.
            if (e.getKeyCode() == KeyEvent.VK_W){
                System.out.print("w");
            }
            if (e.getKeyCode() == KeyEvent.VK_A){
                System.out.print("a");
            }
            if (e.getKeyCode() == KeyEvent.VK_S){
                System.out.print("s");
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                System.out.print("d");
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                System.out.print("space");
            }
        }
        public void keyReleased(KeyEvent e) {
            // Invoked when a key has been released.
        }
}
