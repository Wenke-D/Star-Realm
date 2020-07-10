package model.card.ability.effect;

import model.InvalideOperationException;
import model.comp.Target;
import view.GraphicEffect;

public interface Effect extends GraphicEffect {
	/**
	 * set the infomation needed while execute this effet.
	 * @param input
	 */
	public void setInput(String input);

	/**
	 * Set target of this execution.
	 * @param target
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



}
