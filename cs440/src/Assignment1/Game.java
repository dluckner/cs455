package Assignment1;

import java.util.ArrayList;

public class Game {
	private boolean db = true;
	private int numPlayers;
	public Game(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	public void run() {
		LayerTree gameMap = new LayerTree(this.numPlayers);
//		gameMap.existanceOfWinner();
		gameMap.test3();
	}
}
