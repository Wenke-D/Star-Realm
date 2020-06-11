package model.card.ability.effect;

import model.comp.Target;
import view.GraphicEffect;

public interface Effect extends GraphicEffect {
	public void affect(Target target);
	public void affect(Target self, Target opponent, Target store);
}
