package tv.jomo.minesweeper;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame {

  private static Game instance = new Game("CAFEB0MB");
  public String difficulty;
  public MineField field;

  public static synchronized Game getInstance() {
    return instance;
  }

  private Game(String s) {
    super(s);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    start();
    setResizable(false);
    setVisible(true);
  }

  private void start() {
    if (field != null) {
      remove(field);
    }
    field = new MineField(10, 10, 10);
    add(field);
    pack(); // auto resize
  }

  public void win() {
    field.solve();
    JOptionPane.showMessageDialog(this, "Well done!", "Win", JOptionPane.PLAIN_MESSAGE);
    start();
  }

  public void lose() {
    field.solve();
    JOptionPane.showMessageDialog(this, "You suck at this game.", "Lose", JOptionPane.PLAIN_MESSAGE);
    start();
  }
}