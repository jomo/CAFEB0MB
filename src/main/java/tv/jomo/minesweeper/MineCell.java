package tv.jomo.minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.StrokeBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BasicStroke;

@SuppressWarnings("serial")
class MineCell extends JButton {
  Boolean bomb = false;
  Boolean flagged = false;
  // neighbour count
  Integer bombs = 0;
  // position in the MineField
  Integer x;
  Integer y;

  MineCell(Integer x, Integer y) {
    super();
    this.x = x;
    this.y = y;
    setPreferredSize(new Dimension(50, 50));
    setupListener();
    initStyle();
  }

  // sets the inital JButton style
  void initStyle() {
    setText("");
    setEnabled(true);
    setOpaque(true);
    setBackground(new Color(0x4C, 0x9E, 0xD9));
    setForeground(Color.BLACK);
    // the border adds some whitespace between each MineCell in the field
    setBorder(new StrokeBorder(new BasicStroke(), Color.WHITE));
  }

  // adds mouse events
  private void setupListener() {
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (isEnabled()) {
          // right click
          if (e.getButton() == MouseEvent.BUTTON3) {
            toggleFlag();
          // left click
          } else if (e.getButton() == MouseEvent.BUTTON1) {
            flagged = false;
            if (bomb) {
              // clicked on a bomb
              Game.getInstance().lose();
            } else {
              if (bombs == 0) {
                // no surrounding bombs
                Game.getInstance().field.clearCells(MineCell.this); // this = MouseAdapter
              } else {
                reveal();
              }
            }
          }
          // check if game was won
          Game.getInstance().field.checkWin();
        }
      }
    });
  }

  // toggles the `flagged` state and sets the text
  void toggleFlag() {
    flagged = !flagged;
    setText(flagged ? "⚑" : "");
  }

  // shows the number of bombs nearby
  // or if the cell itself is a bomb
  void reveal() {
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