package model.card;

import model.Store;
import model.card.ability.Ability;
import model.player.Player;

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
	public void affect(Player owner) {
		basicAbis.affect(owner);
	}
	
	@Override
	public void affect(String type, Player owner, Player opponent, Store store) {
		switch(type) {
		case "basic":{
			basicAbis.affect(owner, opponent, store);
			break;
		}
		case "ally":{
			allyAbis.affect(owner, opponent, store);
			break;
		}
		case "scrap":{
			scrapAbis.affect(owner, opponent, store);
			break;
		}
		}
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
