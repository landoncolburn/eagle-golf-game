import java.awt.Rectangle;

public class Camera {

  private float x;
  private float y;
  private int h,w;

  public Camera(float x, float y) {
    this.x = x;
    this.y = y;
    this.w = Game.gameInstance.size.width;
    this.h = Game.gameInstance.size.height;
  }

  public void tick(GameObject object){
    x += ((object.getX() - x) - Game.gameInstance.size.width/2) * 0.05f;
    y += ((object.getY() - y) - Game.gameInstance.size.height/2) * 0.05f;
  }

  public float getX(){
    return x;
  }

  public void setX(float x){
    this.x = x;
  }

  public void setY(float y){
    this.y = y;
  }

  public float getY(){
    return y;
  }

  public Rectangle getBounds(){
    return new Rectangle((int)getX(), (int)getY(), w, h);
  }
}
