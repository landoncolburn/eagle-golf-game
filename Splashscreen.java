import java.awt.*;
import java.awt.image.*;

public class Splashscreen extends GameObject{

  private BufferedImage studioLogo;
  int count = 0;
  private float opacity = 0f;

  public Splashscreen(){
    super(0, 0, ID.SCREEN);
    studioLogo = Game.gameInstance.bil.loadImage("assets/studio_logo.png");
  }

  public void opacity(float i){
    if(i>0){
      if(opacity+i<=100){
        opacity+=i;
      }
    } else if(i<0){
      if(opacity+i>=0){
        opacity+=i;
      }
    }
  }

  public void tick(){
    if(count<250){
      count++;
    }

    if(count < 50){
      opacity(0.02f);
    } else if(count > 50 && count < 150){

    } else if(count > 150 && count < 200){
      opacity(-0.02f);
    } else if(count > 200 && count < 250){
      opacity = 0;
    } else if(count >= 250){
      Game.gameInstance.handler.addObject(new MainMenu());
      Game.gameInstance.handler.addObject(new Button(Game.gameInstance.size.width/2-150, 400));
      Game.gameInstance.handler.removeObject(this);
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(0, 0, Game.gameInstance.size.width, Game.gameInstance.size.height);
  }

  public void render(Graphics g){
    Graphics2D g2 = (Graphics2D)g;
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2.drawImage(studioLogo, Game.gameInstance.size.width/2-150, Game.gameInstance.size.height/2-45, null);
  }

}
