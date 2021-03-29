package com.game.battleship.GUI;

import com.game.battleship.controller.Controller.AppController;
import com.game.battleship.controller.aicontroller.AIController;
import com.game.battleship.domain.Models.Hit;
import com.game.battleship.domain.MySwingElements.*;
import com.game.battleship.domain.Models.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A clickble GUI where there are two N x M sized grids, N and M are integers larger than zero
 * and a button the handle the start and restart of the program. It extends JFrame
 *
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html">JFrame</a>
 */
public class ClickableGridGUI extends JFrame {
    private int gridSizeX = 10;
    private int gridSizeY = 10;
    private ArrayList<JButton> playerButtons = new ArrayList<>();
    private ArrayList<JButton> computerButtons = new ArrayList<>();
    private final Color standardPlayerColor = Color.BLUE;
    private final Color standardPlayerShipHeadColor = Color.YELLOW;
    private final Color standardPlayerShipColor = Color.CYAN;
    private final Color standardPlayerHitColor = Color.BLACK;
    private final Color standardPlayerCorrectHitColor = Color.GREEN;
    private final Color standardComputerColor = Color.RED;
    private final Color standardComputerHitColor = Color.BLACK;
    private final Color standardComputerCorrectHitColor = Color.GREEN;
    private final Color standardDestroyedColor = new Color(102, 0, 153);
    private AppController controller;
    private AIController aiController;
    private JPanel mainPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JLabel messageLabel = new JLabel();
    public JLabel AiMessageLabel = new JLabel();
    private JButton startButton = new JButton();
    private GridLayout grid1;
    private GridLayout grid2;
    private MyButtonOnClick eventStartPlayer;
    private MyButtonOnClick eventGamePlayer;
    private MyButtonOnClick starButtonStart;
    private MyButtonOnClick startButtonRestart;

    /**
     * Constructor for the GUI
     *
     * @param controller   Application controller to handle user input
     * @param aiController Controller to manage the input of the Artificial intelligence
     */
    public ClickableGridGUI(AppController controller, AIController aiController) {
        this(10, 10, controller, aiController);
    }

    /**
     * The constructor for the grid. Prepares the GUI for the user.
     *
     * @param gridSizeX    Number of rows in the grid
     * @param gridSizeY    Number of columns in the grid
     * @param controller   Application controller to handle user input
     * @param aiController Controller to manage the input of the Artificial intelligence
     */
    public ClickableGridGUI(int gridSizeX, int gridSizeY, AppController controller, AIController aiController) {
        super("Battleship");
        this.controller = controller;
        this.aiController = aiController;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        eventStartPlayer = new MyGridButtonOnClickEventStartPlayer(this, controller, playerButtons, gridSizeX, gridSizeY);
        eventGamePlayer = new MyButtonOnClickEventGamePlayer(this, controller, aiController);
        starButtonStart = new StartButtonEventHandler(this, controller, playerButtons, gridSizeX, gridSizeY);
        startButtonRestart = new RestartButtonEventHandler(this, controller, playerButtons, computerButtons, gridSizeX, gridSizeY);
        grid1 = new GridLayout(gridSizeX, gridSizeY);
        grid2 = new GridLayout(gridSizeX, gridSizeY);
        leftPanel.setLayout(grid1);
        rightPanel.setLayout(grid2);

        for (int i = 0; i < gridSizeX; i++)
            for (int j = 0; j < gridSizeY; j++) {
                ///first button
                GridButton btn = new GridButton("");
                btn.setMyX(i);
                btn.setMyY(j);
                btn.addMouseListener(eventStartPlayer);
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setBackground(this.standardPlayerColor);
                btn.setOpaque(true);
                playerButtons.add(btn);
                //System.out.println(btn.getX() + " " + btn.getY());
                leftPanel.add(btn);
                ///second button
                btn = new GridButton("");
                btn.setMyX(i);
                btn.setMyY(j);
                btn.setPreferredSize(new Dimension(40, 40));
                btn.setBackground(this.standardComputerColor);
                btn.setOpaque(true);
                computerButtons.add(btn);
                //System.out.println(btn.getX() + " " + btn.getY());
                rightPanel.add(btn);


            }
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.add(leftPanel);


        middlePanel.setLayout(new GridLayout(3, 1));

        startButton.addMouseListener(starButtonStart);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AiMessageLabel.setVerticalAlignment(SwingConstants.CENTER);
        AiMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        middlePanel.add(messageLabel);
        middlePanel.add(AiMessageLabel);
        startButton.setText("Start");
        middlePanel.add(startButton);

        mainPanel.add(middlePanel);//spacer

        mainPanel.add(rightPanel);

        add(mainPanel);

        changeToStartGameState();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationByPlatform(true);
        setVisible(true);

    }

