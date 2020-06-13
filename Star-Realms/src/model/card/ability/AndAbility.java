package model.card.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Store;
import model.card.ability.effect.Effect;
import model.comp.Target;
import model.player.Player;
import view.GraphicEffect;

/**
 * This kind of ability can execute all his effects directly without any other
 * condition.
 * 
 * @author Matth
 *
 */
public class AndAbility implements Ability {
	private final List<Effect> abis;

	public AndAbility(List<Effect> l) {
		Objects.requireNonNull(l);
		abis = new ArrayList<Effect>(l);
	}

	@Override
	public void affect(Target owner, Target opponent, Target store, List<String> extraInfos) {
		for (Effect a : abis) {
			a.affect(owner, opponent, store);
		}
	}

	@Override
	public List<String> needExtraInfo() {
		return null;
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
