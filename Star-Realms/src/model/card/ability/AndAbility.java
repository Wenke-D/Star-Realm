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
	private final List<Effect> effects;

	public AndAbility(List<Effect> l) {
		Objects.requireNonNull(l);
		effects = new ArrayList<Effect>(l);
	}

	@Override
	public void affect(Target owner, Target opponent, Target store, List<String> extraInfos) {
		for (Effect a : effects) {
			a.affect(owner, opponent, store);
		}
	}

	@Override
	public List<String> needExtraInfo() {
		ArrayList<String> infos = new ArrayList<String>();
		for (Effect e : effects) {
			String s = e.needExraInfo();
			if (s != null)
				infos.add(s);
		}

		return infos.size() == 0 ? null : infos;
	}

	@Override
	public List<GraphicEffect> getEffects() {
		List<GraphicEffect> list = new ArrayList<GraphicEffect>(effects);
		return list;
	}

	@Override
	public String getAbilityType() {
		return "And";
	}
}
