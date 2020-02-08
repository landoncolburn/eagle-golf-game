import java.awt.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class StrengthMeter extends GameObject{

  private Ball parent;
  private static boolean visible;

  private BufferedImage sprite = null;
  private BufferedImage[] states = new BufferedImage[6];

  // Constructor
  public StrengthMeter(int x, int y, Ball parent){
    super(x, y, ID.GUI);
    this.parent = parent;
    for(int i = 0; i<6; i++){
      states[i] = Game.gameInstance.bil.loadImage("assets/meter/meter-"+i+".png");
    }
    sprite = states[0];
  }

  // Runs once per frame
  public void tick(){
    x = parent.getX()-15;
    y = parent.getY()-110;
    updateImage();
  }

  // Sets meter's to be shown
  public static void show(){
    visible = true;
  }

  // Hides meter
  public static void hide(){
    visible = false;
  }

  // Updates image based on ball magnitude
  public void updateImage(){
    sprite = states[(int)((parent.magnitude-parent.minStrength)/(double)(parent.maxStrength-parent.minStrength)*5)];
  }

  // Draws graphics if visiblity is true
  public void render(Graphics g){
    if(visible){
      Graphics2D g2d = (Graphics2D) g;
      AffineTransform origXform = g2d.getTransform();
      AffineTransform newXform = (AffineTransform)(origXform.clone());
      newXform.rotate(parent.angle-Math.PI/2, x+25, y+120);
      g2d.setTransform(newXform);
      g.drawImage(sprite, getX(), getY(), 50, 100, null);
      g2d.setTransform(origXform);
    }
  }

  // Returns hitbox for StrengthMeter (zero)
  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 0, 0);
  }

}
