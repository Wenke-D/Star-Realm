package model.comp.cardSquence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import model.card.Base;
import model.card.GameCard;
import model.card.Ship;
import model.comp.Graphic;
import model.comp.Target;

public class Field extends CardContainer implements Graphic {
	private final HashSet<GameCard> allyAbilityUsed;

	public Field() {
		allyAbilityUsed = new HashSet<GameCard>();
	}
	
	/**
	 * reset card ally ability state.
	 */
	public void resetState() {
		allyAbilityUsed.clear();
	}
	
	public List<GameCard> clearShips() {

		List<GameCard> ships = new ArrayList<GameCard>();

		for (int i = 0; i < size();) {
			if (get(i) instanceof Ship) {
				ships.add(remove(i));
			} else {
				i++;
			}
		}
		return ships;
	}

	public boolean hasOutpostBase() {

		for (int i = 0; i < size(); i++) {
			if (get(i).isOutpost())
				return true;
		}
		return false;
	}

	/**
	 * Test if card indicated can be destroyed under a damage.
	 * 
	 * @param index       card index.
	 * @param damagePoint damage number
	 * @return {@code true} if this damage is high enough, {@code false} if damage
	 *         is too low, or the target card is a ship.
	 */
	public boolean isDestoryed(int index, int damagePoint) {
		if (!get(index).isBase()) {
			return false;
		}

		Base base = (Base) get(index);

		return base.destroyed(damagePoint);
	}
	
	/**
	 * This function is used to active a specific ability of a card in the field.
	 * @param cardIndex
	 * @param type
	 * @param target
	 */
	public void active(int cardIndex, String type, Target target) {
		GameCard card = get(cardIndex);
		switch (type) {
		case "ally":
			if (hasAlly(card) & !allyAbilityUsed.contains(card)) {
				card.activeAbility(target, type);
				allyAbilityUsed.add(card);
			}
			return;
		case "scrap":
			card.activeAbility(target, type);
			remove(cardIndex);
			return;
		}
	}
	
	/**
	 * This function will active all the bases's basic ability who are lifted from last turn
	 */
	public void active(Target target) {
		for(int i = 0; i < size(); i ++) {
			get(i).activeAbility(target, "basic");
		}
	}

	private boolean hasAlly(GameCard card) {
		for (int i = 0; i < size(); i++) {
			if (get(i).isAlly(card))
				return true;
		}
		return false;
	}
	
	@Override
	public GameCard remove(int index) {
		allyAbilityUsed.remove(get(index));
		return super.remove(index);
	}

	@Override
	public void paint() {
		if (size() != 0) {
			System.out.println("Field:");
			super.paint();
		}

		else {
			System.out.println("Field: None");
		}

	}

}
