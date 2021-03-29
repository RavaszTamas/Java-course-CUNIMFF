package com.game.battleship.domain.MySwingElements;

import com.game.battleship.GUI.ClickableGridGUI;
import com.game.battleship.MyExceptions.ControllerException;
import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.controller.Controller.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This is the event handler for the grid buttons when they are in the start game state, for the player grid, it extends MyButtonOnClick
 *
 * @author Ravasz Tamás
 * @see MyButtonOnClick
 */
public class MyGridButtonOnClickEventStartPlayer extends MyButtonOnClick {
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
    public MyGridButtonOnClickEventStartPlayer(ClickableGridGUI gui, AppController controller, ArrayList<JButton> buttonsOfPlayer, int gridSizeX, int gridSizeY) {
        super();
        this.controller = controller;
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.buttonsOfPlayer = buttonsOfPlayer;
        this.gui = gui;
    }

    /**
     * The UI event for adding a ship or rotating it
     *
     * @param e the event parameter
     */
    void UIAddShip(MouseEvent e) {
        GridButton clicked = (GridButton) e.getSource();
        int DIRECTION = 0;
        if (buttonsOfPlayer.get(clicked.getMyX() * gridSizeX + clicked.getMyY()).getBackground().equals(Color.YELLOW)) {
            try {
                controller.rotateShip(clicked.getMyX(), clicked.getMyY());
            } catch (ValidatorException ex) {

            } catch (RepositoryException | ControllerException ex) {
                gui.setMessageLabel(ex.getMessage());
            }
            gui.Update();
            return;
        }
        int SIZE = controller.getNextSize();
        if (SIZE == -1) {
            gui.setMessageLabel("No more ships!");
            return;
        }
        //System.out.println(clicked.getMyX() + " " + clicked.getMyY());
        try {
            controller.addShip(clicked.getMyX(), clicked.getMyY(), SIZE, DIRECTION);
        } catch (ValidatorException ex) {

            try {
                DIRECTION = 1;
                controller.addShip(clicked.getMyX(), clicked.getMyY(), SIZE, DIRECTION);
            } catch (ValidatorException | RepositoryException exc) {
                gui.setMessageLabel(exc.getMessage());
                return;
            }


        } catch (RepositoryException ex) {
            gui.setMessageLabel(ex.getMessage());
            return;
        }
        gui.Update();
    }

    /**
     * The UI event for deleting the ship
     *
     * @param e the event parameter
     */
    void UIDeleteShip(MouseEvent e) {
        GridButton clicked = (GridButton) e.getSource();
        try {
            controller.removeShip(clicked.getMyX(), clicked.getMyY());
            gui.Update();

        } catch (RepositoryException ex) {
            gui.setMessageLabel(ex.getMessage());
        }
    }

    /**
     * The event when the mouse is used, if the left click is used then a ship is added if possible, if the right click is used then
     * a ship is deleted if possible otherwise nothing happens
     *
     * @param e the event parameter
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            UIAddShip(e);
        else if (e.getButton() == MouseEvent.BUTTON3)
            UIDeleteShip(e);

    }
}
