import java.awt.*;
import java.awt.image.*;

public class Ground extends GameObject{

  private BufferedImage sprite = null;
  private int w,h;

  public Ground(int x, int y, int w, int h){
    super(x, y, ID.GROUND);
    this.w = w;
    this.h = h;
  }

  public void tick(){

  }

  public void render(Graphics g){
    g.setColor(new Color(108, 215, 95));
    g.fillRect(getX(), getY(), w, h);
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), w, h);
  }

}
