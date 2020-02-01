import java.util.*;
import java.awt.*;

public class Handler {

  public static boolean DEBUG = false;

  private LinkedList<GameObject> gameObjects;
  private boolean[] keys = {
    false, //W
    false, //A
    false, //S
    false, //D
    false, //Space
    false //F3
  };

  public Handler(){
    gameObjects = new LinkedList<GameObject>();
  }

  public GameObject getObject(int i){
    return gameObjects.get(i);
  }

  public void addObject(GameObject object){
    gameObjects.add(object);
  }

  public void removeObject(GameObject object){
    gameObjects.remove(object);
  }

  public void render(Graphics g){
    for(int i = 0; i < gameObjects.size(); i++){
      gameObjects.get(i).render(g);
    }
  }

  public void tick(){
    for(int i = 0; i<gameObjects.size(); i++){
      gameObjects.get(i).tick();
    }
  }

  public LinkedList<Collision> checkCollision(GameObject ball){
    LinkedList<Collision> collisions = new LinkedList<Collision>();
    for(int i = 0; i<gameObjects.size(); i++){
      GameObject tempObject = gameObjects.get(i);
      if(ball.getBounds().intersects(tempObject.getBounds()) && tempObject != ball){
        collisions.add(new Collision(tempObject, ball.getBounds().intersection(tempObject.getBounds())));
      }
    }
    return collisions;
  }

  public void setKey(int i, boolean b){
    keys[i] = b;
  }

  public boolean getKey(int i){
    return keys[i];
  }

}
