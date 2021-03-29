package com.game.battleship.MyExceptions;

/**
 * Exception for the Repository layer
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">Exception</a>
 */
public class RepositoryException extends Exception {
    public RepositoryException(String message)
    {
        super(message);
    }
}

