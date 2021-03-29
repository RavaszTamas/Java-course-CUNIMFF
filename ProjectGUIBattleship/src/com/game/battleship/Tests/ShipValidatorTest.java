package com.game.battleship.Tests;


import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.controller.Validators.ShipValidator;

public class ShipValidatorTest {

    public static  void validateShipTest() {
        ShipValidator validator = new ShipValidator(10,10);
        try {
            validator.validateShip(-1,-1,10,5);
            assert false : "Not valid ship!";
        } catch (ValidatorException e) {
            //System.out.println(e.getMessage());
            assert true : "OK!";
        }

        try {
            validator.validateShip(0,0,3,1);
            assert true : "Valid ship!";
        } catch (ValidatorException e) {
            //System.out.println(e.getMessage());
            assert false : "Not ok!";
        }


        try {
            validator.validateShip(0,9,10,1);
            assert false : "Invalid ship!";
        } catch (ValidatorException e) {
            //System.out.println(e.getMessage());
            assert true : "Ok!";
        }

    }
}