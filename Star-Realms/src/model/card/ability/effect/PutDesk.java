package model.card.ability.effect;

import model.card.Card;
import model.comp.Target;

public class PutDesk implements Effect {

	@Override
	public String getTarget() {
		return "self";
	}

	@Override
	public String getType() {
		return "Putdesk";
	}

	@Override
	public int getValue() {
		return 0;
	}

	@Override
	public void affect(Target target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Card returnAffect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String needExraInfo() {
		return null;
	}

	@Override
	public void receiveAffect(Target target, Object obj) {
		Card card = (Card) obj;
		target.addToDeck(card);
	}


}
