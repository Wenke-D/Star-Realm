package model.card.ability;

import model.comp.Target;
import view.GraphicAbility;

public interface Ability extends GraphicAbility  {
	public void affect(Target owner, Target opponent, Target store);
}
