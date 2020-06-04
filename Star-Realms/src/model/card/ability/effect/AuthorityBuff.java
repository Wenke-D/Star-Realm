package model.card.ability.effect;

import model.comp.Target;

public class AuthorityBuff extends AbstractEffect {


	AuthorityBuff(Aim target, int value, String text) {
		super(target, value, text);
	}

	@Override
	public void affect(Target target) {
		target.changeAuthority(getValue());
	}

}
