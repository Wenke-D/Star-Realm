package model.card.ability;

import java.util.ArrayList;
import java.util.List;

import model.card.ability.effect.Effect;
import model.comp.Target;

public class Ability {
	private final List<Effect> abis;

	public Ability(List<Effect> l) {
		abis = new ArrayList<Effect>(l);
	}

	public void affect(Target target) {
		for (Effect a : abis) {
			a.affect(target);
		}
	}

	@Override
	public String toString() {
		if (abis.size() == 0) {
			return "|  None   |";
		}
		StringBuilder sb = new StringBuilder("|");
		if (abis.size() > 0) {
			sb.append(abis.get(0).toString());
			for (int i = 1; i < abis.size() - 1; i++) {
				sb.append(" & ").append(abis.get(i).toString());
			}
		}
		sb.append("|");
		return sb.toString();

	}
}
