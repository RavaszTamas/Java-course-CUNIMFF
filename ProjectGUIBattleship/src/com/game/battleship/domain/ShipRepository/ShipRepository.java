package com.game.battleship.domain.ShipRepository;

import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.MyExceptions.ShipException;
import com.game.battleship.domain.Models.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Repository of the ship, used to store the ships on the grid
 */
public class ShipRepository {
    private ArrayList<Ship> ships = new ArrayList<>();
    private HashMap<Integer, Integer> shipTypes = new HashMap<>();
    final private int[] DirectionX = {0, 1};
    final private int[] DirectionY = {1, 0};

    /**
     * Constructor for the ship repository
     * Here the type and number of possible existing instance of ships of that type is defined
     */
    public ShipRepository() {
        shipTypes.put(2, 2);
        shipTypes.put(3, 1);
        shipTypes.put(4, 1);
    }

    /**
     * Returns the next available ship type(length) to be put on the screen
     *
     * @return int - type of the ship (length)
     */
    public int getNextSize() {
        for (var type : shipTypes.keySet()) {
            if (shipTypes.get(type) != 0)
                return type;
        }
        return -1;
    }

    /**
     * Adds a ship to the repository, if it is a valid new ship, meaning no overlap
     *
     * @param newShip Ship - the new  ship to be added
     * @throws RepositoryException if there is an overlap or the repository runs out of available slots for the length/type of the ship
     */
    public void addShip(Ship newShip) throws RepositoryException {
        if (shipTypes.get(newShip.getLength()) == 0)
            throw new RepositoryException("No more ships avaliable of that size!");
        if (ships.contains(newShip)) {
            throw new RepositoryException("A ship occupies that area!");
        }

        ships.add(newShip);
        shipTypes.put(newShip.getLength(), shipTypes.get(newShip.getLength()) - 1);
    }

    /**
     * Remove a ship from the repository by it's head/start point on the grid, and increases the number of avaliable slots for
     * that ship type/length
     *
     * @param XCoord X Coordinate of the head of the ship
     * @param YCoord Y Coordinate of the head of the ship
     * @throws RepositoryException throws an exception if no ship is found on those coordinates
     */
    public void removeShipByCoord(int XCoord, int YCoord) throws RepositoryException {
        Ship shipToDelete = null;
        Point head = new Point(XCoord, YCoord);
        for (Ship sh : ships) {
            if (sh.getStart().equals(head))
                shipToDelete = sh;
        }
        if (shipToDelete == null)
            throw new RepositoryException("No ship head at those coordiantes!");

        if (!ships.removeIf(x -> x.generatePoints().contains(new Point(XCoord, YCoord))))
            throw new RepositoryException("No ship head at those coordiantes!");

        shipTypes.put(shipToDelete.getLength(), shipTypes.get(shipToDelete.getLength()) + 1);

    }

    /**
     * Removes all elements from the repository and set the number for the types to initial value
     */
    public void clear() {
        ships.clear();
        shipTypes.put(2, 2);
        shipTypes.put(3, 1);
        shipTypes.put(4, 1);

    }

    /**
     * Returns all the ships in an ArrayList
     *
     * @return ArrayList - all ships in the repository
     */
    public ArrayList<Ship> getAll() {
        return this.ships;
    }

    /**
     * Checks if all the possible number of ships that can be inserted into the grid are inserted
     *
     * @return true - if all ships are used up. false - otherwise
     */
    public boolean checkIfallUsed() {
        boolean allUsed = true;
        for (var o : shipTypes.keySet()) {
            if (shipTypes.get(o) != 0) {

                allUsed = false;
                break;
            }
        }
        return allUsed;
    }

    /**
     * Returns the ship by it's start/head point
     *
     * @param XCoord X Coordinate of the ship head to be found
     * @param YCoord Y Coordinate of the ship head to be found
     * @return Ship - if the ship is found, null if the ship is not found
     */
    public Ship getShipByHead(int XCoord, int YCoord) {
        for (Ship ship : this.ships) {
            if (ship.getStart().x == XCoord && ship.getStart().y == YCoord)
                return new Ship(ship);
        }
        return null;
    }

    /**
     * Checks if the given ship overlaps a another one in the repository
     *
     * @param ship Ship - the shi to be checked
     * @return boolean - true if there is an overlap, false if not
     */
    public boolean checkIfOverlap(Ship ship) {
        return ships.contains(ship);
    }

    /**
     * Checks if on the given coordinates there is a ship
     *
     * @param xCoord int - X Coordiante to be verified
     * @param yCoord int - Y Coordiante to be verified
     * @return boolean - true if there is a ship located on those coordinates, false - otherwise
     */
    public boolean checkIfHit(int xCoord, int yCoord) {
        for (Ship ship : ships) {
            for (Point point : ship.generatePoints()) {
                if (point.x == xCoord && point.y == yCoord)
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if on the given coordinates there is a ship which has 0 health
     *
     * @param xCoord int - X Coordiante to be verified
     * @param yCoord int - Y Coordiante to be verified
     * @return boolean - true if there is a ship located on those coordinates and it has 0 health, false - otherwise
     */
    public boolean checkIfSunked(int xCoord, int yCoord) {
        for (Ship ship : ships) {
            for (Point point : ship.generatePoints()) {
                if (point.x == xCoord && point.y == yCoord && ship.getHealth() == 0)
                    return true;
            }
        }
        return false;

    }

    /**
     * Checks if on the given coordinates there is a ship and decreases it's health
     *
     * @param xCoord int - X Coordiante to be verified
     * @param yCoord int - Y Coordiante to be verified
     * @throws ShipException       if there is a ship and the ship's health is already 0
     * @throws RepositoryException if there is no ship on those coordinates
     */
    public void decreaseShipHP(int xCoord, int yCoord) throws ShipException, RepositoryException {
        for (Ship ship : ships) {
            for (Point point : ship.generatePoints()) {
                if (point.x == xCoord && point.y == yCoord) {
                    ship.decreaseHealth();
                    return;
                }
            }
        }
        throw new RepositoryException("No ship on those coordiantes!");

    }

    /**
     * Checks if all ships are sunk
     *
     * @return true - if all of the ships healths are 0, false - otherwise
     */
    public boolean allShipsSunk() {

        for (Ship ship : ships) {
            if (ship.getHealth() != 0)
                return false;
        }

        return true;
    }

    /**
     * Checks if on the given coordinates there is a ship which has 0 health and destroyed is false
     *
     * @param XCoord int - X Coordiante to be verified
     * @param YCoord int - Y Coordiante to be verified
     * @return boolean - true if there is a ship located on those coordinates and it has 0 health and destroyed is false, false - otherwise
     */
    public boolean checkIfKill(int XCoord, int YCoord) {

        for (Ship ship : ships) {
            int AuxXCoord = ship.getStart().x;
            int AuxYCoord = ship.getStart().y;
            for (int i = 0; i < ship.getLength(); i++) {
                if (AuxXCoord == XCoord && AuxYCoord == YCoord && ship.getHealth() == 0 && ship.isDestoryed() == false)
                    return true;
                AuxXCoord += DirectionX[ship.getDirection()];
                AuxYCoord += DirectionY[ship.getDirection()];
            }
        }
        return false;

    }

    /**
     * Returns the ship which was destroyed the most recently, meaning it's destroyed value is false
     *
     * @return Ship - the destroyed ship, null if there is no destroyed and unverified ship
     */
    public Ship LastDestroyedShip() {
        for (Ship ship : ships) {
            if (ship.getHealth() == 0 && ship.isDestoryed() == false)
                return ship;
        }
        return null;
    }
}
