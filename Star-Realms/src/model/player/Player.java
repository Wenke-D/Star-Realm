package model.player;

import model.Store;
import model.card.GameCard;
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
	public void active(GameCard card, String type, Player opponent, Store store);
		
	public void attack(Player otherPlayer);
	
	public void endTurn();

	public void beginTurn();


	public GameCard put(int index);


	void get(GameCard c);


	boolean canAfford(int price);
	
	

}
