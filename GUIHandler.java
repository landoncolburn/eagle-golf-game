import java.awt.Graphics;
import java.util.LinkedList;

public class GUIHandler {

  private LinkedList<GameObject> elements;

  public GUIHandler(){
    elements = new LinkedList<GameObject>();
  }

  public void addObject(GameObject object){
    elements.add(object);
  }

  public void removeObject(GameObject object){
    elements.remove(object);
  }

  public void render(Graphics g){
    for(int i = 0; i < elements.size(); i++){
      elements.get(i).render(g);
    }
  }

  public void tick(){
    for(int i = 0; i<elements.size(); i++){
      elements.get(i).tick();
    }
  }

  public void updateLayout(){
    for(GameObject g : elements){
      if(g instanceof Menu){
        Menu m = (Menu)g;
        m.updateLayout();
      } else if(g instanceof Debug){
        Debug d = (Debug)g;
        d.updateLayout();
      }
    }
  }
}
