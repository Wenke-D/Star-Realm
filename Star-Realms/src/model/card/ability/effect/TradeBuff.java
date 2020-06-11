package model.card.ability.effect;

import model.comp.Target;

public class TradeBuff extends AbstractSimpleEffect {

	TradeBuff(String target, int value) {
		super(target, value);
	}

	@Override
	public void affect(Target target) {
		target.changeTrade(getValue());

	}

	@Override
	public String getType() {
		return "Trade";
	}

}
