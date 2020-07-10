package model.card.ability;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.card.ability.effect.Effect;
import model.comp.Target;
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
		Target realTarget = null;
		for (Effect effect : effects) {
			switch (effect.getTarget()) {
			case "self":
				realTarget = owner;

				break;
			case "opponent":
				realTarget = opponent;
				break;

			case "store":
				realTarget = store;
				break;
			default:
				throw new RuntimeException("Unknown target!");

			}
			if (effect instanceof SimpleEffect)
				effect.affect(realTarget);
			else {
				effect.affect(realTarget, extraInfos.get(0));
			}

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
