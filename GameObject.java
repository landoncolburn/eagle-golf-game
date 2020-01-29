import java.util.*;
import java.awt.*;

public abstract class GameObject{
  protected double x,y;
  private ID id;

  public GameObject(double x, double y, ID id){
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public abstract void tick();
  public abstract void render(Graphics g);
  public abstract Rectangle getBounds();

	public int getX() {
		return (int)x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(double y) {
		this.y = y;
	}

  public ID getID(){
    return id;
  }

  public void setID(ID id){
    this.id = id;
  }
}
