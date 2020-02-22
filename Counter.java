import java.awt.*;
import java.awt.image.BufferedImage;

public class Counter extends GameObject {

  private int w,h;

  private BufferedImage[] nums = new BufferedImage[10];
  private BufferedImage ones,tens;

  private int stroke,d;

  public Counter(int w, int h){
    super(Game.gameInstance.size.width-w-40, 30, ID.GUI);
    this.w = w;
    this.h = h;
    for(int i = 0; i<10; i++){
      nums[i] = Game.gameInstance.bil.loadImage("assets/numbers/"+i+".png");
    }
  }

  public void tick(){
    stroke = Game.gameInstance.handler.strokes;
    if(stroke>=10){
      d = 2;
      tens = nums[stroke/10];
      ones = nums[stroke%10];
    } else {
      d = 1;
      tens = null;
      ones = nums[stroke];
    }
  }

  public void render(Graphics g){
    if(d <= 1){
      g.drawImage(ones, getX()+(w/2), getY(), w/2, h, null);
    } else if(d > 1){
      g.drawImage(tens, getX()+15, getY(), w/2, h, null);
      g.drawImage(ones, getX()+(w/2), getY(), w/2, h, null);
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(getX(), getY(), w, h);
  }

}
