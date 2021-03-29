package com.game.battleship.domain.MySwingElements;

import com.game.battleship.GUI.ClickableGridGUI;
import com.game.battleship.controller.Controller.AppController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The event handler for the restart button, it extends MyButtonOnClick
 *
 * @author Ravasz Tamás
 * @see MyButtonOnClick
 */
public class RestartButtonEventHandler extends MyButtonOnClick {

    private AppController controller;
    private ClickableGridGUI gui;
    private int gridSizeX = 0;
    private int gridSizeY = 0;
    private ArrayList<JButton> buttonsOfPlayer;
    private ArrayList<JButton> buttonsOfComputer;

    /**
     * Constructor for the handler
     *
     * @param gui               ClickableGridGUI - The handler belongs to this gui
     * @param controller        AppController - the controller of the application
     * @param buttonsOfPlayer   ArrayList - The JButtons of the grid of the player
     * @param buttonsOfComputer ArrayList - the JButtons of the grid of the computer
     * @param gridSizeX         int - number of rows of the grid
     * @param gridSizeY         int - number of columns of the grid
     */
    public RestartButtonEventHandler(ClickableGridGUI gui, AppController controller, ArrayList<JButton> buttonsOfPlayer, ArrayList<JButton> buttonsOfComputer, int gridSizeX, int gridSizeY) {
        super();
        this.controller = controller;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.buttonsOfPlayer = buttonsOfPlayer;
        this.buttonsOfPlayer = buttonsOfComputer;
        this.gui = gui;
    }

    /**
     * Handles the start button, changes the game state to start game state,
     *
     * @param e MouseEvent - the click event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JButton source = (JButton) e.getSource();
        source.setEnabled(true);
        source.setText("Start");
        source.removeMouseListener(this);
        gui.activateAll();
        gui.changeToStartGameState();
    }

}
