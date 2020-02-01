import java.awt.Rectangle;

public class Collision {
  private Rectangle collisionShape;
  private GameObject object;

  public Collision(GameObject obj, Rectangle cs){
    this.collisionShape = cs;
    this.object = obj;
  }

  public Rectangle getBounds(){
    return collisionShape;
  }

  public GameObject getObject(){
    return object;
  }
}
