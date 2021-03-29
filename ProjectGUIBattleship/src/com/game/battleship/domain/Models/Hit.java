package com.game.battleship.domain.Models;

import java.util.Objects;

/**
 * Object which represents a hit on the game grid
 *
 * @author Ravasz Tamás
 */
public class Hit {
    public int x;
    public int y;
    public boolean shipWasHit;

    /**
     * Constructor for the hit
     *
     * @param paramX     int - X coordinate of the hit
     * @param paramY     int - Y coordinate of the hit
     * @param paramIsHit boolean - true if the hit is hit on a ship false otherwise
     */
    public Hit(int paramX, int paramY, boolean paramIsHit) {
        x = paramX;
        y = paramY;
        shipWasHit = paramIsHit;
    }

    /**
     * Checks if the object is equal to the current one
     *
     * @param o the object to compare to
     * @return true if the same or they X, Y, and hit values are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return x == hit.x &&
                y == hit.y &&
                shipWasHit == hit.shipWasHit;
    }

    /**
     * Hashcode of the instance of object Hit
     *
     * @return int - the hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, shipWasHit);
    }
}
