package model.card.ability.effect;

import model.InvalideOperationException;
import model.comp.Target;
import view.GraphicEffect;

public interface Effect {

	/**
	 * Set target of this execution.
	 * @param target effect's target.
	 */
	public void setTarget(Target target);

	/**
	 * Active this effect
	 * @return the resulat of this execution while this effect has connect with other effect.
	 * @throws InvalideOperationException effect does not have input or the input is wrong.
	 */
	public String execute() throws InvalideOperationException;

	/**
	 * Weather this effect need player's input before running.
	 * @return true if need, false if not.
	 */
	public boolean needInput();

	/**
	 * Description of input needed.
	 * @return String description.
	 */
	public String inputHint();

	/**
	 * set the information needed while execute this effet.
	 * @param input player's input.
	 */
	public void setInput(String input);



}
