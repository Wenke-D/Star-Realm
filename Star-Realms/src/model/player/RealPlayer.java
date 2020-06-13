package model.player;

import java.util.List;
import model.card.Card;

public class RealPlayer extends AbstractPlayer {

	public RealPlayer(int tradePoint, int combatPoint, int authorityPoint, List<Card> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
	}

	@Override
	public List<String> needExtraInput(String place, int cardIndex, String abilityType) {
		Card card;
		switch (place) {
		case "field": {
			card = getCardFromField(cardIndex);
			break;
		}
		case "hand": {
			card = getCardFromHand(cardIndex);
			break;

		}
		default:
			throw new RuntimeException("Invalide Place");
		}
		return card.needExraInfos(abilityType);

	}

	@Override
	public String randomAction() {
		throw new UnsupportedOperationException("RealPlater's command comes from input.");
	}
	

}
