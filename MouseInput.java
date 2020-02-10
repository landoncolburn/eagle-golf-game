import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

public class MouseInput extends MouseAdapter{

  private Point mousePos;

  public MouseInput(){
  }

  public void mousePressed(MouseEvent e){
    mousePos = e.getPoint();
  }

  public void mouseReleased(MouseEvent e){
    mousePos = null;
  }

  public void mouseClicked(MouseEvent e){
  }

  public Point getPoint(){
    return mousePos;
  }

  public void reset(){
    mousePos = new Point(0,0);
  }

}
