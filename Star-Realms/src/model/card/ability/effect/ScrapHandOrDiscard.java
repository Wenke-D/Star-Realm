package model.card.ability.effect;

import jdk.jshell.spi.ExecutionControl.RunException;
import model.card.Card;
import model.comp.Target;

public class ScrapHandOrDiscard implements Effect {

	private final String target;
	private final int value;

	ScrapHandOrDiscard(String target, int value) {
		this.target = target;
		this.value = value;
	}

	@Override
	public String getTarget() {
		return target;
	}

	@Override
	public String getType() {
		return "ScrapHandOrDiscard";
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void affect(Target target) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();

	}
	
	/**
	 * extraInfo should like n,n
	 */
	@Override
	public Integer returnAffect(Target target, String extraInfo) {
		String[] subStrs = extraInfo.split(",");
		int n = Integer.valueOf(subStrs[0])+Integer.valueOf(subStrs[1]);
		if(n > value)
			throw new RuntimeException("Too many card");
		return n;
	}

	@Override
	public String needExraInfo() {
		return "ScrapHandOrDiscard";
	}

	@Override
	public void receiveAffect(Target realTarget2, Object obj) {
		throw new UnsupportedOperationException();
	}

}
