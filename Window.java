import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Font;

public class Window{

  static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
  
  public Window(String title, Dimension size, Game game){
    JFrame f = new JFrame(title);
    f.setPreferredSize(size);
    f.setMinimumSize(size);
    f.setMaximumSize(size);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    f.setVisible(true);

    f.add(game);

    Font paintFont = new Font("TimesRoman", Font.PLAIN, 15);
    f.setFont(paintFont);
    f.getFontMetrics(paintFont);

    device.setFullScreenWindow(f);
    Game.gameInstance.size = Toolkit.getDefaultToolkit().getScreenSize();
  }
}
