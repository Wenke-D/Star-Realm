package model.player;

import model.Store;
import model.card.Card;
import model.comp.Target;
import view.GraphicPlayer;

/**
 * <p>
 * the player in this game, it support varies interaction between players and
 * store.
 * <p>
 * 
 * <p>
 * <b>Attention, the class who implements this interface should not close {@code System.in}</b>
 * </p>
 * 
 * @author Matth
 *
 */
public interface Player extends Target, GraphicPlayer {

	public boolean isDead();
	
	public void active(int cardIndex, String type, Player opponent, Store store);
	public void active(Card card, String type, Player opponent, Store store);
		
	public void attack(Player otherPlayer);
	
	public void endTurn();
	
	/**
	 * Active cards ability while his turn begins
	 */
	public void beginTurn();

	
	/**
	 * 
	 * @param index This index begin with 1.
	 * @return
	 */
	public Card put(int index);


	void get(Card c);


	boolean canAfford(int price);

	public void pay(int cost);
	
	/**
	 * Test if a card in the field can be destroyed with a certain damage
	 * @param cardIndex the index of the card
	 * @param damage the damage number
	 * @return true if it can be destroyed, false if not.
	 */
	public boolean baseIsDestory(int cardIndex, int damage);
	
	/**
	 * Move a card from field to discard pile. 
	 * @param cardIndex card's index
	 */
	public void destoryCard(int cardIndex);
	
	

}
