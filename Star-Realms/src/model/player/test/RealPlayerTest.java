package model.player.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.card.GameCard;
import model.player.RealPlayer;

class RealPlayerTest {

	@Test
	void testExecute() {
		RealPlayer p = new RealPlayer(50, 50, 50, new ArrayList<GameCard>());
		p.playGame(null, null, null);
	}

}
