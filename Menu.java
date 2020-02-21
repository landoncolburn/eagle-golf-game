import java.awt.*;
import java.util.*;

public class Menu extends GameObject {

  private LinkedList<MenuItem> items = new LinkedList<MenuItem>();
  private String title;
  private int w;
  private int h;

  public Menu(String title, MenuItem[] items){
    super(0, 0, ID.GUI);
    this.title = title;
    this.w = (int)Game.gameInstance.size.width/2;
    this.h = (int)Game.gameInstance.size.height*3/4;
    this.x = (int)(Game.gameInstance.size.width-w)/2;
    this.y = (int)(Game.gameInstance.size.height-h)/2;
    for(MenuItem item : items){
      this.items.add(item);
    }
    autoLayout();
  }

  public Menu(String title, MenuItem[] items, int x, int y){
    super(x, y, ID.GUI);
    this.title = title;
    this.w = Game.gameInstance.size.width/2;
    this.h = Game.gameInstance.size.height*3/4;
    for(MenuItem item : items){
      this.items.add(item);
    }
    autoLayout();
  }

  public Menu(String title, MenuItem[] items, int x, int y, int w, int h){
    super(x, y, ID.GUI);
    this.title = title;
    this.w = w;
    this.h = h;
    for(MenuItem item : items){
      this.items.add(item);
    }
    autoLayout();
  }

  public void tick(){
    items.forEach((i) -> {
      i.tick();
    });
  }

  public void autoLayout(){
    for(int i = 0; i<items.size(); i++){
      items.get(i).build();
      items.get(i).setPos(new Point((Game.gameInstance.size.width-items.get(i).getWidth())/2, (int)y+200+(i*75)));
    }
  }

  public void setTitle(String s){
    this.title = s;
  }

  public void updateLayout(){
    this.x = (int)(Game.gameInstance.size.width-w)/2;
    this.y = (int)(Game.gameInstance.size.height-h)/2;
    autoLayout();
  }

  public void render(Graphics g){
    if(Game.gameInstance.paused){
      setTitle(Game.gameInstance.getLevelName());
      g.setColor(new Color(0, 0, 0, 175));
      g.fillRect((int)x, (int)y, w, h);
      g.setFont(Game.fonts[2]);
      g.setColor(Color.WHITE);
      g.drawString(title, ((int)x+w/2)-g.getFontMetrics().stringWidth(title)/2, (int)y+75);
      items.forEach((i) -> {
        i.render(g);
      });
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(0, 0, 0, 0);
  }

}
