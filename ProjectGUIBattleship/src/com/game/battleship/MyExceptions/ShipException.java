package com.game.battleship.MyExceptions;

/**
 * Exception for the ship object
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">Exception</a>
 */
public class ShipException extends Exception{
    public ShipException(String message){super(message);}
}