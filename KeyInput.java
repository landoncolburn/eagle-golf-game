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
    if(key == KeyEvent.VK_W) handler.setKey(0, Key.UP);
    if(key == KeyEvent.VK_D) handler.setKey(3, Key.UP);
    if(key == KeyEvent.VK_S) handler.setKey(2, Key.UP);
    if(key == KeyEvent.VK_A) handler.setKey(1, Key.UP);
    if(key == KeyEvent.VK_SPACE) handler.setKey(4, Key.UP);
    if(key == KeyEvent.VK_UP) handler.setKey(5, Key.UP);
    if(key == KeyEvent.VK_DOWN) handler.setKey(6, Key.UP);
    if(key == KeyEvent.VK_LEFT) handler.setKey(7, Key.UP);
    if(key == KeyEvent.VK_RIGHT) handler.setKey(8, Key.UP);
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_W) handler.setKey(0, Key.DOWN);
    if(key == KeyEvent.VK_D) handler.setKey(3, Key.DOWN);
    if(key == KeyEvent.VK_S) handler.setKey(2, Key.DOWN);
    if(key == KeyEvent.VK_A) handler.setKey(1, Key.DOWN);
    if(key == KeyEvent.VK_SPACE) handler.setKey(4, Key.DOWN);
    if(key == KeyEvent.VK_F3) Game.gameInstance.DEBUG = !Game.gameInstance.DEBUG;
    if(key == KeyEvent.VK_ESCAPE) Game.gameInstance.paused = !Game.gameInstance.paused;
    if(key == KeyEvent.VK_UP) handler.setKey(5, Key.DOWN);
    if(key == KeyEvent.VK_DOWN) handler.setKey(6, Key.DOWN);
    if(key == KeyEvent.VK_LEFT) handler.setKey(7, Key.DOWN);
    if(key == KeyEvent.VK_RIGHT) handler.setKey(8, Key.DOWN);
  }
}
