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
  }
}
