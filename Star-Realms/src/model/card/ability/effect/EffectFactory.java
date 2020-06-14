package model.card.ability.effect;

import org.dom4j.Element;

public class EffectFactory {

	public static Effect makeEffect(Element e) {
		int value = Integer.valueOf(e.attributeValue("value"));
		String effectType = e.attributeValue("type");
		String target = e.attributeValue("target");
		String extraInfo = e.attributeValue("extraInfo");

		if (target == null)
			target = "self";

		if (extraInfo == null)
			return new SimpleEffect(target, value, effectType);
		else
			return new ComplexEffect(target, value, effectType, extraInfo);
	}

}
