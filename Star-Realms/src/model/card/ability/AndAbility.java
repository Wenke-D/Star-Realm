package model.card.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Store;
import model.card.ability.effect.Effect;
import model.comp.Target;
import model.player.Player;
import view.GraphicEffect;

public class AndAbility implements Ability {
	private final List<Effect> abis;

	public AndAbility(List<Effect> l) {
		Objects.requireNonNull(l);
		abis = new ArrayList<Effect>(l);
	}
	
	@Override
	public void affect(Target owner, Target opponent, Target store) {
		for (Effect a : abis) {
			a.affect(owner, opponent, store);
		}
	}

	@Override
	public List<GraphicEffect> getEffects() {
		List<GraphicEffect> list = new ArrayList<GraphicEffect>(abis);
		return list;
	}

	@Override
	public String getAbilityType() {
		return "And";
	}
}
