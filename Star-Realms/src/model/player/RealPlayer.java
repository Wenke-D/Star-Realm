package model.player;

import java.util.List;
import java.util.Scanner;

import model.Log;
import model.Store;
import model.card.GameCard;

public class RealPlayer extends AbstractPlayer {
	

	public RealPlayer(int tradePoint, int combatPoint, int authorityPoint, List<GameCard> list) {
		super(tradePoint, combatPoint, authorityPoint, list);
	}
	

}
