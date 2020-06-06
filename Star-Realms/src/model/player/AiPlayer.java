package model.player;

import java.util.List;

import model.card.GameCard;

public class AiPlayer extends AbstractPlayer {

	public AiPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
	}


}
