package model.card.ability.effect;

import java.util.Objects;

import model.comp.Target;

/**
 * SimpleEffect execute without player extra input.
 * @author Matth
 *
 */
public class SimpleEffect extends AbstractEffect  {
	public enum Type {
		Authority, Combat, Draw, Trade
	}
	
	private final Type effectType;

	SimpleEffect(String target, int value, String type) {
		super(target, value);
		effectType = Type.valueOf(type); 
	}

	@Override
	public String needExraInfo() {
		return null;
	}

	@Override
	public void affect(Target target) {
		switch (effectType) {
		case Authority: {
			target.changeAuthority(value);
			break;
		}
		case Combat: {
			target.changeCombat(value);
			break;

		}
		case Draw: {
			target.drawCard(value);
			break;

		}
		case Trade: {
			target.changeTrade(value);
			break;
		}
		}
	}

	@Override
	public void affect(Target target, String extraInfo) {
		throw new UnsupportedOperationException("SimpleEffect does not need extraInfo");

	}

	@Override
	public String getType() {
		return effectType.toString();
	}
}
