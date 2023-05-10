package project.EventListener;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class WindowHandle implements WindowListener {
    public boolean IsWindowDeactivated = false, IsWindowClosing = false;

    // When change tab or click outside the window
    public void windowDeactivated(WindowEvent e) {
        this.IsWindowDeactivated = true;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        IsWindowClosing = true;
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

}
