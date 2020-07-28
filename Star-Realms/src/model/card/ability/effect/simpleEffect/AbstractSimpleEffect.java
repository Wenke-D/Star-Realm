package model.card.ability.effect.simpleEffect;

import model.card.ability.effect.Effect;
import model.comp.Target;

import java.util.Map;

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
	public Map<String, String> inputHint() {
		throw new UnsupportedOperationException("This kind of effect doesn't have hint");
	}

	@Override
	public void setInput(Object input) {
		throw new UnsupportedOperationException("Can not set input in this kind of effect");
	}

	@Override
	public Object getResult() {
		throw new UnsupportedOperationException("This effect does not have result");
	}

	@Override
	public String getInputType() {
		throw new UnsupportedOperationException("This effect does not need input");
	}
}