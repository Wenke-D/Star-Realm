package model.card.ability.effect.simpleEffect;

import model.card.ability.effect.Effect;
import model.comp.Target;

abstract class AbstractSimpleEffect implements Effect {

	protected Target target;
	protected final int value;



	AbstractSimpleEffect(int value) {
		this.value = value;
	}

	@Override
	public void setTarget(Target target) {
		this.target = target;
	}

	@Override
	public boolean needInput() {
		return false;
	}

	@Override
	public String inputHint() {
		throw new UnsupportedOperationException("This kind of effect doesn't have hint");
	}

	@Override
	public void setInput(String input) {
		throw new UnsupportedOperationException("Can not set input in this kind of effect");
	}
}