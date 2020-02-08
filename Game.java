import java.awt.*;
import java.awt.image.*;
import java.awt.Event;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 42l;

  public static Game gameInstance;
  public Handler handler;

  private BufferedImage studioLogo;
  private Thread thread;
  private boolean isRunning = false;
  public int fps = 0;

  public BufferedImageLoader bil = new BufferedImageLoader();
  public Dimension size = new Dimension(1000, 600);
  public MouseMotionInput mmi;
  public MouseInput mi;

  public final double GRAVITY = 0.3;

  public Color background = new Color(50, 50, 50);

  public boolean DEBUG = false;

  public Game(){
    gameInstance = this;
    new Window("Game", size, gameInstance);

    handler = new Handler();
    mmi = new MouseMotionInput(handler);
    mi = new MouseInput(handler);

    handler.addObject(new Splashscreen());

    this.addMouseListener(mi);
    this.addMouseMotionListener(mmi);
    this.addKeyListener(new KeyInput(handler));

    start();
  }

  public void start(){
    isRunning = true;
    thread = new Thread(this);
    thread.start();
  }

  public void stop(){
    isRunning = false;
    try{
      thread.join();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        tick();
        delta--;
      }
      if(isRunning)
        render();
      frames++;
      if(System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        fps = frames;
        frames = 0;
      }
      long endTime = System.nanoTime();
      long elapsedTime = endTime - now;
      try {
        Thread.sleep((Math.abs((long) 16666666 - elapsedTime)) / 1000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    stop();
  }

  public void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs==null){
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    //////////////////////////////////
    ///////----DRAW IN HERE----///////
    //////////////////////////////////

    g.setColor(background);
    g.fillRect(0, 0, size.width, size.height);

    handler.render(g);

    //////////////////////////////////
    g.dispose();
    bs.show();
  }

  public void tick(){
    handler.tick();
  }

  public void startGame(){
    background = new Color(160, 240, 240);
    handler.addObject(new Wall(0, size.height-100, size.width, 100));
    handler.addObject(new Wall(0, 0, size.width, 100));
    handler.addObject(new Wall(0, 0, 100, size.height));
    handler.addObject(new Wall(size.width-100, 0, 100, size.height));
    handler.addObject(new Ball(500, 200));

  }

  public static void main(String[] args) {
    new Game();
  }
}
