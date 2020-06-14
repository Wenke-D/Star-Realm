package model.card.ability.effect;

import model.card.Card;
import model.comp.Target;

abstract class AbstractEffect implements Effect {

	protected final String target;
	protected final int value;



	AbstractEffect(String target, int value) {
		this.target = target;
		this.value = value;
	}

	@Override
	public String getTarget() {
		return target;
	}

	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public Card returnAffect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void receiveAffect(Target realTarget2, Object card) {
		throw new UnsupportedOperationException();
	}

}