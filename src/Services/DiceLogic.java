package Services;

public abstract class DiceLogic {
	
	static boolean runDice() {
		if(GameState.getDice() == 0) {
			GameState.setDice((int)(Math.random() * 6 + 1));
			return true;
		}
		return false;
	}
	
	static void gameDice(int num) {
		GameState.setDice(num);	
	}
}
