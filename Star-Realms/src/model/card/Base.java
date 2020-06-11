package model.card;


import model.card.ability.Ability;
import model.card.ability.effect.Effect;
import view.GraphicAbility;

public class Base extends AbstractCard{
	private final int defense;
	private final boolean outPost;

	public Base(String name, String faction, int cost, Ability basicAbis, Ability allyAbis, Ability scrapAbis,
			int defense, boolean outPost) {
		super(name, faction, cost, basicAbis, allyAbis, scrapAbis);
		this.defense = defense;
		this.outPost = outPost;
	}

	@Override
	public boolean isOutpost() {
		return outPost;
	}
	
	public boolean destroyed(int damagePoint) {
		if(damagePoint >= defense) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isBase() {
		return true;
	}
	








	
	
}
