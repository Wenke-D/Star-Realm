package model.player;

import model.Log;
import model.Store;
import model.card.GameCard;
import model.comp.Graphic;

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
public interface Player {
	/**
	 * React to a attack
	 * 
	 * @param point
	 */
	public void sufferDamage(int point);

	/**
	 * React while a base is attacked
	 * 
	 * @param point
	 */
	public void baseAttacked(int baseIndex, int damagePoint);

	/**
	 * <p>
	 * player's act one time during his round. By default each action will update
	 * the view, player can change it by changing return.
	 * </p>
	 * 
	 * @param store    a place where can buy card
	 * @param opponent his adversary
	 * @return 1 means player's round finish. -1 means this action no need to update
	 *         whole view, just print log.
	 */
	public int playGame(Store store, Player opponent, Log log);

	public boolean isDead();
	
	/**
	 * This function is been called while the first player begins his first turn.
	 */
	public void draw3();

	public int getAuthority();

	public int getTrade();

	public int getCombat();

	public Graphic getHands();

	public Graphic getField();

	public Graphic getDeck();

	public Graphic getDisCardPile();

	public void affected(int cardIndex, String type);
	
	/**
	 * What a player do before his turn begins.
	 */
	public void prepare();

}
