import java.awt.*;

public class MenuItem extends GameObject{

  private String name;
  private int action;
  private Type type;

  private int w;
  private int h;
  private Rectangle bounds;

  private boolean highlighted;
  private boolean visibility = false;
  private boolean actionRan = false;

  public MenuItem(String name) {
    super(0, 0, ID.GUI);
    this.name = name;
    build();
  }

  public MenuItem(String name, Type type) {
    super(0, 0, ID.GUI);
    this.name = name;
    this.type = type;
    build();
  }

  public MenuItem(String name, Type type, int action) {
    super(0, 0, ID.GUI);
    this.name = name;
    this.type = type;
    this.action = action;
    build();
  }

  public void build(){
    w = Game.gameInstance.oldg.getFontMetrics(Game.fonts[1]).stringWidth(name);
    h = 25;
    bounds = new Rectangle((int)x, (int)y, w, h);
  }

  public void show(){
    visibility = true;
  }

  public void hide(){
    visibility = false;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setAction(int action){
    this.action = action;
  }

  public void performAction(){
    switch(action){
      case 0:
        Handler.exit();
        break;
      case 1:
        Game.gameInstance.window.f.dispose();
        Game.gameInstance.window = new Window("Game", new Dimension(1000, 600), Game.gameInstance, false);
        break;
      default:
        break;
    }
    Game.gameInstance.mi.reset();
  }

  public void setPos(Point pos){
    this.x = pos.getX();
    this.y = pos.getY();
  }

  public int getX(){
    return (int)x;
  }

  public int getY(){
    return (int)y;
  }

  public int getWidth(){
    return w;
  }

  public int getHeight(){
    return h;
  }

  public void setBounds(int x, int y, int w, int h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  public void tick(){
    if(Game.gameInstance.paused){
      if(type == Type.BUTTON){
        if(getFauxBounds().contains(Game.gameInstance.mmi.getPoint())){
          highlighted = true;
          Game.gameInstance.setCursor(Game.cursors[1]);
        } else {
          highlighted = false;
          Game.gameInstance.setCursor(Game.cursors[0]);
        }
        if(Game.gameInstance.mi.getPoint() != null && getFauxBounds().contains(Game.gameInstance.mi.getPoint())){
          performAction();
        }
      }
    }
  }

  public void render(Graphics g){
    if(Game.gameInstance.paused){
      if(highlighted){
        g.setColor(Color.YELLOW);
      } else {
        g.setColor(Color.WHITE);
      }
      g.setFont(Game.fonts[1]);
      g.drawString(name, (int)x, (int)y+h);
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(0, 0, 0, 0);
  }

  public Rectangle getFauxBounds(){
    return new Rectangle((int)x, (int)y, w, h);
  }

}
