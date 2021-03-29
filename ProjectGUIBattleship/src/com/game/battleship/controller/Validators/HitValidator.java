package com.game.battleship.controller.Validators;

import com.game.battleship.MyExceptions.ValidatorException;

import java.awt.*;
import java.util.ArrayList;

/**
 * Validator for a new hit on the grid
 *
 * @author Ravasz Tamás
 */
public class HitValidator {
    private int XMax, YMax;
    private ArrayList<Point> possibleLocations = new ArrayList<>();

    /**
     * Constructor for the validator
     *
     * @param paramW the number of rows in the grid
     * @param paramH the number of columns in the grid
     */
    public HitValidator(int paramW, int paramH) {
        XMax = paramW;
        YMax = paramH;
        for (int i = 0; i < XMax; i++) {
            for (int j = 0; j < YMax; j++) {
                possibleLocations.add(new Point(i, j));
            }
        }
    }

    /**
     * Checks if the hit is a valid coordinate
     *
     * @param XCoord X coordinate of the hit
     * @param YCoord Y coordinate of the hit
     * @throws ValidatorException if XCoord or YCoord are negative or bigger then the bounds of the grid
     */
    public void validate(int XCoord, int YCoord) throws ValidatorException {
        String _errors = "";
        if (!possibleLocations.contains(new Point(XCoord, YCoord)))
            _errors += "Invalid X or Y Coordiante! ";
        if (!_errors.equals(""))
            throw new ValidatorException(_errors);
    }

}
