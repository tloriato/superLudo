package Services;

import Models.Pin;
import View.ViewMaster;

public class Movement {
	
	private static Pin selected = null;
	
	public static void select(int posX, int posY) {
		System.out.println(selected);
		if(selected != null) {
			move(posX,posY);
			selected = null;
			GameState.nextTurn();
		}
		else {
			selected = GameState.getTurnPlayer().checkForPin(posX, posY);
		}
		System.out.println(selected);
	}
	
	private static void move (int posX, int posY) {
		selected.setPosition(posX, posY);
		//System.out.println(selected.getPosX());
		//System.out.println(selected.getPosY());
		ViewMaster.refreshBoard();
	}
}
