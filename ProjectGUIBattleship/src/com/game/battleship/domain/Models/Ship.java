package com.game.battleship.domain.Models;

import com.game.battleship.MyExceptions.ShipException;

import java.awt.*;
import java.util.ArrayList;

/**
 * A ship object which represents the ship on the grid. It has a head which has it's coordinates stored
 * the length of the ship and the ship's direction (it can be 0 (horizontal) or 1 (vertical))
 *
 * @author Ravasz Tamás
 */
public class Ship {

    private int direction;
    private int health;
    private Point start;
    private int length;
    final private int[] DirectionX = {0, 1};
    final private int[] DirectionY = {1, 0};
    private boolean destroyed = false;

    /**
     * Set's the value of the destroyed boolean value to the value of the parameter
     *
     * @param destroyed boolean - the value to which the ship's destroyed value is set
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Copy constructor of the ship object
     *
     * @param ship Ship- - The ship from which we copy the data
     */
    public Ship(Ship ship) {
        this.direction = ship.getDirection();
        this.start = ship.getStart();
        this.length = ship.getLength();
        this.health = ship.getHealth();
    }

    /**
     * Sets the direction of the ship
     *
     * @param direction int - the direction of the ship
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Sets the value of the start/head coordinate
     *
     * @param start Point - new start point of the ship
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Sets the value for the length of the ship
     *
     * @param length int - the new length of the ship
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * Checks if two ships are equal or that they overlap
     * If two ships overlap they are considered equal
     *
     * @param obj the compared object
     * @return true if it is a ship and that they overlap or are equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Ship))
            return false;
        Ship otherShip = (Ship) obj;
        int thisX = start.x;
        int thisY = start.y;
        for (int i = 0; i < this.length; i++) {
            int otherX = otherShip.getStart().x;
            int otherY = otherShip.getStart().y;
            for (int j = 0; j < otherShip.length; j++) {
                if (otherX == thisX && otherY == thisY)
                    return true;
                otherX += DirectionX[otherShip.direction];
                otherY += DirectionY[otherShip.direction];
            }
            thisX += DirectionX[this.direction];
            thisY += DirectionY[this.direction];

        }
        return false;
    }

    /**
     * Constructor of the ship
     *
     * @param start     Point - Start/Head of the ship
     * @param length    int - length of the ship
     * @param direction int - direction of the ship, can be 0 or 1/horizontal or vertical
     */
    public Ship(Point start, int length, int direction) {
        this.direction = direction;
        this.start = start;
        this.length = length;
        this.health = length;
    }

    /**
     * Getter for the direction
     *
     * @return int - the direction of the ship
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Generates the points on which the ships resides and
     * returns them in an ArrayList
     *
     * @return ArrayList - an array list of points which represent the coordinates of the ship
     */
    public ArrayList<Point> generatePoints() {
        ArrayList<Point> points = new ArrayList<>();
        int AuxX = start.x;
        int AuxY = start.y;
        for (int i = 0; i < this.length; i++) {
            points.add(new Point(AuxX, AuxY));
            AuxX += DirectionX[this.direction];
            AuxY += DirectionY[this.direction];
        }
        return points;
    }

    /**
     * Getter for the health
     *
     * @return Point - the head of the ship
     */
    public Point getStart() {
        return start;
    }

    /**
     * Getter for the head/start
     *
     * @return int - the length of the ship
     */
    public int getLength() {
        return length;
    }

    /**
     * Getter for the length
     *
     * @return int - the health of the ship
     */
    public int getHealth() {
        return health;
    }

    /**
     * Decreases the health of the ship, if the health, if the new health is 0 it returns true otherwise false
     *
     * @return boolean - true if the ship's new health is 0, false - otherwse
     * @throws ShipException if the ship's health is already at 0 and the function is calledm then it throws the exception, the health can't be negative
     */
    public boolean decreaseHealth() throws ShipException {
        if (health == 0)
            throw new ShipException("Health already 0!");
        health--;
        if (health == 0)
            return true;
        return false;
    }

    /**
     * Checks if the ship is destroyed or not
     *
     * @return true - if the ship is destroyed, false - if the ship is not destroyed
     */
    public boolean isDestoryed() {
        return destroyed;
    }
}
