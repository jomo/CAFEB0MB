package tv.jomo.minesweeper;

import javax.swing.JFrame;

public class Game extends JFrame {

  private static Game instance = new Game("CAFEB0MB");
  public String difficulty;
  public MineField field;

  public static synchronized Game getInstance() {
    return instance;
  }

  private Game(String s) {
    super(s);
    add(new MineField(10, 10, 10));
    pack();
    setResizable(false);
    setVisible(true);
  }
  public void win() {
    // TODO
  }

  public void lose() {
    // TODO
  }
}