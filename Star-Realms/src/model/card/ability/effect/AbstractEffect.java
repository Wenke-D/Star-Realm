package model.card.ability.effect;

abstract class AbstractEffect implements Effect{
	
	private final Aim target;
	private final int value;
	private final String text;
	
	AbstractEffect(Aim target, int value, String text) {
		this.target = target;
		this.value = value;
		this.text = text;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return text + " "+value;
	}
	
}
