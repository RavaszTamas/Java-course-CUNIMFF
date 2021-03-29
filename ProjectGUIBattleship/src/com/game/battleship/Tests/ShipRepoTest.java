package com.game.battleship.Tests;


import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.domain.Models.Ship;
import com.game.battleship.domain.ShipRepository.ShipRepository;

import java.awt.*;

public class ShipRepoTest {

    public static void testAddShip() {
        ShipRepository repo = new ShipRepository();
        try {
            repo.addShip(new Ship(new Point(1, 2), 2, 0));
            repo.addShip(new Ship(new Point(2, 2), 2, 0));
            repo.addShip(new Ship(new Point(3, 2), 3, 0));
            assert true:"ok";
        }
        catch (RepositoryException e)
        {
            assert false : "Ship doesn't exist!";
        }

        try {
            repo.addShip(new Ship(new Point(1, 2), 3, 0));
            assert false:"ship exists";
        }
        catch (RepositoryException e)
        {
            assert true : "ok";
        }

        try {
            repo.removeShipByCoord(1,2);
            assert true:"ok";
        } catch (RepositoryException e) {
            assert false:"Ship exists!";
        }
        try {
            repo.removeShipByCoord(1,2);
            assert false:"Ship doesn't exist!";
        } catch (RepositoryException e) {
            assert true:"ok";
        }
        try {
            repo.addShip(new Ship(new Point(1, 2), 2, 0));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        //System.out.println(repo.getNextSize());


    }
}