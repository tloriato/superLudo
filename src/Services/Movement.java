package Services;

import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class Movement {
	
	private static Pin selected = null;
	private static Pin lastSelected = null;
	
	public static void select(int posX, int posY) {
		int dice = GameState.getDice();
		if(dice==0) {
			System.out.println("Jogue o Dado");
			return;
		}
		
		if(selected != null) {
			if(move(posX,posY));{
				if(dice == 6) {
					GameState.addCountSix();
					if (GameState.getCountSix()<3) {
						lastSelected = selected;
					}
					else {
						moveBegin();
						GameState.nextTurn();
						selected=null;
					}
				}
				else{
					GameState.nextTurn();
				}
			}
			selected = null;
		}
		
		else {
			selected = GameState.getTurnPlayer().checkForPin(posX, posY);
			if(selected != null) {
				if(selected.isBeginZone() || selected.isEndZone()) {
					selected = null;
					System.out.println("Nao Pode Movimentar esse pino");
				}
			}
		}
		System.out.println(selected);
	}
	
	private static boolean move (int posX, int posY) {
		if(checkForMove(posX, posY)) {
			selected.setPosition(posX, posY);
			ViewMaster.refreshBoard();
			return true;
		}
		return false;
	}
	
	static void firstMove (Player player) {
		selected = player.getBeginPin();
		outOfBegin (player);
	}
	
	static void moveFive() {
		selected = GameState.getTurnPlayer().getBeginPin();
		if (selected==null)
			return;
		if(outOfBegin (GameState.getTurnPlayer()))
			GameState.nextTurn();
	}
	
	private static boolean outOfBegin (Player player) {
		int number = player.getNumber();
		if(number ==1) 
			return move(1,6);
		else if(number ==2)
			return move(8,1);
		else if(number ==3) 
			return move(13,8);
		else
			return move(6,13);
	}
	
	private static void moveBegin() {
	}
	
	private static boolean checkForMove(int posX, int posY) {
		return true;
	}
}
