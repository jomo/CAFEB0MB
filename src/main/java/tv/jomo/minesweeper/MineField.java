package tv.jomo.minesweeper;

import javax.swing.JPanel;
import java.awt.GridLayout;

@SuppressWarnings("serial")
class MineField extends JPanel {
  Integer width;
  Integer height;
  Integer bombs;
  // 2D array storing the MineCells
  private MineCell[][] cells;

  // creates a new MineField
  // `width` and `height` define the MineCell count
  // `bombs` defines the number of bombs randomly spread across the field
  MineField(Integer width, Integer height, Integer bombs) {
    super();
    this.bombs = bombs;
    this.width = width;
    this.height = height;
    cells = new MineCell[width][height];
    generateField();
    addBombs();
    setBombCount();
  }

  // removes and adds MineCells in a GridLayout to the field
  private void generateField() {
    removeAll();
    setLayout(new GridLayout(height, width));
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = new MineCell(x, y);
        cells[x][y] = cell;
        add(cell);
      }
    }
  }

  // sets random cells across the field to bombs
  private void addBombs() {
    Integer count = 0;
    while (count < bombs) {
      Integer x = (int) (Math.random() * width);
      Integer y = (int) (Math.random() * height);
      MineCell bombcell = cells[x][y];
      if (!bombcell.bomb) {
        bombcell.bomb = true;
        count++;
      }
    }
  }

  // sets the count of neighbouring bombs for each cell on the field
  private void setBombCount() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = cells[x][y];
        if (cell.bomb) {
          countBombs(cell);
        }
      }
    }
  }

  // increments neighbouring cell's bomb count
  private void countBombs(MineCell bomb) {
    Integer count = 0;
     for (Integer ymod = -1; ymod <= 1; ymod++) {
      for (Integer xmod = -1; xmod <= 1; xmod++) {
        Integer x = bomb.x + xmod;
        Integer y = bomb.y + ymod;
        if (x >= 0 && x < width && y >= 0 && y < height) {
          cells[x][y].bombs++;
        }
      }
    }
  }

  // reveal all cells
  void solve() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        cells[x][y].reveal();
      }
    }
  }

  // reset all cells
  void reset() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        cells[x][y].initStyle();
      }
    }
  }

  // check if all flags are correctly set and win the game
  void checkWin() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = cells[x][y];
        if (cell.bomb != cell.flagged || (!cell.flagged && cell.isEnabled())) {
          return;
        }
      }
    }
    Game.getInstance().win();
  }

  // automatically clears cells that have no neighbouring bombs
  void clearCells(MineCell center) {
    for (Integer xmod = -1; xmod <= 1; xmod++) {
      for (Integer ymod = -1; ymod <= 1; ymod++) {
        Integer x = center.x + xmod;
        Integer y = center.y + ymod;
        if (x >= 0 && x < width && y >= 0 && y < height) {
          MineCell cell = cells[x][y];
          if (cell.isEnabled() && !cell.bomb && !cell.flagged) {
            cell.reveal();
            if (cell.bombs == 0) {
              clearCells(cell);
            }
          }
        }
      }
    }
  }
}