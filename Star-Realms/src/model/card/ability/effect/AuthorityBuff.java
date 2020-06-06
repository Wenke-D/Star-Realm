package model.card.ability.effect;

import model.comp.Target;

public class AuthorityBuff extends AbstractSimpleEffect {


	AuthorityBuff(String target, int value) {
		super(target, value);
	}

	@Override
	public void affect(Target target) {
		target.changeAuthority(getValue());
	}

}
