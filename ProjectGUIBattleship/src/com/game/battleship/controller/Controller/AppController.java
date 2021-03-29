package com.game.battleship.controller.Controller;

import com.game.battleship.MyExceptions.ControllerException;
import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.MyExceptions.ShipException;
import com.game.battleship.MyExceptions.ValidatorException;
import com.game.battleship.domain.Models.Hit;
import com.game.battleship.domain.HitRepository.HitRepository;
import com.game.battleship.controller.Validators.HitValidator;
import com.game.battleship.domain.Models.Ship;
import com.game.battleship.domain.ShipRepository.ShipRepository;
import com.game.battleship.controller.Validators.ShipValidator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Performs most of the task on the business layer of the program, it servers as the business logic level of the program
 * it processes the input and responds accordingly
 *
 * @author Ravasz Tamás
 */
public class AppController {

    private HitRepository playerHitRepository;
    private HitRepository computerHitRepository;
    private ShipRepository playerShipRepository;
    private ShipRepository computerShipRepository;
    private ShipValidator shipValidator;
    private HitValidator hitValidator;

    /**
     * The controller layer for the player
     *
     * @param playerHitRepository    The repository of hits for the player
     * @param computerHitRepository  The repository of hits of the computer for the player
     * @param playerShipRepository   The repository of ships for the player
     * @param computerShipRepository The repository of ships of the computer for the player
     * @param shipValidator          The validator for the inserted ship
     * @param hitvalidator           The validator for a hit
     */
    public AppController(HitRepository playerHitRepository, HitRepository computerHitRepository, ShipRepository playerShipRepository, ShipRepository computerShipRepository, ShipValidator shipValidator, HitValidator hitvalidator) {
        this.playerHitRepository = playerHitRepository;
        this.computerHitRepository = computerHitRepository;
        this.playerShipRepository = playerShipRepository;
        this.computerShipRepository = computerShipRepository;
        this.shipValidator = shipValidator;
        this.hitValidator = hitvalidator;
    }

    /**
     * Adds a ship to the grid
     *
     * @param XCoord    X coordinate of the ship
     * @param YCoord    Y coordinate of the ship
     * @param length    length of the ship
     * @param direction direction of the ship, can be 0 or 1
     * @throws ValidatorException  if the validator evaluates that the new ship is invalid
     * @throws RepositoryException if during the insert the same ship is added or the ship overlaps a another one
     */
    public void addShip(int XCoord, int YCoord, int length, int direction) throws ValidatorException, RepositoryException {
        shipValidator.validateShip(XCoord, YCoord, length, direction);
        try {
            playerShipRepository.addShip(new Ship(new Point(XCoord, YCoord), length, direction));
        } catch (RepositoryException ex) {
            direction = (direction + 1) % 2;
            shipValidator.validateShip(XCoord, YCoord, length, direction);
            playerShipRepository.addShip(new Ship(new Point(XCoord, YCoord), length, direction));

        }
    }

    /**
     * Removes a ship from the grid
     *
     * @param XCoord X coordinate of one of the tiles which contain a ship
     * @param YCoord Y coordinate of one of the tiles which contain a ship
     * @throws RepositoryException if no ship found on those coordinates
     */
    public void removeShip(int XCoord, int YCoord) throws RepositoryException {
        playerShipRepository.removeShipByCoord(XCoord, YCoord);
    }

    /**
     * Returns all the computer's hits
     *
     * @return ArrayList
     */
    public ArrayList<Hit> getAllComputerHits() {
        return computerHitRepository.getAllHits();
    }

    /**
     * Returns all the player's hits
     *
     * @return ArrayList
     */

    public ArrayList<Hit> getAllPlayerHits() {

        return playerHitRepository.getAllHits();
    }

    /**
     * Returns all the player's ships
     *
     * @return ArrayList
     */
    public ArrayList<Ship> getAllPlayerShips() {
        return playerShipRepository.getAll();
    }

