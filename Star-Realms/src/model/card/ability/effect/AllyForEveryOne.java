package model.card.ability.effect;

import model.card.ability.effect.simpleEffect.AbstractSimpleEffect;
import model.comp.Target;

public class AllyForEveryOne extends AbstractSimpleEffect {

	AllyForEveryOne() {
		super("self", 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getType() {
		return "allyForEveryone";
	}

	@Override
	public void affect(Target target) {
		;
	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String needExraInfo() {
		return null;
	}

}
