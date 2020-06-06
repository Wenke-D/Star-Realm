package model.card.ability;

import java.util.ArrayList;
import java.util.List;

import model.Store;
import model.card.ability.effect.Effect;
import model.comp.Target;

public class Ability {
	private final List<Effect> abis;

	public Ability(List<Effect> l) {
		abis = new ArrayList<Effect>(l);
	}

	public void affect(Target self) {
		for (Effect a : abis) {
			a.affect(self);
		}
	}
	
	public void affect(Target owner, Target opponent, Store store) {
		affect(owner);
	}

}
