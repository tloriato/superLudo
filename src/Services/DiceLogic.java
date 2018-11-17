package Services;

public class DiceLogic {

	private static int value;
	
	
	public static boolean runDice() {
		if(GameState.getDice() ==0) {
			GameState.setDice((int)(Math.random() * 6 + 1));
			return true;
		}
		return false;
	}
	
}
