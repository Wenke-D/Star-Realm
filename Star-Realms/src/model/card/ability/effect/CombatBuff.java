package model.card.ability.effect;

import model.comp.Target;

public class CombatBuff extends AbstractEffect {



	CombatBuff(Aim target, int value, String text) {
		super(target, value, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affect(Target target) {
		target.changeCombat(getValue());
	}

}
