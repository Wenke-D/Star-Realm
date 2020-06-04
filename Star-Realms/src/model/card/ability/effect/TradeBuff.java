package model.card.ability.effect;

import model.comp.Target;

public class TradeBuff extends AbstractEffect {



	TradeBuff(Aim target, int value, String text) {
		super(target, value, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affect(Target target) {
		target.changeTrade(getValue());

	}

}
