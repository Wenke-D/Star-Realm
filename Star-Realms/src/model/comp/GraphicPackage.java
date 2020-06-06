package model.comp;

import view.GraphicPlayer;
import view.GraphicStore;

/**
 * The package between view and data, where view get info to print.
 * @author Matth
 *
 */
public class GraphicPackage {
	private final int roundNumber;
	private final String winner;
	private final Player curPlayer;
	private final Player opponent;
	private final Store store;
		
	public int getRoundNumber() {
		return roundNumber;
	}
	
	public String getWiner(){
		return winner;
	}

	public GraphicPlayer getCurPlayer();

	public GraphicPlayer getOpponent();

	public GraphicStore getStore();
	
	/**
	 * Get messages from Data
	 * @return
	 */
	public String getMessage();
}
