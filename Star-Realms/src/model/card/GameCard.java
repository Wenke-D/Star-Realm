package model.card;

import model.card.ability.Ability;
import model.comp.Target;

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

	public Ability getBasicAbility();
}
