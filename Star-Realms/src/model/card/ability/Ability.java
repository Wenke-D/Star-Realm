package model.card.ability;

import model.Store;
import model.player.Player;
import view.GraphicAbility;

public interface Ability extends GraphicAbility  {
	public void affect(Player self);
	public void affect(Player owner, Player opponent, Store store);
}
