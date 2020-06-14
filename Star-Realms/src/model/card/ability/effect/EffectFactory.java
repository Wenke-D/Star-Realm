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

		if (effectType.equals("RemoveShip"))
			return new RemoveShip(target, value);

		if (effectType.equals("PutDesk"))
			return new PutDesk();
		
		if (effectType.equals("DrawNbBlob"))
			return new DrawNbBlob();
		
		if(effectType.equals("ScrapHandOrDiscard"))
			return new ScrapHandOrDiscard(target, value);
		
		if (effectType.equals("DrawNb"))
			return new DrawNb(extraInfo);
		
		if (effectType.equals("AllyForEveryOne"))
			return new AllyForEveryOne();

		if (extraInfo == null)
			return new SimpleEffect(target, value, effectType);
		else
			return new ComplexEffect(target, value, effectType, extraInfo);
	}

}