    /**
     * Performs the attack on the grid for the player
     *
     * @param XCoord X Coordinate where the player attacks
     * @param YCoord Y Coordinate where the player attacks
     * @return int - exit code, 0 no ship hit, 1 ship hit but not sunk, 2 ship hit and sunk
     * @throws ValidatorException  If the player tries to attack on an invalid coordinate
     * @throws RepositoryException If the player tries to shoot on the same position, which has already been used
     * @throws ControllerException In case of internal failure, AI tries to shoot to the same spot.
     */
    public int playerAttack(int XCoord, int YCoord) throws ValidatorException, RepositoryException, ControllerException {
        hitValidator.validate(XCoord, YCoord);
        boolean ifHit = computerShipRepository.checkIfHit(XCoord, YCoord);
        playerHitRepository.add(new Hit(XCoord, YCoord, ifHit));

        if (ifHit) {
            try {
                computerShipRepository.decreaseShipHP(XCoord, YCoord);
            } catch (ShipException e) {
                throw new ControllerException(e.getMessage() + "This should never happen btw...");
            }
        }
        boolean ifSunk = computerShipRepository.checkIfSunked(XCoord, YCoord);
        if (ifSunk) {
            Ship lastDestroyedShip = computerShipRepository.LastDestroyedShip();
            lastDestroyedShip.setDestroyed(true);
        }
        if (!ifHit)
            return 0;
        if (ifHit && !ifSunk)
            return 1;
        return 2;
    }

    /**
     * Checks if the player is able to start the game
     *
     * @return returns if the player can start the game
     */
    public boolean canStart() {
        return playerShipRepository.checkIfallUsed();
    }

    /**
     * Returns the next available ship to be placed on the grid, it is always the smallest length ship
     *
     * @return int - the size of the next ship
     */
    public int getNextSize() {
        return playerShipRepository.getNextSize();
    }

    /**
     * It rotates a ship if possible on a given coordinate
     *
     * @param XCoord int - X coordinate of the ship's head
     * @param YCoord int - Y coordinate of the ship's head
     * @throws ValidatorException  If the player tries to attack an invalid position
     * @throws RepositoryException If the player tries to attack a position which was already attacked
     * @throws ControllerException If the player can't rotate the ship
     */
    public void rotateShip(int XCoord, int YCoord) throws ValidatorException, RepositoryException, ControllerException {
        Ship shipToRotate = playerShipRepository.getShipByHead(XCoord, YCoord);
        if (shipToRotate == null)
            throw new ValidatorException("No ship head there!");
        shipToRotate.setDirection((shipToRotate.getDirection() + 1) % 2);
        shipValidator.validateShip(shipToRotate);
        playerShipRepository.removeShipByCoord(XCoord, YCoord);
        if (!playerShipRepository.checkIfOverlap(shipToRotate)) {
            playerShipRepository.addShip(shipToRotate);
        } else {
            shipToRotate.setDirection((shipToRotate.getDirection() + 1) % 2);
            playerShipRepository.addShip(shipToRotate);
            throw new ControllerException("Can't rotate");
        }
    }

    /**
     * Checks if the player has won the game
     *
     * @return boolean true - if the player has won, false otherwise
     */
    public boolean checkIfPlayerWin() {
        return computerShipRepository.allShipsSunk();
    }

    /**
     * Clears all the directories, repositories of data
     */
    public void clearAll() {
        computerShipRepository.clear();
        computerHitRepository.clear();
        playerShipRepository.clear();
        playerHitRepository.clear();
    }

    /**
     * Checks if the player has won the game
     *
     * @return boolean true - if the player has won, false otherwise
     */

    public boolean checkIfAIWin() {
        return playerShipRepository.allShipsSunk();
    }

    /**
     * Returns all the computer's ships
     *
     * @return ArrayList
     */
    public ArrayList<Ship> getAllComputerShips() {
        return computerShipRepository.getAll();
    }
}
