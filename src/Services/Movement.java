package Services;

import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class Movement {
	
	private static Pin selected = null;
	
	public static void select(int posX, int posY) {
		int dice = GameState.getDice();
		if(dice==0) {
			System.out.println("Jogue o Dado");
			return;
		}
		
		if(selected != null) {
			move(posX,posY);
			GameState.nextTurn();
		}
		else {
			selected = GameState.getTurnPlayer().checkForPin(posX, posY);
		}
		System.out.println(selected);
	}
	
	private static void move (int posX, int posY) {
		selected.setPosition(posX, posY);
		selected = null;
		//System.out.println(selected.getPosX());
		//System.out.println(selected.getPosY());
		ViewMaster.refreshBoard();
	}
	
	static void FirstMove (Player player) {
		selected = player.getPins()[0];
		int number = player.getNumber();
		if(number ==1) 
			move(1,6);
		else if(number ==2)
			move(8,1);
		else if(number ==3) 
			move(13,8);
		else
			move(6,13);
	}
}
