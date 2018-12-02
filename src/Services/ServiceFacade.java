package Services;

import Models.Pin;
import Models.Player;

public abstract class ServiceFacade {
	public static boolean runDice() {
		return DiceLogic.runDice();
	}
	
	public static void initializeGame() {
		GameState.initializeGame();
	}
	
	public static void resetGame() {
		GameState.resetGame();
	}
	
	public static Player getTurnPlayer() {
		return GameState.getTurnPlayer();
	}
	
	public static void firstTurn() {
		GameState.firstTurn();
	}
	
	public static Player[] getPlayers() {
		return GameState.getPlayers();
	}
	
	public static int getDice() {
		return GameState.getDice();
	}
	
	public static void loadState(String fullLoadedState) {
		GameState.loadState(fullLoadedState);
	}
	
	public static String getFullState() {
		return GameState.getFullState();
	}
	
	public static void select(Pin pin) {
		Movement.select(pin);
	}
	
	public static void forcedMove() {
		Movement.forcedMove();
	}
	
}
