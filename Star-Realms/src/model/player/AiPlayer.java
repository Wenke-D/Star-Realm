package model.player;

import java.util.List;

import model.Log;
import model.Store;
import model.card.GameCard;

public class AiPlayer extends AbstractPlayer {

	public AiPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
	}

	@Override
	public int playGame(Store store, Player opponent, Log log) {
		System.out.println("Hello");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		endTurn();
		return 1;
	}

}
