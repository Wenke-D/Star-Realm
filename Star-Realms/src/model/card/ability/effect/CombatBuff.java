package model.card.ability.effect;

import model.comp.Target;

public class CombatBuff extends AbstractSimpleEffect {

	CombatBuff(String target, int value) {
		super(target, value);
	}

	@Override
	public void affect(Target target) {
		target.changeCombat(getValue());
	}

}
