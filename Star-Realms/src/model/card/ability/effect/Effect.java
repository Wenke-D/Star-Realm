package model.card.ability.effect;

import model.InvalideOperationException;
import model.comp.Target;

import java.util.Map;

public interface Effect {

	/**
	 * Set target of this execution.
	 * @param target effect's target.
	 */
	public void setTarget(Target target);

	/**
	 * Execute this effect
	 */
	public void execute();

	/**
	 * Weather this effect need player's input before running.
	 * @return true if need, false if not.
	 */
	public boolean needInput();

	/**
	 * Hint of input needed.
	 *
	 * The first entry is description witch the key name is description.
	 * The second is type of input wanted witch the key name is valueType.
	 *
	 * This function should not be called while the effect it belongs to does not need input
	 *
	 * @return Map<String, String>.
	 */
	public Map<String, String> inputHint() ;

	/**
	 * set the information needed while execute this effet.
	 * @param input player's input.
	 */
	public void setInput(Object input);

	/**
	 * Return the result of execution.
	 * @return Object
	 */
	public Object getResult();


	/**
	 * The input source, from user or last effect.
	 * @return String user or effect
	 */
	public String getInputType();


}
