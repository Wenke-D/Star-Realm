package model.player;

import model.Log;
import model.Store;
import model.card.GameCard;
import model.comp.Graphic;
import model.comp.Target;

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
public interface Player extends Target {

	public boolean isDead();
	
	public int getAuthority();

	public int getTrade();

	public int getCombat();

	public void affected(int cardIndex, String type);
		
	public void attack(Target otherPlayer);
	
	public void endTurn();

	public void beginTurn();


	public GameCard put(int index);


	void get(GameCard c);


	boolean afford(int price);

}
