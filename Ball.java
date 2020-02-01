import java.awt.*;
import java.awt.image.*;

public class Ball extends GameObject{

  private BufferedImage sprite = null;

  private Vector2 forces = new Vector2();
  public double angle = 0;
  private boolean freefall = false;
  private int i = -10;
  public int magnitude = 25;

  public int maxStrength = 90;
  public int minStrength = 25;

  private Rectangle wallArea;
  private Rectangle floorArea;

  private int jumpDelay = 0;


  public Ball(int x, int y){
    super(x, y, ID.BALL);
    sprite = Game.gameInstance.bil.loadImage("assets/ball.png");
    Game.gameInstance.handler.addObject(new StrengthMeter(x, y, this));
  }

  public void tick(){
    wallArea = null;
    floorArea = null;

    //Checks collision boundaries
    Game.gameInstance.handler.checkCollision(this).forEach((c)->{
      if(c.getObject().getID()==ID.WALL){
        wallArea = c.getBounds();
      }
      if(c.getObject().getID()==ID.GROUND){
        floorArea = c.getBounds();
      }
    });

    //Hits ground
    if(floorArea!=null && floorArea.getHeight()>1){
      y-=floorArea.getHeight();
    }

    //Hits wall
    if(wallArea!=null && wallArea.getWidth()>1){
      if(wallArea.contains(new Point(getX(), getY()))){
        x+=wallArea.getWidth();
      } else {
        x-=wallArea.getWidth();
      }
      forces.setX(-forces.getX()/5);
      forces.setY(forces.getY()*0.9);
    }

    //Handles gravity and forgivness
    if(floorArea==null){
      forces.addY(Game.gameInstance.GRAVITY);
    } else {
      forces.setY(0);
      forces.setX(forces.getX()*0.9);
      if(jumpDelay<0){
        StrengthMeter.show();
      }
    }

    //Handles friction
    if(Math.abs(forces.getX())<0.01){
      forces.setX(0);
    }

    inputHandler();

    jumpDelay--;

    x+=forces.getX();
    y+=forces.getY();

  }

  public void render(Graphics g){
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.drawImage(sprite, (int)x, (int)y, 20, 20, null);
    if(Handler.DEBUG){
      g.setColor(Color.RED);
      g.drawLine(getX()+10, getY()+10, (int)(getX()+10+-Math.cos(angle)*magnitude), (int)(getY()+10+-Math.sin(angle)*magnitude));
      g.drawRect(getX(), getY(), 20, 20);
    }
  }

  public void inputHandler(){
    if(Game.gameInstance.handler.getKey(4) && jumpDelay <= 0){
      forces.addAtAngle(-magnitude/5, angle);
      jumpDelay = 100;
      StrengthMeter.hide();
    }

    if(Game.gameInstance.handler.getKey(1)){
      angle+=-0.03;
    } else if(Game.gameInstance.handler.getKey(3)){
      angle+=0.03;
    }

    if(Game.gameInstance.handler.getKey(0) && magnitude<maxStrength){
      magnitude += 1;
    } else if(Game.gameInstance.handler.getKey(2) && magnitude>minStrength){
      magnitude += -1;
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 20, 20);
  }

}
