package com.game.battleship.MyExceptions;

/**
 * Exception for the validator
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">Exception</a>
 */
public class ValidatorException extends Exception {
    public ValidatorException(String message)
    {
        super(message);
    }
}
