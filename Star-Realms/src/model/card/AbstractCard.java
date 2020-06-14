package model.card;

import java.util.List;

import model.Store;
import model.card.ability.Ability;
import model.comp.Target;
import model.player.Player;
import view.GraphicAbility;

abstract class AbstractCard implements Card {
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
		throw new UnsupportedOperationException("This is not a Base");
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
	public boolean isAlly(Card other) {
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
	public void affect(String type, Target owner, Target opponent, Target store, List<String> extraInfos) {
		switch(type) {
		case "basic":{
			basicAbis.affect(owner, opponent, store, extraInfos);
			break;
		}
		case "ally":{
			allyAbis.affect(owner, opponent, store, extraInfos);
			break;
		}
		case "scrap":{
			scrapAbis.affect(owner, opponent, store, extraInfos);
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
	
	@Override
	public String getFaction() {
		
		return faction;
	}

	@Override
	public GraphicAbility getBasicAbility() {
		return basicAbis;
	}

	@Override
	public GraphicAbility getAllyAbility() {
		return allyAbis;
	}

	@Override
	public GraphicAbility getScrapAbility() {
		return scrapAbis;
	}
	
	@Override
	public boolean isBase() {
		return false;
	}

	@Override
	public int getDefense() {
		throw new UnsupportedOperationException("This is not a base");
	}
	
	@Override
	public List<String> needExraInfos(String type) {
		switch(type) {
		case "basic":{
			return basicAbis.needExtraInfo();
		}
		case "ally":{
			return allyAbis.needExtraInfo();
		}
		case "scrap":{
			return scrapAbis.needExtraInfo();
		}
		default:
			throw new RuntimeException("Invalid Type: "+type);
		}
	}


}
