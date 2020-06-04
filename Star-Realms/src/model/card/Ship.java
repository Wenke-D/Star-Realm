package model.card;

import model.card.ability.Ability;

public class Ship extends AbstractCard {

	public Ship(String name, String faction, int cost, Ability basicAbis, Ability allyAbis, Ability scrapAbis) {
		super(name, faction, cost, basicAbis, allyAbis, scrapAbis);
	}
}
