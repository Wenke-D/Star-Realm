package model.card.ability.effect;

import model.comp.Target;
import view.GraphicEffect;

public interface Effect extends GraphicEffect {
	public void affect(Target target);
	
	public void affect(Target target, String extraInfo);
	
	/**
	 * Weather his effect need other info to perform his ability
	 * @return null if not, info type if need.
	 */
	public String needExraInfo();
}
