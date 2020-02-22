import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;
import java.awt.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {

  private static final long serialVersionUID = 42l;

  public static Game gameInstance;
  public Handler handler;
  public GUIHandler gui;

  private LinkedList<LevelData> levels = new LinkedList<LevelData>();
  private int currentLvl = 0;

  public static Font[] fonts = {
    new Font("SansSerif", Font.PLAIN, 16),
    new Font("SansSerif", Font.PLAIN, 24),
    new Font("SansSerif", Font.PLAIN, 32),
  };
  public static Cursor[] cursors = {
    new Cursor(Cursor.DEFAULT_CURSOR),
    new Cursor(Cursor.HAND_CURSOR)
  };
  private Thread thread;
  public boolean isRunning = false;
  public boolean paused = false;
  public int fps = 0;
  public Graphics oldg;

  public BufferedImageLoader bil = new BufferedImageLoader();
  public Dimension size = new Dimension(1000, 600);
  public MouseMotionInput mmi;
  public MouseInput mi;
  public Camera camera;

  public final double GRAVITY = 0.3;

  public Color background = new Color(50, 50, 50);

  public boolean DEBUG = false;
  // public MenuItem menuItem;
  public Window window;
  public Ball player;
  public Flag flag;

  public Game(){
    gameInstance = this;
    window = new Window("Game", size, gameInstance, true);

    camera = new Camera(0, 0);
    handler = new Handler();
    gui = new GUIHandler();
    mmi = new MouseMotionInput(handler);
    mi = new MouseInput();

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
    double amountOfTicks = 65.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(isRunning) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        frames++;
        tick();
        render();
        delta--;
      }
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
    Graphics2D g2d = (Graphics2D) g;
    //////////////////////////////////
    ///////----DRAW IN HERE----///////
    //////////////////////////////////

    g.setColor(background);
    g.fillRect(0, 0, size.width, size.height);
    oldg = g;

    g2d.translate(-camera.getX(), -camera.getY());

    handler.render(g);

    g2d.translate(camera.getX(), camera.getY());

    gui.render(g);

    //////////////////////////////////
    g.dispose();
    bs.show();
  }

  public double lerp(double a, double b, double f){
    return (a * (1.0 - f)) + (b * f);
  }

  public void tick(){

    size = window.f.getSize();

    for(int i = 0; i<handler.gameObjects.size(); i++){
      if(handler.gameObjects.get(i).getID() == ID.BALL){
        camera.tick(handler.gameObjects.get(i));
      }
    }

    handler.tick();
    gui.tick();
  }

  public void startGame(){
    loadLevel("level"+currentLvl+".lvl");
    background = new Color(160, 240, 240);
    MenuItem[] pauseMenu = {
      new MenuItem("Respawn", Type.BUTTON, Action.RESPAWN),
      new MenuItem("Settings", Type.BUTTON, Action.FULLSCREEN),
      new MenuItem("Main Menu", Type.BUTTON , Action.SETTINGS),
      new MenuItem("Skip Level", Type.BUTTON , Action.NEXT_LEVEL),
      new MenuItem("Exit Game", Type.BUTTON, Action.EXIT),
    };
    gui.addObject(new Menu(levels.getFirst().getName(), pauseMenu));
    gui.addObject(new Debug(player));
    gui.addObject(new Counter(168, 99));

  }

  public void loadLevel(String levelName){
    levels.add(new LevelData(levelName));
    handler.gameObjects.addAll(levels.get(currentLvl).getObjects());
    handler.par = levels.get(currentLvl).getPar();
  }

  public void nextLevel(){
    int stroke = handler.strokes;
    int par = handler.par;
    if(stroke == 1){
      gui.addObject(new Animation(Animations.HOLE_IN_ONE));
    } else if(par-stroke==3){ //ALBATROSS
      gui.addObject(new Animation(Animations.ALBATROSS));
    } else if(par-stroke==2){ //EAGLE
      gui.addObject(new Animation(Animations.EAGLE));
    } else if(par-stroke==1){ //BIRDIE
      gui.addObject(new Animation(Animations.BIRDIE));
    } else if(stroke == par){ //PAR
      gui.addObject(new Animation(Animations.PAR));
    } else if(par-stroke==-1){ //BOGEY
      gui.addObject(new Animation(Animations.BOGEY));
    } else if(par-stroke==-2){ //DOUBLE BOGEY
      gui.addObject(new Animation(Animations.DOUBLE_BOGEY));
    } else if(stroke > par){ //FAIL
      gui.addObject(new Animation(Animations.FAIL));
    } else if(stroke < par){ //SUCCESS
      gui.addObject(new Animation(Animations.SUCCESS));
    }
    unloadLevel();
    currentLvl++;
    if(currentLvl<4){
      loadLevel("level"+currentLvl+".lvl");
    }
  }

  public void unloadLevel(){
    handler.gameObjects.clear();
    handler.setStroke(0);
  }

  public String getLevelName(){
    if(currentLvl <= levels.size()-1){
      return levels.get(currentLvl).getName();
    } else {
      return "Invalid Level";
    }
  }

  public static void main(String[] args) {
    new Game();
  }
}
