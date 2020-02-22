import java.io.*;
import java.util.*;

public class LevelData {

  private File file;
  private String name;
  private int[] spawn = new int[2];
  private int[] flag = new int[2];
  private int par;
  private LinkedList<Wall> walls = new LinkedList<Wall>();

  public LevelData(String file){
    this.file = new File("levels/" + file);
    String[] pathEle = file.split("/");
    this.name = pathEle[pathEle.length-1];
    loadLevel();
  }

  public void loadLevel(){
    try {
      Scanner readLvl = new Scanner(file);
      name = readLvl.nextLine();
      par = Integer.parseInt(readLvl.nextLine());
      String[] tspawn = readLvl.nextLine().split(",", 2);
      spawn[0] = Integer.parseInt(tspawn[0]);
      spawn[1] = Integer.parseInt(tspawn[1]);
      String[] tempflag = readLvl.nextLine().split(",", 2);
      flag[0] = Integer.parseInt(tempflag[0]);
      flag[1] = Integer.parseInt(tempflag[1]);
      while(readLvl.hasNext()){
        String[] arg = readLvl.nextLine().split(",", 4);
        walls.add(new Wall(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), Integer.parseInt(arg[3])));
      }
      readLvl.close();
    } catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public LinkedList<GameObject> getObjects(){
    LinkedList<GameObject> go = new LinkedList<GameObject>();
    go.addAll(walls);
    Game.gameInstance.player = new Ball(spawn[0], spawn[1]);
    go.add(Game.gameInstance.player);
    Game.gameInstance.flag = new Flag(flag[0], flag[1]);
    go.add(Game.gameInstance.flag);
    return go;
  }

  public String getName(){
    return this.name;
  }

  public int getPar(){
    return par;
  }
}
