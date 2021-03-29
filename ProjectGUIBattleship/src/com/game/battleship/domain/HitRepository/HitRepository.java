package com.game.battleship.domain.HitRepository;

import com.game.battleship.MyExceptions.RepositoryException;
import com.game.battleship.domain.Models.Hit;

import java.util.ArrayList;

/**
 * Repository for storing the hits on the grid
 *
 * @author Ravasz Tamás
 */
public class HitRepository {

    private ArrayList<Hit> hits;

    /**
     * Constructor for the repository
     */
    public HitRepository() {
        hits = new ArrayList<>();
    }

    /**
     * Adds a new Hit into the repository, it throws an exception if the hit is already in the repository
     *
     * @param hit Hit - hit to be added to the repository
     * @throws RepositoryException if the hit is already in the repository
     */
    public void add(Hit hit) throws RepositoryException {
        if (hits.contains(hit))
            throw new RepositoryException("Already attacked that target!");
        hits.add(hit);
    }

    /**
     * Returns all the hits
     *
     * @return ArrayList - the hits of the repo in an ArrayList
     */
    public ArrayList<Hit> getAllHits() {
        return this.hits;
    }

    /**
     * Clears the repository from all the hits
     */
    public void clear() {
        hits.clear();
    }
}
