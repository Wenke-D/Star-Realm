package model.card.ability.effect;

import com.alibaba.fastjson.JSONObject;
import model.card.ability.effect.simpleEffect.Authority;
import model.card.ability.effect.simpleEffect.Combat;
import model.card.ability.effect.simpleEffect.Draw;
import model.card.ability.effect.simpleEffect.Trade;
import model.comp.Target;


public class EffectFactory {
    private final Target self;
    private final Target store;
    private final Target opponent;

    public EffectFactory(Target self, Target store, Target opponent) {
        this.self = self;
        this.store = store;
        this.opponent = opponent;
    }

    public Effect makeEffect(JSONObject effectJSON) {
        String effectName = effectJSON.getString("name");
        Effect effect;

        switch (effectName) {
            case "authority" -> {
                int value = effectJSON.getIntValue("value");
                effect = new Authority(value);
            }
            case "combat" -> {
                int value = effectJSON.getIntValue("value");
                effect = new Combat(value);
            }
            case "draw" -> {
                int value = effectJSON.getIntValue("value");
                effect = new Draw(value);
            }
            case "trade" -> {
                int value = effectJSON.getIntValue("value");
                effect = new Trade(value);
            }
            case "destroyBase" -> effect = new DestroyBase();

            case "scrapCardsInTradeRow" -> effect = new ScrapCardsInTradeRow();

            default -> throw new IllegalStateException("Unexpected value: " + effectName);
        }

        setTarget(effect, effectJSON);
        return effect;

    }

    private void setTarget(Effect effect, JSONObject effectJSON) {
        String target = effectJSON.getString("target");
        switch (target) {
            case "self" -> {
                effect.setTarget(self);
            }
            case "store" -> {
                effect.setTarget(store);
            }
            case "opponent" -> {
                effect.setTarget(opponent);
            }
        }
    }

}
