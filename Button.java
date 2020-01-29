import java.awt.*;
import java.awt.image.*;

public class Button extends GameObject{

  private BufferedImage buttonImage = null;
  private BufferedImage buttonDownImage = null;
  private BufferedImage button = null;

  public Button(int x, int y){
    super(x, y, ID.SCREEN);
    buttonImage = Game.gameInstance.bil.loadImage("assets/button.png");
    buttonDownImage = Game.gameInstance.bil.loadImage("assets/button_pushed.png");
  }

  public void tick(){
    if(Game.gameInstance.mi.getPoint() != null && getBounds().contains(Game.gameInstance.mi.getPoint())){
      MainMenu.removeSelf();
      Game.gameInstance.handler.removeObject(this);
      Game.gameInstance.startGame();
    }
    if(Game.gameInstance.mmi.getPoint() != null && getBounds().contains(Game.gameInstance.mmi.getPoint())){
      button = buttonDownImage;
    } else {
      button = buttonImage;
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 300, 150);
  }

  public void render(Graphics g){
    g.drawImage(button, getX(), getY(), 300, 150, null);
  }

}
