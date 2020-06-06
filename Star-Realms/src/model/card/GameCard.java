package model.card;

import model.Store;
import model.player.Player;

/**
 * This interface represent the cards exist in the game.
 * @author Matth
 *
 */
public interface GameCard {
	
	/**
	 * Get the price of the card
	 * @return A integer which represent the price
	 */
	public int getCost();
	public boolean isOutpost();
	public String getName();
	public boolean isBase();
	public boolean isAlly(GameCard other);
	
	/**
	 * Card active his ability
	 * @param type type of ability
	 * @param owner card's owner
	 * @param opponent owner's opponent
	 * @param store store in the game
	 */
	public void affect(String type, Player owner, Player opponent, Store store);
	
	/**
	 * active basic ability to affect owner
	 * @param self
	 */
	public void affect(Player self);
}
