package project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener {
    private boolean keyW, keyA, keyS, keyD, keySpace;

    public boolean isKeyW() {
        return this.keyW;
    }

    public boolean getKeyW() {
        return this.keyW;
    }

    public void setKeyW(boolean keyW) {
        this.keyW = keyW;
    }

    public boolean isKeyA() {
        return this.keyA;
    }

    public boolean getKeyA() {
        return this.keyA;
    }

    public void setKeyA(boolean keyA) {
        this.keyA = keyA;
    }

    public boolean isKeyS() {
        return this.keyS;
    }

    public boolean getKeyS() {
        return this.keyS;
    }

    public void setKeyS(boolean keyS) {
        this.keyS = keyS;
    }

    public boolean isKeyD() {
        return this.keyD;
    }

    public boolean getKeyD() {
        return this.keyD;
    }

    public void setKeyD(boolean keyD) {
        this.keyD = keyD;
    }

    public boolean isKeySpace() {
        return this.keySpace;
    }

    public boolean getKeySpace() {
        return this.keySpace;
    }

    public void setKeySpace(boolean keySpace) {
        this.keySpace = keySpace;
    }

    public void keyTyped(KeyEvent e) {
        // Invoked when a key has been typed.
    }

    public void keyPressed(KeyEvent e) {
        // Invoked when a key has been pressed.
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyW = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyA = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyD = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keySpace = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
        if (e.getKeyCode() == KeyEvent.VK_W) {
            keyW = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keyA = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keyD = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keySpace = false;
        }
    }
}
