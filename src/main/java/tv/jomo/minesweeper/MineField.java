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

  private void setBombCount() {
    for (Integer y = 0; y < height; y++) {
      for (Integer x = 0; x < width; x++) {
        MineCell cell = cells[x][y];
        cell.bombs = countBombs(cell);
      }
    }
  }

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

  private boolean isSolvable() {
    // TODO: flood fill the field
    // and check if all non-bombs are 'filled'
    return true;
  }

  public MineCell getCell(Integer x, Integer y) {
    return cells[x][y];
  }

  public void explodeBombs() {
    // TODO: iterate over cells, explode bombs
  }

  public void clearCells(Integer x, Integer y) {
    // TODO: clear neighbouring cells where count == 0
  }
}