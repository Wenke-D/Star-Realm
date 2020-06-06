package model.card.ability;

import java.util.ArrayList;
import java.util.List;

import model.Store;
import model.card.ability.effect.Effect;
import model.player.Player;

public class Ability {
	private final List<Effect> abis;

	public Ability(List<Effect> l) {
		abis = new ArrayList<Effect>(l);
	}
	
	public void affect(Player self) {
		for (Effect a : abis) {
			a.affect(self);
		}
	}
	
	public void affect(Player owner, Player opponent, Store store) {
		
	}

}
