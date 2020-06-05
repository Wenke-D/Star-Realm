package model.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.card.ability.Ability;
import model.card.ability.effect.Effect;
import model.comp.Target;

abstract class AbstractCard implements GameCard {
	private final String name;
	private final String faction;
	private final int cost;
	private final Ability basicAbis;
	private final Ability allyAbis;
	private final Ability scrapAbis;

	AbstractCard(String name, String faction, int cost, Ability basicAbis, Ability allyAbis,
			Ability scrapAbis) {
		this.name = name;
		this.faction = faction;
		this.cost = cost;
		this.basicAbis = basicAbis;
		this.allyAbis = allyAbis;
		this.scrapAbis = scrapAbis;
	}

	@Override
	public boolean isOutpost() {
		return false;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isBase() {
		return false;
	}

	@Override
	public boolean isAlly(GameCard other) {
		if(faction.equals(""))
			return false;
		if (other instanceof AbstractCard) {
			AbstractCard otherCard = (AbstractCard) other;
			return faction.equals(otherCard.faction);
		} else {
			return false;
		}

	}
	
	@Override
	public Ability getBasicAbility() {
		return basicAbis;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(
				String.format("|| %s | %s | %d |\n", name, faction == "" ? "None" : faction, cost));

		builder.append("   ||Basic::").append(basicAbis.toString()).append('\n');

		builder.append("   ||Ally::").append(allyAbis.toString()).append('\n');

		builder.append("   ||Scrap::").append(scrapAbis.toString());
		return builder.toString();
	}

}
