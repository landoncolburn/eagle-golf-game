import java.awt.*;
import java.awt.image.*;

public class Flag extends GameObject{

  private int w,h;

  private BufferedImage sprite = null;

  public Flag(int x, int y){
    super(x, y, ID.FLAG);
    h = 160;
    w = 40;
    sprite = Game.gameInstance.bil.loadImage("assets/flag.png");
  }

  public void tick(){

  }

  public void render(Graphics g){
    g.drawImage(sprite, getX(), getY(), w, h, null);
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY()+(2*h/3), w/2, h/3);
  }

}
