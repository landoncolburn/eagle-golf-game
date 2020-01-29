import java.awt.*;
import java.awt.image.*;

public class MainMenu extends GameObject{

  public static void removeSelf(){
    Game.gameInstance.handler.removeObject(menu);
  }

  private static MainMenu menu;

  private BufferedImage logo;
  private BufferedImage background;
  private int count = 0;

  public MainMenu(){
    super(0, 0, ID.SCREEN);
    logo = Game.gameInstance.bil.loadImage("assets/GameLogo.png");
    background = Game.gameInstance.bil.loadImage("assets/menuBackground.jpg");
    menu = this;
  }

  public void tick(){

  }

  public Rectangle getBounds(){return null;}

  public void render(Graphics g){
    g.drawImage(background, 0, 0, Game.gameInstance.size.width, Game.gameInstance.size.height, null);
    g.drawImage(logo, Game.gameInstance.size.width/2-200, 50, 400, 200, null);
  }

}
