package model.card.ability.effect;

import model.card.Card;
import model.comp.Target;

public class DrawNb extends AbstractEffect {

	
	DrawNb(String target) {
		super(target, -1);
	}


	@Override
	public String getType() {
		return "DrawNb";
	}

	@Override
	public int getValue() {
		return -1;
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
		return null;
	}


	@Override
	public void receiveAffect(Target realTarget, Object info) {
		int n = Integer.valueOf((String) info);
		realTarget.drawCard(n);
	}



}
