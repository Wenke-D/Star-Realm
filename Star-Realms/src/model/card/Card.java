package model.card;

import java.util.List;

import model.Store;
import model.comp.Target;
import model.player.Player;
import view.GraphicCard;

/**
 * This interface represent the cards exist in the game.
 * @author Matth
 *
 */
public interface Card extends GraphicCard {
	
	/**
	 * Get the price of the card
	 * @return A integer which represent the price
	 */
	public int getCost();
	public boolean isOutpost();
	public String getName();
	public boolean isAlly(Card other);
	
	/**
	 * Card active his ability
	 * @param type type of ability
	 * @param owner card's owner
	 * @param opponent owner's opponent
	 * @param store store in the game
	 */
	public void affect(String type, Target owner, Target opponent, Target store, List<String> extraInfos);
	
	public List<String> needExraInfos(String type);
	
}
