import java.util.*;
import java.awt.*;

public class Handler {

  private LinkedList<GameObject> gameObjects;
  private boolean[] keys = {
    false, //W
    false, //A
    false, //S
    false, //D
    false //Space
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

  public Rectangle checkCollision(GameObject ball){
    for(int i = 0; i<gameObjects.size(); i++){
      GameObject tempObject = gameObjects.get(i);
      if(tempObject.getID().equals(ID.GROUND)){
        if(tempObject.getBounds().intersects(ball.getBounds())){
          return tempObject.getBounds().intersection(ball.getBounds());
        }
      }
    }
    return null;
  }

  public void setKey(int i, boolean b){
    keys[i] = b;
  }

  public boolean getKey(int i){
    return keys[i];
  }

}
