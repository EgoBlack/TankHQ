package PanzerHQ;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    private final GamePanel panzerGamePanel;
    private final ChooseTankColorDialog chooseTankColorDialog;

    // Konstruktor für das Spielfenster
    public GameWindow() {
        // Erstellen eines Panels (Testpanel) für den Inhalt des Fensters
        this.panzerGamePanel = new GamePanel();
        chooseTankColorDialog = new ChooseTankColorDialog(this, panzerGamePanel);
        registerWindowListener();
        createMenu();

        add(panzerGamePanel);
        pack();

        setTitle("PanzerHQ");
        setLocation(10, 10);
        setResizable(false);

        setVisible(true);
    }

    private void registerWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // hier werden wir später unser Spiel pausieren
                panzerGamePanel.pauseGame();
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // hier werden wir später unser Spiel wieder fortsetzen
                panzerGamePanel.continueGame();
            }
        });
    }

    // Methode zum Erstellen der Menüleiste
    private void createMenu() {
        // Erstellen einer Menüleiste
        JMenuBar menuBar = new JMenuBar();

        // Menüleiste dem JFrame hinzufügen
        this.setJMenuBar(menuBar);

        // Erstellen der einzelnen Menüs
        JMenu fileMenu = new JMenu("File");  // Menü "File" für Dateiaktionen
        JMenu gameMenu = new JMenu("Game");  // Menü "Game" für Spielaktionen
        JMenu prefMenu = new JMenu("Preferences");  // Menü "Preferences" für Einstellungen

        // Menüs zur Menüleiste hinzufügen
        menuBar.add(fileMenu);
        menuBar.add(gameMenu);
        menuBar.add(prefMenu);

        // Menüeinträge zum "File"-Menü hinzufügen
        addFileMenuItems(fileMenu);
        addGameMenuItems(gameMenu);
        addPrefMenuItems(prefMenu);
    }

    // Methode zum Hinzufügen der Menüeinträge für das "File"-Menü
    private void addFileMenuItems(JMenu fileMenu) {
        // Erstellen eines Menüeintrags "Quit" zum Beenden des Spiels
        JMenuItem quitItem = new JMenuItem("Quit");

        // "Quit"-Eintrag dem "File"-Menü hinzufügen
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addGameMenuItems(JMenu gameMenu) {
        JMenuItem pauseItem = new JMenuItem("Pause");
        gameMenu.add(pauseItem);
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.pauseGame();
            }
        });

        JMenuItem continuetItem = new JMenuItem("Continue");
        gameMenu.add(continuetItem);
        continuetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.continueGame();
            }
        });

        gameMenu.addSeparator();
        JMenuItem restartItem = new JMenuItem("Restart");
        gameMenu.add(restartItem);
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.restartGame();
            }
        });
    }

    private void addPrefMenuItems(JMenu prefMenu) {
        JMenuItem changeColorItem = new JMenuItem("Change Tank's Colors...");
        prefMenu.add(changeColorItem);

        changeColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //panzerGamePanel.getPlayersTank().setTurretColor(java.awt.Color.YELLOW);
                panzerGamePanel.pauseGame();
                chooseTankColorDialog.pack();
                chooseTankColorDialog.setLocation(300,200);
                chooseTankColorDialog.setVisible(true);
            }
        });

        JMenu submenu = new JMenu("Choose Background");
        prefMenu.add(submenu);

        JMenuItem menuItem = new JMenuItem("Mud Area");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.setBackgroundImage(0);
                repaint();
            }
        });

        menuItem = new JMenuItem("Snow Area");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.setBackgroundImage(1);
                repaint();
            }
        });
        menuItem = new JMenuItem("Desert Area");
        submenu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panzerGamePanel.setBackgroundImage(2);
                repaint();
            }
        });
    }
}