package Main;

import Controler.Controler;
import Services.GameState;
import View.CentralPanel;
import View.PrimFrame;
import View.SideMenu;
import View.ViewMaster;

public class SuperLudo {
	
	public static void main(String[] args) {
		GameState.initializeGame();
		ViewMaster.InitializeView();
	}

}
