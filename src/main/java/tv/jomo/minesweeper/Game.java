package tv.jomo.minesweeper;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {

  private static Game instance = new Game("CAFEB0MB");
  public String difficulty;
  public MineField field;

  public static synchronized Game getInstance() {
    return instance;
  }

  private Game(String s) {
    super(s);
    addMenu();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    start();
    setResizable(false);
    setVisible(true);
  }

  private void addMenu() {
    JMenuBar menuBar = new JMenuBar();

    JButton newGameMenu = new JButton("New Game");
    newGameMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });
    menuBar.add(newGameMenu);

    JButton gamePropertyMenu = new JButton("Game Properties");
    gamePropertyMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Game.this, "TODO", "Game Properties", JOptionPane.PLAIN_MESSAGE);
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

  private void start() {
    if (field != null) {
      remove(field);
    }
    field = new MineField(10, 10, 10);
    add(field);
    pack(); // auto resize
  }

  public void win() {
    field.solve();
    JOptionPane.showMessageDialog(this, "Well done!", "Win", JOptionPane.PLAIN_MESSAGE);
    start();
  }

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