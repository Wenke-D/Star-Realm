package model.card.ability.effect;

public class EffectFactory {
	public static Effect makeEffect(int value, String type, String target) {
				
		if(target == null)
			target = "self";
		Aim aim = Aim.valueOf(target);
		switch (type) {
		case "A":
			return new AuthorityBuff(aim, value, "Healty");
		case "T":
			return new TradeBuff(aim, value, "Trade");
		case "C":
			return new CombatBuff(aim, value, "Combat");
		case "D":
			return new DrawBuff(aim, value, "Draw");
		default:
			return null;
		}
	}
	
	public static Effect makeEffect(String value, String type, String target) {
		return makeEffect(Integer.valueOf(value), type, target);
	}
	
}
