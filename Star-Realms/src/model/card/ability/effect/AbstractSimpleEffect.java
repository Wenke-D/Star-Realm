package model.card.ability.effect;

import java.util.Objects;

import model.comp.Target;

abstract class AbstractSimpleEffect implements Effect {

	private final String target;
	private final int value;

	AbstractSimpleEffect(String target, int value) {
		this.target = Objects.requireNonNull(target);
		this.value = value;
	}

	@Override
	public String getTarget() {
		return target;
	}

	@Override
	public void affect(Target self, Target opponent, Target store) {
		switch (target) {
		case "self":
			affect(self);
			break;
		case "rival":
			affect(opponent);
			break;
		default:
			affect(store);
		}

	}

	@Override
	public int getValue() {
		return value;
	}

}
