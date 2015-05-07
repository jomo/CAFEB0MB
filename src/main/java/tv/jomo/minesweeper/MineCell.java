package tv.jomo.minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.StrokeBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;


public class MineCell extends JButton {
  public Boolean bomb = false;
  public Boolean flagged = false;
  public Integer x;
  public Integer y;
  public Integer bombs = 0; // neighbour count

  public MineCell(Integer x, Integer y) {
    super();
    this.x = x;
    this.y = y;
    setPreferredSize(new Dimension(50, 50));
    setupListener();
    initStyle();
  }

  public void initStyle() {
    setText("");
    setEnabled(true);
    setOpaque(true);
    setBackground(Color.LIGHT_GRAY);
    setBorder(new StrokeBorder(new BasicStroke(), Color.WHITE));
  }

  // adds mouse events
  private void setupListener() {
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (isEnabled()) {
          if (e.getButton() == MouseEvent.BUTTON3) {
            toggleFlag();
          } else if (e.getButton() == MouseEvent.BUTTON1) {
            flagged = false;
            if (bomb) {
              Game.getInstance().lose();
            } else {
              if (bombs == 0) {
                Game.getInstance().field.clearCells(MineCell.this);
              } else {
                reveal();
              }
            }
          } else {
            setText("");
          }
          Game.getInstance().field.checkWin();
        }
      }
    });
  }

  // toggles the `flagged` state and sets the text
  public void toggleFlag() {
    flagged = !flagged;
    setText(flagged ? "⚑" : "");
  }

  // shows the number of bombs nearby
  // or if the cell itself is a bomb
  public void reveal() {
    if (isEnabled()) {
      setEnabled(false);
      if (bomb) {
        if (flagged) {
          setText("⚑ ✶");
        } else {
          setText("✶");
        }
      } else {
        setText(bombs == 0 ? "" : bombs.toString());
      }
      setBackground(Color.WHITE);
    }
  }
}