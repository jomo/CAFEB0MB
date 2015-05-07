package tv.jomo.minesweeper;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    JMenuItem newGameMenu = new JMenuItem("New Game");
    newGameMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });
    menuBar.add(newGameMenu);

    JMenuItem gamePropertyMenu = new JMenuItem("Game Properties");
    gamePropertyMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Game.this, "TODO", "Game Properties", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menuBar.add(gamePropertyMenu);

    // Glue = Spacer (because Java)
    menuBar.add(Box.createHorizontalGlue());

    JMenuItem aboutMenu = new JMenuItem("About");
    aboutMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(Game.this, "Created by jomo in 2015\nSource Code: https://github.com/jomo/CAFEB0MB/issues/2", "About CAFEB0MB", JOptionPane.PLAIN_MESSAGE);
      }
    });
    menuBar.add(aboutMenu);

    setJMenuBar(menuBar);
  }

  private void start() {
    if (field != null) {
      remove(field);
    }
    field = new MineField(15, 10, 10);
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
    JOptionPane.showMessageDialog(this, "You suck at this game.", "Lose", JOptionPane.PLAIN_MESSAGE);
    start();
  }
}