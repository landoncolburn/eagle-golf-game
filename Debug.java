import java.awt.*;

public class Debug extends GameObject{

  private Ball player;
  private int px,py;
  private double pfx,pfy;
  private double a,m;
  private int fps;

  public Debug(Ball ball){
    super(0, 0, ID.GUI);
    this.player = ball;
  }

  public void update(){
    px = player.getX();
    py = player.getY();
    pfx = player.forces.getX();
    pfy = player.forces.getY();
    a = player.angle;
    m = player.magnitude;
    fps = Game.gameInstance.fps;
  }

  public void tick(){
    if(player!=null){
      update();
    }
  }

  public void render(Graphics g){
    if(Game.gameInstance.DEBUG){
      g.setColor(Color.WHITE);
      g.setFont(Game.fonts[0]);
      g.drawString(("FPS: " + Game.gameInstance.fps), 10, 20);
      g.drawString(("X: " + px + ", Y: " + py), 10, 40);
      g.drawString(("Angle: " + a + ", Magnitude: " + m), 10, 60);
      g.drawString(("Forces: ["+pfx+", "+pfy+"]"), 10, 80);
      g.drawString(("Entities: "+ Game.gameInstance.handler.toString()), 10, 100);
      g.drawString(("Camera: " + Game.gameInstance.camera.getX() + ", " + Game.gameInstance.camera.getY()), 10, 120);
    }
  }

  public void updateLayout(){
    x = 0;
    y = 0;
  }

  public Rectangle getBounds(){
    return new Rectangle(0,0,0,0);
  }
}
