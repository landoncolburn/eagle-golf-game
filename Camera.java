import java.awt.Rectangle;

public class Camera {

  private float x;
  private float y;
  private int h,w;
  private int ox = 0;
  private int oy = 0;

  public Camera(float x, float y) {
    this.x = x;
    this.y = y;
    this.w = Game.gameInstance.size.width;
    this.h = Game.gameInstance.size.height;
  }

  public void tick(GameObject object){
    if(Game.gameInstance.handler.getKey(5) == Key.UP){
      oy = -1;
    } else if(Game.gameInstance.handler.getKey(6) == Key.UP){
      oy = 1;
    } else {
      oy = 0;
    }

    if(Game.gameInstance.handler.getKey(7) == Key.UP){
      ox = -1;
    } else if(Game.gameInstance.handler.getKey(8) == Key.UP){
      ox = 1;
    } else {
      ox = 0;
    }

    x += ((object.getX() - x) - Game.gameInstance.size.width/2) * 0.05f + ox*20;
    y += ((object.getY() - y) - Game.gameInstance.size.height/2) * 0.05f + oy*20;
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
