package model.card.ability.effect;

import model.comp.Target;

public class ComplexEffect extends AbstractSimpleEffect {

	private enum Type {
		Scrap;
	}

	private final Type effectType;

	private final String extraInfo;

	ComplexEffect(String target, int value, String type, String extraInfo) {

		super(target, value);
		effectType = Type.valueOf(type);
		this.extraInfo = extraInfo;
	}

	@Override
	public void affect(Target target) {
		throw new UnsupportedOperationException("ComplexEffect need extraInfo");
	}

	@Override
	public void affect(Target target, String extraInfo) {
		switch (effectType) {
		case Scrap: {
			target.remove(Integer.valueOf(extraInfo));
			break;
		}
		}

	}

	@Override
	public String needExraInfo() {
		return extraInfo;
	}

	@Override
	public String getType() {
		return effectType.toString();
	}

}
