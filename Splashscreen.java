import java.awt.*;
import java.awt.image.*;

public class Splashscreen extends GameObject{

  private BufferedImage[] studioLogo = new BufferedImage[10];
  private BufferedImage activeLogo = null;
  int count = 0;

  public Splashscreen(){
    super(0, 0, ID.SCREEN);
    for(int i = 0; i<10; i++){
      studioLogo[i] = Game.gameInstance.bil.loadImage("assets/studio/GameDesignLogo"+i+".png");
    }
  }

  public void tick(){
    if(count<320){
      count+=2;
    }
    if(count < 44){
      activeLogo = studioLogo[9-(int)((count)/5)];
    }
    else if(count > 44 && count < 200){
      activeLogo = studioLogo[0];
    }
    else if(count>200 && count < 244){
      activeLogo = studioLogo[(int)((count-200)/5)];
    }
    else if(count>244 && count < 300){
      activeLogo = null;
    } else if(count>300){
      Game.gameInstance.handler.addObject(new MainMenu());
      Game.gameInstance.handler.addObject(new Button(Game.gameInstance.size.width/2-150, 400));
      Game.gameInstance.handler.removeObject(this);
    }
  }

  public Rectangle getBounds(){return null;}

  public void render(Graphics g){
    g.drawImage(activeLogo, Game.gameInstance.size.width/2-150, Game.gameInstance.size.height/2-45, null);
  }

}
