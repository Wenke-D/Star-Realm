package model.card.ability.effect;

abstract class AbstractSimpleEffect implements Effect{
	
	private final String target;
	private final int value;
	
	AbstractSimpleEffect(String target, int value) {
		this.target = target;
		this.value = value;
	}
	
	public String getTarget() {
		return target;
	}
	
	public int getValue() {
		return value;
	}
	
	
}
