package tv.jomo.minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Dimension;


public class MineCell extends JButton {
  public Boolean bomb;
  public Boolean flagged;
  public int x;
  public int y;
  public int bombs; // neighbour count

  public MineCell(int x, int y) {
    super();
    this.x = x;
    this.y = y;
    setPreferredSize(new Dimension(50, 50));
    setupListener();
  }

  private void setupListener() {
    addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON3) {
          setText("F");
        } else if (e.getButton() == MouseEvent.BUTTON1) {
          setText("O_o");
        } else {
          setText("");
        }
      }
    });
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