    /**
     * The function changes the game state to active game state
     * meaning the player can't place anymore ships, and can attack the enemy grid.
     * The enemy AI can also attack after this.
     */
    public void changeToActiveGameState() {
        for (JButton btn : playerButtons) {
            btn.removeMouseListener(eventStartPlayer);
        }
        for (JButton btn : computerButtons) {
            btn.addMouseListener(eventGamePlayer);
        }
    }

    /**
     * Updates the screen without any new message.
     * It also updates the grids, refreshing all the elements of the grids.
     */
    public void Update() {
        this.Update("");
    }

    /**
     * Updates the GUI using the message as paramater, the message is printed out on the screen
     * using the player message label
     *
     * @param message The string which contains the message to be printed on the screen
     *                it also updates the grids, refreshing all the elements of the grids.
     */
    public void Update(String message) {
        ArrayList<Ship> playerShips = controller.getAllPlayerShips();
        ArrayList<Ship> computerShips = controller.getAllComputerShips();
        ArrayList<Hit> computerHits = controller.getAllComputerHits();
        ArrayList<Hit> playerHits = controller.getAllPlayerHits();
        for (int i = 0; i < gridSizeX; i++) {
            for (int j = 0; j < gridSizeY; j++) {
                JButton btn = playerButtons.get(i * gridSizeX + j);
                btn.setBackground(this.standardPlayerColor);
                btn = computerButtons.get(i * gridSizeX + j);
                btn.setBackground(this.standardComputerColor);

            }
        }
        for (Ship ship : playerShips) {
            var points = ship.generatePoints();
            JButton btn = playerButtons.get(points.get(0).x * gridSizeX + points.get(0).y);
            btn.setBackground(this.standardPlayerShipHeadColor);
            for (int i = 1; i < points.size(); i++) {
                btn = playerButtons.get(points.get(i).x * gridSizeX + points.get(i).y);
                btn.setBackground(this.standardPlayerShipColor);
            }

        }
        for (Hit hit : computerHits) {
            JButton btn = playerButtons.get(hit.x * gridSizeX + hit.y);
            if (hit.shipWasHit)
                btn.setBackground(this.standardComputerCorrectHitColor);
            else
                btn.setBackground(this.standardComputerHitColor);

        }
        for (Hit hit : playerHits) {
            JButton btn = computerButtons.get(hit.x * gridSizeX + hit.y);
            if (hit.shipWasHit)
                btn.setBackground(this.standardPlayerCorrectHitColor);
            else
                btn.setBackground(this.standardPlayerHitColor);

        }
        for (Ship ship : playerShips) {
            if (ship.isDestoryed()) {
                var points = ship.generatePoints();
                for (int i = 0; i < points.size(); i++) {
                    JButton btn = playerButtons.get(points.get(i).x * gridSizeX + points.get(i).y);
                    btn.setBackground(this.standardDestroyedColor);
                }

            }
        }
        for (Ship ship : computerShips) {
            if (ship.isDestoryed()) {
                var points = ship.generatePoints();
                for (int i = 0; i < points.size(); i++) {
                    JButton btn = computerButtons.get(points.get(i).x * gridSizeX + points.get(i).y);
                    btn.setBackground(this.standardDestroyedColor);
                }

            }
        }

        messageLabel.setText(message);
    }

    /**
     * Sets the text for the player message label using a string as parameter
     *
     * @param message The message to be printed on the screen
     */
    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }

    /**
     * Disables all the grid buttons of the grid. This should be used in case a player wins the game
     * and removes their event handlers
     */
    public void disableAllGridButtons() {
        for (JButton btn : playerButtons) {
            btn.setEnabled(false);
        }
        for (JButton btn : computerButtons) {
            btn.setEnabled(false);
            btn.removeMouseListener(eventGamePlayer);
        }
    }

    /**
     * The restart labeled button is set again to start, and it's event handler is changed to the start event handler
     */
    public void switchStartRestart() {
        if (!startButton.isEnabled()) {
            startButton.setEnabled(true);
            startButton.setText("Restart");
            startButton.removeMouseListener(starButtonStart);
            startButton.addMouseListener(startButtonRestart);
        }
    }

    /**
     * Changes the game state to start state, reseting all data for AI
     * and for the player.
     */
    public void changeToStartGameState() {

        controller.clearAll();
        aiController.AISetUp();
        messageLabel.setText("");
        AiMessageLabel.setText("");
        eventGamePlayer.reset();
        this.Update();
    }

    /**
     * Activates all the buttons of the two game grids and sets their specific event handlers
     */
    public void activateAll() {
        startButton.addMouseListener(starButtonStart);
        for (JButton btn : playerButtons) {
            btn.setEnabled(true);
            btn.addMouseListener(eventStartPlayer);
        }
        for (JButton btn : computerButtons) {
            btn.setEnabled(true);
        }

    }

    /**
     * After an AI action this method is called to refresh the AI message label
     *
     * @param message The message to be printed out
     */
    public void UpdateAI(String message) {
        AiMessageLabel.setText(message);
        this.Update(this.messageLabel.getText());
    }
}
