package model.card.ability.effect;

import model.card.Card;
import model.comp.Target;

public class DrawNbBlob implements Effect {
	

	@Override
	public String getTarget() {
		return "self";
	}

	@Override
	public String getType() {
		return "DrawNb";
	}

	@Override
	public int getValue() {
		return 0;
	}

	@Override
	public void affect(Target target) {
		int nb = target.nbBlob();
		target.drawCard(nb);

	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Card returnAffect(Target target, String extraInfo) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String needExraInfo() {
		throw new UnsupportedOperationException();
	}


	@Override
	public void receiveAffect(Target realTarget, Object info) {
		// TODO Auto-generated method stub
		
	}

}
