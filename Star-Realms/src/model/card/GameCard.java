package model.card;

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
	
	/**
	 * <p>
	 * Active a ability to affect a {@code Target}.
	 * </p>
	 * @param target the {@codeTarget} affect to.
	 * @param type which ability to active, basic, ally or scrap.
	 */
	public void activeAbility(Target target, String type);
}
