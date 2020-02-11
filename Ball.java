import java.awt.*;
import java.awt.image.*;

public class Ball extends GameObject{

  private BufferedImage sprite = null;

  public Vector2D forces = new Vector2D();
  public double angle = Math.PI;
  public int magnitude = 25;

  public int maxStrength = 90;
  public int minStrength = 25;

  private boolean jumpable = false;
  public boolean isGround = false;

  private Handler handler;

  private double oldx, oldy;

  //Constructor
  public Ball(int x, int y){
    super(x, y, ID.BALL);
    sprite = Game.gameInstance.bil.loadImage("assets/ball.png");
    handler = Game.gameInstance.handler;
    handler.addObject(new StrengthMeter(x, y, this));
    oldx = x;
    oldy = y;
  }

  // Runs once each frame
  public void tick(){
    // Gravity and Friction
    forces.setX(forces.getX()*0.995);

    // Bounds velocity to numbers greater than 0.01
    if(forces.getY()<0.01 && forces.getY()>-0.01) forces.setY(0);
    if(forces.getX()<0.01 && forces.getX()>-0.01) forces.setX(0);

    // Handles when to show StrengthMeter and allow next jump
    if(forces.getX()==0&&forces.getY()==0){
      StrengthMeter.show();
      jumpable = true;
    }

    if(x>10000||x<-10000||y>5000||y<-5000){
      respawn();
    }

    // Runs tick based methods
    handler.collision(this);
    inputHandler();
    move();
  }

  // Draws all ball related graphics (and debug menu)
  public void render(Graphics g){
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.drawImage(sprite, (int)x, (int)y, 20, 20, null);
    if(Game.gameInstance.DEBUG){
      // Angle and magnitude visual
      g.setColor(Color.RED);
      g.drawLine(getX()+10, getY()+10, (int)(getX()+10+-Math.cos(angle)*magnitude), (int)(getY()+10+-Math.sin(angle)*magnitude));
      g.drawRect(getX(), getY(), 20, 20);
    }
  }


  // Tick style method to change position based on velocity
  public void move(){
    x+=forces.getX();
    y+=forces.getY();
  }

  // Handles all player based user input
  public void inputHandler(){

    // Handles jumping triggers
    if(handler.getKey(4) == Key.UP && jumpable){
      oldx = x;
      oldy = y;
      forces.addAtAngle(-magnitude/4, angle);
      StrengthMeter.hide();
      jumpable = false;
    }

    // Handles rotation controls
    if(handler.getKey(1) == Key.UP){
      angle+=-Math.PI/100;
    } else if(handler.getKey(3) == Key.UP){
      angle+=Math.PI/100;
    }

    // Handles magnitude (strength) controls
    if(handler.getKey(0) == Key.UP && magnitude<maxStrength){
      magnitude += 1;
    } else if(handler.getKey(2) == Key.UP && magnitude>minStrength){
      magnitude += -1;
    }
  }

  public void respawn(){
    forces.setY(0);
    forces.setX(0);
    x = oldx;
    y = oldy;
  }

  // Getters and Setters
  public void setX(int x){
    this.x = x;
  }

  public void setY(int y){
    this.y = y;
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), 20, 20);
  }

}
