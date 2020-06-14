package model.player;

import java.util.List;

import model.card.Card;

/**
 * A AiPlayer play the game without receiving input.
 * @author Matth
 *
 */
public class AiPlayer extends AbstractPlayer {

	public AiPlayer(int tradePoint, int combatPoint, int authorityPoint, List<Card> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
	}

	@Override
	public List<String> needExtraInput(String place, int cardIndex, String abilityType) {
		throw new UnsupportedOperationException("Ai player shouldn't be test if need extra info");
	}

	@Override
	public String randomAction() {
		return "end";
	}








	



}
