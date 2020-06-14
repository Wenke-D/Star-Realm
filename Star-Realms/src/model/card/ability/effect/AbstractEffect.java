package model.card.ability.effect;

abstract class AbstractEffect implements Effect {

	protected final String target;
	protected final int value;



	AbstractEffect(String target, int value) {
		this.target = target;
		this.value = value;
	}

	@Override
	public String getTarget() {
		return target;
	}

	@Override
	public int getValue() {
		return value;
	}

}