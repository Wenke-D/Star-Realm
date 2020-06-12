package model.card.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Store;
import model.card.ability.effect.Effect;
import model.player.Player;
import view.GraphicEffect;

public class AndAbility implements Ability {
	private final List<Effect> abis;

	public AndAbility(List<Effect> l) {
		Objects.requireNonNull(l);
		abis = new ArrayList<Effect>(l);
	}
	
	public void affect(Player self) {
		for (Effect a : abis) {
			a.affect(self);
		}
	}
	
	public void affect(Player owner, Player opponent, Store store) {
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
