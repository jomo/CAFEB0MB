package tv.jomo.minesweeper;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {

  // initalize a new Game instance
  private static Game instance = new Game("CAFEB0MB");

  public String difficulty;
  public MineField field;
  private Integer width = 10;
  private Integer height = 10;
  private Integer bombs = 10;

  // allows to access the Game instance from public space
  public static synchronized Game getInstance() {
    return instance;
  }

  // initalizes a new Game
  private Game(String s) {
    super(s);
    addMenu();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    start();
    setResizable(false);
    setVisible(true);
  }

  // adds the Menu bar
  // this includes the "New Game", "Game Properties", and "About" menus
  private void addMenu() {
    JMenuBar menuBar = new JMenuBar();

    JButton newGameMenu = new JButton("New Game");
    newGameMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });
    menuBar.add(newGameMenu);

    // Game Properties asks for width, height, and number of bombs
    JButton gamePropertyMenu = new JButton("Game Properties");
    gamePropertyMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Integer new_width = field.width;
        Integer new_height = field.height;
        Integer new_bombs = field.bombs;
        do {
          Object result = JOptionPane.showInputDialog(Game.this, "Enter the number of columns:", "Field width", JOptionPane.PLAIN_MESSAGE, null, null, field.width);
          if (result == null) {
            return; // Cancelled
          }
          try {
            new_width = Integer.parseInt(result.toString());
          } catch(Exception ex) {
            new_width = 0;
          }
        } while (new_width < 4 || new_width > 30);
        do {
          Object result = JOptionPane.showInputDialog(Game.this, "Enter the number of rows:", "Field height", JOptionPane.PLAIN_MESSAGE, null, null, field.height);
          if (result == null) {
            return; // Cancelled
          }
          try {
            new_height = Integer.parseInt(result.toString());
          } catch(Exception ex) {
            new_height = 0;
          }
        } while (new_height < 4 || new_height > 30);
        do {
          Object result = JOptionPane.showInputDialog(Game.this, "Enter the number of bombs in the field:", "Bombs", JOptionPane.PLAIN_MESSAGE, null, null, field.bombs);
          if (result == null) {
            return; // Cancelled
          }
          try {
            new_bombs = Integer.parseInt(result.toString());
          } catch(Exception ex) {
            new_bombs = 0;
          }
        } while (new_bombs < 1 || new_bombs >= new_width * new_height);
        width = new_width;
        height = new_height;
        bombs = new_bombs;
        start();
      }
    });
    menuBar.add(gamePropertyMenu);

    // Glue = Spacer (because Java)
    menuBar.add(Box.createHorizontalGlue());

    JButton aboutMenu = new JButton("About");
    aboutMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Game.this, "Created by jomo in 2015\nSource Code: https://github.com/jomo/CAFEB0MB", "About CAFEB0MB", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menuBar.add(aboutMenu);

    setJMenuBar(menuBar);
  }

  // reset the game and start a new one
  private void start() {
    if (field != null) {
      remove(field);
    }
    field = new MineField(width, height, bombs);
    add(field);
    pack(); // auto resize
  }

  // solve the game and show the win message
  public void win() {
    field.solve();
    JOptionPane.showMessageDialog(this, "Well done!", "Win", JOptionPane.PLAIN_MESSAGE);
    start();
  }

  // solve the game and show the lose message with "New Game" and "Retry" options
  public void lose() {
    field.solve();
    Object[] options = { "New Game", "Retry" };
    if (JOptionPane.showOptionDialog(null, "You suck at this game.", "Lose", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]) > 0) {
      field.reset();
    } else {
      start();
    }
  }
}