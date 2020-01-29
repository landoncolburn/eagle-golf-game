import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  Handler handler;

  public KeyInput(Handler handler){
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_W) handler.setKey(0, true);
    if(key == KeyEvent.VK_D) handler.setKey(3, true);
    if(key == KeyEvent.VK_S) handler.setKey(2, true);
    if(key == KeyEvent.VK_A) handler.setKey(1, true);
    if(key == KeyEvent.VK_SPACE) handler.setKey(4, true);
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_W) handler.setKey(0, false);
    if(key == KeyEvent.VK_D) handler.setKey(3, false);
    if(key == KeyEvent.VK_S) handler.setKey(2, false);
    if(key == KeyEvent.VK_A) handler.setKey(1, false);
    if(key == KeyEvent.VK_SPACE) handler.setKey(4, false);
  }
}
