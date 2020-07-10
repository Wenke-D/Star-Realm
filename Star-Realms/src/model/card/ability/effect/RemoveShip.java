package model.card.ability.effect;

import model.card.Card;
import model.comp.Target;

public class RemoveShip extends AbstractSimpleEffect {

	
	RemoveShip(String target, int value) {
		super(target, value);
	}

	@Override
	public void affect(Target target) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String needExraInfo() {
		return "number";
	}

	@Override
	public String getType() {
		return "RemoveShip";
	}

	@Override
	public Card returnAffect(Target target, String extraInfo) {
		int i = Integer.valueOf(extraInfo);
		return target.remove(i);		
	}

}
