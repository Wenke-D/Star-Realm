package model.card.ability.effect;

import model.comp.Target;

public class DrawBuff extends AbstractSimpleEffect {



	public DrawBuff(String target, int value) {
		super(target, value);
	}

	@Override
	public void affect(Target target) {
		target.drawCard(getValue());
	}

}
