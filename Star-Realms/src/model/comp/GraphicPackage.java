package model.comp;

import java.util.ArrayList;

import model.Store;
import model.player.Player;
import view.GraphicPlayer;
import view.GraphicStore;

/**
 * The package between view and data, where view get info to print.
 * 
 * @author Matth
 *
 */
public class GraphicPackage {
	private int roundNumber;
	private String winner;
	private Player curPlayer;
	private Player opponent;
	private Store store;
	private ArrayList<String> messages;

	public GraphicPackage(Player curPlayer, Player opponent, Store store, String winner, int roundNumber) {
		this.roundNumber = roundNumber;
		this.winner = winner;
		this.curPlayer = curPlayer;
		this.opponent = opponent;
		this.store = store;
		messages = new ArrayList<String>();
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public String getWiner() {
		return winner;
	}

	public GraphicPlayer getCurPlayer() {
		return curPlayer;
	}

	public GraphicPlayer getOpponent() {
		return opponent;
	}

	public GraphicStore getStore() {
		return store;
	}

	/**
	 * Messages from Data
	 * 
	 * @return
	 */
	public String getMessage() {
		return messages.remove(messages.size() - 1);
	}
}
