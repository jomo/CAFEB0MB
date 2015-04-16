package tv.jomo.minesweeper;

import javax.swing.JFrame;


public class Game extends JFrame {
  public String difficulty;
  public MineField field;

  public Game(String s) {
    super(s);
    generateField(10, 10, 2);
    pack();
    setResizable(false);
    setVisible(true);
  }

  public void generateField(Integer width, Integer height, Integer bombs) {
    field = new MineField(width, height, bombs);
    add(field);
  }

  public void win() {
    // TODO
  }

  public void lose() {
    // TODO
  }
}