package model.card.ability.effect;

import model.InvalideOperationException;
import model.comp.Target;

public class DrawNb implements Effect {


	@Override
	public void setTarget(Target target) {

	}

	@Override
	public String execute() throws InvalideOperationException {
		return null;
	}

	@Override
	public boolean needInput() {
		return false;
	}

	@Override
	public String inputHint() {
		return null;
	}

	@Override
	public void setInput(Object input) {

	}
}
