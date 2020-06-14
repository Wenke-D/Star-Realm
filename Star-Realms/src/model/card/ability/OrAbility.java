package model.card.ability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.card.ability.effect.Effect;
import model.comp.Target;
import view.GraphicEffect;

public class OrAbility implements Ability {
	private final List<Effect> effects;

	public OrAbility(List<Effect> l) {
		Objects.requireNonNull(l);
		effects = new ArrayList<Effect>(l);
	}

	@Override
	public List<GraphicEffect> getEffects() {
		return Collections.unmodifiableList(effects);
	}

	@Override
	public String getAbilityType() {
		return "or";
	}

	@Override
	public void affect(Target owner, Target opponent, Target store, List<String> extraInfos) {
		int choice = Integer.valueOf(extraInfos.get(0));
		Effect e = effects.get(choice);
		Target realTarget = realTarget(owner, opponent, store, e);
		e.affect(realTarget);		
	}

	@Override
	public List<String> needExtraInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Target realTarget(Target owner, Target opponent, Target store, Effect e) {
		switch (e.getTarget()) {
		case "self":
			return owner;
		case "opponent":
			return opponent;
		case "store":
			return store;
		default:
			throw new RuntimeException("Unknown target!");
		}
	}

}
