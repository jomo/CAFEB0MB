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
    int count = 0;
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

  private boolean isSolvable() {
    // TODO: flood fill the field
    // and check if all non-bombs are 'filled'
    return true;
  }

  public MineCell getCell(int x, int y) {
    return cells[x][y];
  }

  public void explodeBombs() {
    // TODO: iterate over cells, explode bombs
  }

  public void clearCells(int x, int y) {
    // TODO: clear neighbouring cells where count == 0
  }

  public int countBombs() {
    // TODO: count nearby bombs
    return 0;
  }
}