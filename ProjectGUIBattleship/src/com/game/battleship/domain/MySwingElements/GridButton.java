package com.game.battleship.domain.MySwingElements;

import javax.swing.*;

/**
 * A JButton to which additional 2D coordinates can be assigned to make it
 * easier to represent in a grid, it extends JButton
 *
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/swing/JButton.html">JButton</a>
 */
public class GridButton extends JButton {

    private int X;
    private int Y;

    /**
     * Constructor for the Grid Button
     *
     * @param param String - label on the grid button
     */
    public GridButton(String param) {
        super(param);
    }

    /**
     * Setter for the X coordinate
     *
     * @param x int - new value
     */
    public void setMyX(int x) {
        X = x;
    }

    /**
     * Setter for the Y coordinate
     *
     * @param y int - new value
     */
    public void setMyY(int y) {
        Y = y;
    }

    /**
     * Getter for the X Coordinate
     *
     * @return int - value of X
     */
    public int getMyX() {
        return X;
    }

    /**
     * Getter for the Y Coordinate
     *
     * @return int - value of Y
     */
    public int getMyY() {
        return Y;
    }

}
