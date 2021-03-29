package com.game.battleship.controller.Validators;

import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.domain.Models.Ship;

import java.util.ArrayList;

/**
 * Validtor for a new ship, checks if the specific input is acceptable
 * as a correct ship.
 *
 * @author Ravasz Tamás
 */
public class ShipValidator {

    private int XMax, YMax;
    private final ArrayList<Integer> listOfLengths = new ArrayList<>() {{
        add(2);
        add(3);
        add(4);
    }};

    /**
     * Constructor for the ship validator
     *
     * @param paramX The number of rows of the game grid
     * @param paramY The number of columns of the game grid
     */
    public ShipValidator(int paramX, int paramY) {
        XMax = paramX;
        YMax = paramY;
    }

    /**
     * Validates a ship and throws an exception if the ship is not a correct ship.
     *
     * @param ship The ship that is to be validated.
     * @throws ValidatorException Throws the exception in case the ship is not valid.
     */
    public void validateShip(Ship ship) throws ValidatorException {
        this.validateShip(ship.getStart().x, ship.getStart().y, ship.getLength(), ship.getDirection());
    }

    /**
     * Validates a ship by it's parameters and throws an exception if the ship is not a correct ship.
     *
     * @param XCoord    X Coordinate of the head of the ship.
     * @param YCoord    Y Coordinate of the head of the ship.
     * @param length    Length of the ship.
     * @param direction The direction of the ship, can be 0 horizontal, 1 vertical
     * @throws ValidatorException Throws an exception if the ship is not a valid ship
     */
    public void validateShip(int XCoord, int YCoord, int length, int direction) throws ValidatorException {
        StringBuilder errors = new StringBuilder();
        if (XCoord < 0 || XCoord > XMax)
            errors.append("Invalid X Coordinate!");
        if (YCoord < 0 || YCoord > YMax)
            errors.append("Invalid Y Coordinate!");
        if (!(direction == 1 || direction == 0))
            errors.append("Invalid direction!");
        if (!listOfLengths.contains(length))
            errors.append("Invalid length!");

        final int[] DirectionX = {0, 1};
        final int[] DirectionY = {1, 0};
        if (direction == 1 || direction == 0)
            if (XCoord + DirectionX[direction] * length > XMax || YCoord + DirectionY[direction] * length > YMax)
                errors.append("Ship out of bounds!");
        if (errors.length() != 0)
            throw new ValidatorException(errors.toString());
    }

}
