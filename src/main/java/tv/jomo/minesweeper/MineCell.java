package tv.jomo.minesweeper;

import javax.swing.JButton;
import java.awt.Dimension;


public class MineCell extends JButton {
  public Boolean bomb;
  public Boolean flagged;
  public int bombs; // neighbour count

  public MineCell() {
    super();
    setPreferredSize(new Dimension(50, 50));
  }

  public void setFlag(Boolean state) {
    flagged = state;
  }

  public void setBomb(Boolean state) {
    bomb = state;
  }

  public void explode() {
    // TODO
  }

  public void reveal() {
    // TODO
  }
}