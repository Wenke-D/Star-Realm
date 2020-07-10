package model;


/**
 * This Exception represent the actions that violate game rules,
 * for exemple, this exception should be thrown while a player try to attack twice
 * in his turn.
 * Witch means this kind of exception can be recovered, so it inherit Exception.
 */
public class InvalideOperationException extends Exception {
}
