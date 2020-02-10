import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

public class MouseInput extends MouseAdapter{

  private Point mousePos;

  public MouseInput(){
  }

  public void mousePressed(MouseEvent e){
  }

  public void mouseClicked(MouseEvent e){
    mousePos = e.getPoint();
  }

  public Point getPoint(){
    return mousePos;
  }

  public void reset(){
    mousePos = new Point(0,0);
  }

}
