package Main;

import Services.GameState;
import View.ViewMaster;

public class SuperLudo {
	
	public static void main(String[] args) {
		GameState.initializeGame();
		ViewMaster.InitializeView();
		GameState.firstTurn();
	}

}
