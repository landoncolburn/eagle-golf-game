import java.util.*;
import java.awt.*;

public class Handler {

  // Main list containing all gameObjects
  public LinkedList<GameObject> gameObjects;

  public int strokes = 0;
  public int par = 0;

  // Boolean list to store keys pressed state
  private Key[] keys = {
    Key.DOWN, //W (0)
    Key.DOWN, //A (1)
    Key.DOWN, //S (2)
    Key.DOWN, //D (3)
    Key.DOWN, //Space (4)
    Key.DOWN, //UP (5)
    Key.DOWN, //DOWN (6)
    Key.DOWN, //LEFT (7)
    Key.DOWN, //RIGHT (8)
  };

  // Constuctor
  public Handler(){
    gameObjects = new LinkedList<GameObject>();
  }

  // Get gameObject
  public GameObject getObject(int i){
    return gameObjects.get(i);
  }

  // Adds gameObject
  public void addObject(GameObject object){
    gameObjects.add(object);
  }

  // Remove gameObject
  public void removeObject(GameObject object){
    gameObjects.remove(object);
  }

  // Calls render method for each gameObject, and potentially renders debug screen
  public void render(Graphics g){
    for(int i = 0; i < gameObjects.size(); i++){
      if(gameObjects.get(i).getBounds().intersects(Game.gameInstance.camera.getBounds())){
        gameObjects.get(i).render(g);
      }
    }
  }

  //Adds another stoke to total per course
  public void setStroke(int i){
    strokes = i;
  }

  //Gets stroke count
  public int getStroke(){
    return strokes;
  }

  // Calls each gameObjects tick method
  public void tick(){
    if(Game.gameInstance.paused){
    } else {
      for(int i = 0; i<gameObjects.size(); i++){
        gameObjects.get(i).tick();
      }
    }
  }

  // Tests to see if a collision is present at a specified direction from ball
  public boolean testPoint(Direction dir, int m, Ball ball, Rectangle r2){
    Point p;
    switch(dir){
      case NORTH:
        p = new Point(ball.getX()+10, ball.getY()+m);
        break;
      case EAST:
        p = new Point(ball.getX()+20+m, ball.getY()+10);
        break;
      case SOUTH:
        p = new Point(ball.getX()+10, ball.getY()+20+m);
        break;
      case WEST:
        p = new Point(ball.getX()+m, ball.getY()+10);
        break;
      default:
        return false;
    }
    if(r2.contains(p)){
      return true;
    } else {
      return false;
    }
  }

  // Runs though collision detection for the ball
  public void collision(Ball ball){
    ball.isGround = false;
    for(int i = 0; i < gameObjects.size(); i++) {
      GameObject tempObject = gameObjects.get(i);
      if(tempObject.getID() == ID.WALL){
        if(testPoint(Direction.WEST, (int)ball.forces.getX(), ball, tempObject.getBounds())){
          ball.forces.setX(-ball.forces.getX()*0.85);
        }
        if(testPoint(Direction.SOUTH, (int)ball.forces.getY(), ball, tempObject.getBounds())){
          ball.forces.setY(-ball.forces.getY()/2);
          ball.isGround = true;
        }
        if(testPoint(Direction.EAST, (int)ball.forces.getX(), ball, tempObject.getBounds())){
          ball.forces.setX(-ball.forces.getX()*0.85);
        }
        if(testPoint(Direction.NORTH, (int)ball.forces.getY(), ball, tempObject.getBounds())){
          ball.forces.setY(-ball.forces.getY()*0.85);
        }
      }
    }
    if(!ball.isGround){
      ball.forces.addY(0.3);
    } else {
      ball.forces.setX(ball.forces.getX()*0.9);
    }
  }

  // Sets key to specified state
  public void setKey(int i, Key b){
    keys[i] = b;
  }

  // Gets key state
  public Key getKey(int i){
    return keys[i];
  }

  // Kills game
  public static void exit(){
    System.exit(0);
  }

  public String toString(){
    String s = "";
    for(GameObject o : gameObjects){
      s+=o.getID()+", ";
    }
    return s;
  }

}
