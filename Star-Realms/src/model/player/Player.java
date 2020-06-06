package model.player;

import model.Store;
import model.card.GameCard;
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

	public void active(int cardIndex, String type, Player opponent, Store store);
	public void active(GameCard card, String type, Player opponent, Store store);
		
	public void attack(Player otherPlayer);
	
	public void endTurn();

	public void beginTurn();


	public GameCard put(int index);


	void get(GameCard c);


	boolean afford(int price);
	
	public void changeAuthority(int number);
	public void changeCombat(int number);
	public void changeTrade(int number);
	public void drawCard(int number);

}
