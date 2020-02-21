import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation extends GameObject{

  private int w;
  private int h;

  private BufferedImage sprite;

  private boolean visible;
  private int counter = 0;

  private float opacity;

  public Animation(Animations a){
    super(0, 0, ID.GUI);
    sprite = Game.gameInstance.bil.loadImage(setImage(a));
    w = sprite.getWidth()/2;
    h = 110;
    x = (Game.gameInstance.size.width-w)/2;
    y = (Game.gameInstance.size.height-h)/2;
    visible = true;
  }

  public String setImage(Animations a){
    switch(a){
      case FAIL:
        return "assets/states/fail.png";
      case SUCCESS:
        return "assets/states/success.png";
      case HOLE_IN_ONE:
        return "assets/states/hole_in_one.png";
      case BIRDIE:
        return "assets/states/birdie.png";
      case EAGLE:
        return "assets/states/eagle.png";
      case ALBATROSS:
        return "assets/states/albatross.png";
      case PAR:
        return "assets/states/par.png";
      case BOGEY:
        return "assets/states/bogey.png";
      case DOUBLE_BOGEY:
        return "assets/states/double_bogey.png";
      default:
        return "";
    }
  }

  public void tick(){
    if(counter>=0&&counter<25){
      opacity+=0.039f;
    } else if(counter>=25&&counter<100){

    } else if(counter>=100&&counter<150){
      opacity-=0.019f;
    } else if(counter>=150){
      Game.gameInstance.gui.removeObject(this);
    }
    counter++;
  }

  public void render(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    if(visible){
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
      g2d.drawImage(sprite, getX(), getY(), w, h, null);
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), w, h);
  }

}
