package com.game.battleship.Tests;

import com.game.battleship.domain.Models.Ship;

import java.awt.*;

public class ShipTest {

    public static void testEquals()
    {
        Ship shipOne = new Ship(new Point(1,2),3,0);
        Ship shipTwo = new Ship(new Point(1,2),3,0);
        Ship shipThree = new Ship(new Point(0,2),3,1);
        assert shipOne.equals(shipTwo) : "not equal";
        assert shipOne.equals(shipTwo) : "equal";
        assert shipOne.equals(shipThree) : "equal";
    }

}