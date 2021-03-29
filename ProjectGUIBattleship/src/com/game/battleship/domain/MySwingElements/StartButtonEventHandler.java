package com.game.battleship.domain.MySwingElements;

import com.game.battleship.GUI.ClickableGridGUI;
import com.game.battleship.controller.Controller.AppController;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * The event handler for the start button, it extends MyButtonOnClick
 *
 * @author Ravasz Tamás
 * @see MyButtonOnClick
 */
public class StartButtonEventHandler extends MyButtonOnClick {

    private AppController controller;
    private ClickableGridGUI gui;
    private int gridSizeX = 0;
    private int gridSizeY = 0;
    private ArrayList<JButton> buttonsOfPlayer;

    /**
     * Constructor for the handler
     *
     * @param gui             ClickableGridGUI - The handler belongs to this gui
     * @param controller      AppController - the controller of the application
     * @param buttonsOfPlayer ArrayList - The JButtons of the grid of the player
     * @param gridSizeX       int - number of rows of the grid
     * @param gridSizeY       int - number of columns of the grid
     */
    public StartButtonEventHandler(ClickableGridGUI gui, AppController controller, ArrayList<JButton> buttonsOfPlayer, int gridSizeX, int gridSizeY) {
        super();
        this.controller = controller;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.buttonsOfPlayer = buttonsOfPlayer;
        this.gui = gui;
    }

    /**
     * Handles the start button, changes the game state to active game state,
     * If all of the ships are not placed in notifies the user accordingly thorough the message label
     *
     * @param e MouseEvent - the click event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (controller.canStart()) {
            gui.changeToActiveGameState();
            JButton source = (JButton) e.getSource();
            source.setEnabled(false);

        } else {
            gui.setMessageLabel("Can't start, place all ships!");
        }
    }


}
