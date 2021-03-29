package com.game.battleship.MyExceptions;

/**
 * Exception for the controller layer
 * @author Ravasz Tamás
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html">Exception</a>
 */
public class ControllerException extends Exception{
    public ControllerException(String message){super(message);}
}