import java.awt.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class StrengthMeter extends GameObject{

  private BufferedImage sprite = null;
  private BufferedImage[] states = new BufferedImage[6];
  private Ball parent;
  private static boolean visible;

  public StrengthMeter(int x, int y, Ball parent){
    super(x, y, ID.GUI);
    this.parent = parent;
    for(int i = 0; i<6; i++){
      states[i] = Game.gameInstance.bil.loadImage("assets/meter/meter-"+i+".png");
    }
    sprite = states[0];
  }

  public void tick(){
    x = parent.getX()-15;
    y = parent.getY()-110;
    setImage();
  }

  public static void show(){
    visible = true;
  }

  public static void hide(){
    visible = false;
  }

  public void setImage(){
    sprite = states[(int)((parent.magnitude-parent.minStrength)/(double)(parent.maxStrength-parent.minStrength)*5)];
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 0, 0);
  }

  public void render(Graphics g){
    if(visible){
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform old = g2d.getTransform();
      AffineTransform transform = new AffineTransform();
      transform.rotate(parent.angle-Math.PI/2, parent.getX()+10, parent.getY()+10);
      g2d.transform(transform);
      g2d.drawImage(sprite, getX(), getY(), 50, 100, null);
      g2d.setTransform(old);
    }
  }

}
