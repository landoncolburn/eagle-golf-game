import java.awt.*;
import java.awt.image.*;

public class Wall extends GameObject{

  private BufferedImage sprite = null;
  private int w,h;

  public Wall(int x, int y, int w, int h){
    super(x, y, ID.WALL);
    this.w = w;
    this.h = h;
  }

  public void tick(){

  }

  public void render(Graphics g){
    g.setColor(new Color(140, 140, 140));
    g.fillRect(getX(), getY(), w, h);
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), w, h);
  }

}
