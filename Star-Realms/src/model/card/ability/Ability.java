package model.card.ability;

import java.util.List;

import model.comp.Target;
import view.GraphicAbility;

public interface Ability extends GraphicAbility  {
	public void affect(Target owner, Target opponent, Target store, List<String> extraInfos);
	
	/**
	 * Return if this ability need other input to perform his ability
	 * @return a list of info type, null if this ability need nothing
	 */
	public List<String> needExtraInfo();
}
