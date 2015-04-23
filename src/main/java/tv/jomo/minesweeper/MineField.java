package tv.jomo.minesweeper;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;


public class MineField extends JPanel {
  public Integer width;
  public Integer height;
  public Integer bombs;
  private GridLayout grid;
  private MineCell[][] cells;

  // creates a new MineField
  // width and height define the MineCell count
  // bombs defines the number of bombs randomly spread across the field
  public MineField(Integer width, Integer height, Integer bombs) {
    super();
    this.bombs = bombs;
    this.width = width;
    this.height = height;
    cells = new MineCell[width][height];
    generateField();
    addBombs();
    setBombCount();
  }

  // adds MineCells in a GridLayout to the field
  private void generateField() {
    do {
      removeAll();
      setLayout(new GridLayout(width, height));
      for (Integer y = 0; y < height; y++) {
        for (Integer x = 0; x < width; x++) {
          MineCell cell = new MineCell(x, y);
          cells[x][y] = cell;
          add(cell);
        }
      }
    } while (!isSolvable());
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

  // passes the count of neighbouring bombs to each cell on the field
  private void setBombCount() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = cells[x][y];
        cell.bombs = countBombs(cell);
      }
    }
  }

  // counts neighbouring bombs for the cell
  private Integer countBombs(MineCell cell) {
    Integer count = 0;
    for (Integer xmod = -1; xmod <= 1; xmod++) {
      for (Integer ymod = -1; ymod <= 1; ymod++) {
        Integer x = cell.x + xmod;
        Integer y = cell.y + ymod;
        if (x >= 0 && x < width && y >= 0 && y < height) {
          MineCell neighbour = cells[x][y];
          if (neighbour.bomb) {
            count++;
          }
        }
      }
    }
    return count;
  }

  // returns true if the field can be logically solved
  // returns false otherwise
  private boolean isSolvable() {
    // TODO: flood fill the field
    // and check if all non-bombs are 'filled'
    return true;
  }

  // reveal all cells
  public void solve() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        cells[x][y].reveal();
      }
    }
  }

  public void checkWin() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = cells[x][y];
        if (cell.bomb != cell.flagged) {
          return;
        }
      }
    }
    Game.getInstance().win();
  }

  // automatically clears cells that have no neighbouring bombs
  public void clearCells(MineCell center) {
    for (Integer xmod = -1; xmod <= 1; xmod++) {
      for (Integer ymod = -1; ymod <= 1; ymod++) {
        Integer x = center.x + xmod;
        Integer y = center.y + ymod;
        if (x >= 0 && x < width && y >= 0 && y < height) {
          MineCell cell = cells[x][y];
          if (cell.isEnabled() && !cell.bomb) {
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