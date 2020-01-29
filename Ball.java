import java.awt.*;
import java.awt.image.*;

public class Ball extends GameObject{

  private BufferedImage sprite = null;

  private Vector2 forces = new Vector2();
  private double angle = Math.PI;
  private boolean grounded = false;
  private boolean walled = false;
  private int i = -10;
  private int magnitude = 25;

  public Ball(int x, int y){
    super(x, y, ID.BALL);
    sprite = Game.gameInstance.bil.loadImage("assets/ball.png");
  }

  public void tick(){
    if(Game.gameInstance.handler.checkCollision(this)!=null){
      if(Game.gameInstance.handler.checkCollision(this).getWidth()>10){
        grounded = true;
      }
      if(Game.gameInstance.handler.checkCollision(this).getHeight()>10){
        walled = true;
      }
    } else {
      grounded = false;
      walled = false;
    }

    if(grounded){
      y -= Game.gameInstance.handler.checkCollision(this).getHeight()-1;
      forces.setY(forces.getY()/-2);
    } else {
      forces.addY(Game.gameInstance.GRAVITY);
    }

    if(grounded && forces.getX()!=0){
      if(forces.getX()>0){
        forces.addX(-forces.getX()/10);
      } else if(forces.getX()<0){
        forces.addX(forces.getX()/10);
      }
    }

    inputHandler();

    x+=forces.getX();
    if(forces.getY()>0 && !grounded){
      y+=forces.getY();
    } else if(forces.getY()<0){
      y+=forces.getY();
    }

  }

  public void render(Graphics g){
    g.drawImage(sprite, (int)x, (int)y, 20, 20, null);
    g.setColor(Color.RED);
    g.drawLine(getX()+10, getY()+10, (int)(getX()+10+-Math.cos(angle)*magnitude), (int)(getY()+10+-Math.sin(angle)*magnitude));
    g.drawRect(getX(), getY(), 20, 20);
  }

  public void inputHandler(){
    if(Game.gameInstance.handler.getKey(4) && grounded){
      forces.addAtAngle(-magnitude/5, angle);
    }

    if(Game.gameInstance.handler.getKey(1)){
      angle+=-0.2;
    } else if(Game.gameInstance.handler.getKey(3)){
      angle+=0.2;
    }

    if(Game.gameInstance.handler.getKey(0) && magnitude<75){
      magnitude += 1;
    } else if(Game.gameInstance.handler.getKey(2) && magnitude>15){
      magnitude += -1;
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 20, 20);
  }

}
