package model.card.ability.effect;

import org.dom4j.Element;

public class EffectFactory {
	public static Effect makeEffect(Element e) {
		int value = Integer.valueOf(e.attributeValue("value"));
		String type = e.attributeValue("type");

		String target = e.attributeValue("target");
		if (target == null)
			target = "self";

		switch (type) {
		case "A":
			return new AuthorityBuff(target, value);
		case "C":
			return new CombatBuff(target, value);

		case "D":
			return new DrawBuff(target, value);

		case "T":
			return new TradeBuff(target, value);

		default:
			throw new RuntimeException("Invalied scrouce file");
		}

	}
}
