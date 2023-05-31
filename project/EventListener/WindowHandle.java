/* Name: 
    Nguyen Trang Thien Huong ITITIU21212
    Nguyen Minh luong ITITIU21240
    Nguyen Tien Phat ITITIU21273
    Dinh Thi Thanh Nha ITITIU21266
 Purpose: The purpose of this code is to allow for the monitoring and handling of these window events in Java applications. 
 The WindowHandle class is a singleton class, meaning there is only one instance of it in the application,
which can be accessed through the getInstance() method.
*/
package project.EventListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowHandle implements WindowListener {

  public boolean IsWindowDeactivated = false, IsWindowClosing =
    false, IsWindowOpen = false;
  private static final WindowHandle INSTANCE = new WindowHandle();

  private WindowHandle() {}

  public static WindowHandle getInstance() {
    return INSTANCE;
  }

  // When change tab or click outside the window
  public void windowDeactivated(WindowEvent e) {
    this.IsWindowDeactivated = true;
  }

  public void windowOpened(WindowEvent e) {
    this.IsWindowOpen = true;
  }

  public void windowClosing(WindowEvent e) {
    IsWindowClosing = true;
  }

  public void windowClosed(WindowEvent e) {}

  public void windowIconified(WindowEvent e) {}

  public void windowDeiconified(WindowEvent e) {}

  public void windowActivated(WindowEvent e) {}
}
