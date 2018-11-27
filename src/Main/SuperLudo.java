package Main;

import Services.GameState;
import Services.ServiceFacade;
import View.ViewMaster;

public class SuperLudo {
	
	public static void main(String[] args) {
		ServiceFacade.initializeGame();
		ViewMaster.InitializeView();
		ServiceFacade.firstTurn();
	}

}
