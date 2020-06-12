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
	
	

}
