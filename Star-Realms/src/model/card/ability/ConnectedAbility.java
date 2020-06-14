package model.card.ability;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Receiver;

import model.card.Card;
import model.card.ability.effect.Effect;
import model.comp.Target;
import view.GraphicEffect;

public class ConnectedAbility implements Ability {
	private final Effect giver;
	private final Effect receiver;

	public ConnectedAbility(List<Effect> list) {
		this.giver = list.get(0);
		this.receiver = list.get(1);
	}

	@Override
	public List<GraphicEffect> getEffects() {
		ArrayList<GraphicEffect> effects = new ArrayList<GraphicEffect>();
		effects.add(giver);
		effects.add(receiver);
		return effects;
	}

	@Override
	public String getAbilityType() {
		return "connected";
	}

	@Override
	public void affect(Target owner, Target opponent, Target store, List<String> extraInfos) {
		Target realTarget1 = null;
		switch (giver.getTarget()) {
		case "self":
			realTarget1 = owner;

			break;
		case "opponent":
			realTarget1 = opponent;
			break;

		case "store":
			realTarget1 = store;
			break;
		default:
			throw new RuntimeException("Unknown target! :" + giver.getTarget());
		}
		Target realTarget2 = null;
		switch (receiver.getTarget()) {
		case "self":
			realTarget2 = owner;

			break;
		case "opponent":
			realTarget2 = opponent;
			break;

		case "store":
			realTarget2 = store;
			break;
		default:
			throw new RuntimeException("Unknown target!");
		}
		Object obj = giver.returnAffect(realTarget1, extraInfos.get(0));
		receiver.receiveAffect(realTarget2, obj);
	}

	@Override
	public List<String> needExtraInfo() {
		ArrayList<String> infos = new ArrayList<String>();
		String s = giver.needExraInfo();
		if (s != null)
			infos.add(s);

		s = receiver.needExraInfo();
		if (s != null)
			infos.add(s);

		return infos.size() == 0 ? null : infos;
	}

}
