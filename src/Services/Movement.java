package Services;

import Models.Path;
import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class Movement {
	
	private static Pin lastSelected = null;
	
	public static void select(Pin pin) {
		int dice =GameState.getDice();
		if(dice==5) {
			if(moveFive())
				return;
		}
		if(pin.isBeginZone() || pin.isEndZone())
			return;
		if(dice==0) {
			System.out.println("Jogue o Dado");
			return;
		}
		if(!GameState.getTurnPlayer().isPinOwner(pin)) {
			return;
		}
		move(pin);
		if(dice== 6) {
			GameState.addCountSix();
			if(GameState.getCountSix()<3) {
				lastSelected = pin;
				GameState.setDice(0);
				return;
			}
			pin.sendHome();
		}
		GameState.nextTurn();
		
		/*System.out.println(selected);
		int dice = GameState.getDice();
		if(dice==0) {
			System.out.println("Jogue o Dado");
			return;
		}
		if(dice==5) {
			moveFive();
		}
		
		if(selected != null) {
			if(move(posX,posY));{
				if(dice == 6) {
					GameState.addCountSix();
					if (GameState.getCountSix()<3) {
						lastSelected = selected;
						GameState.setDice(0);
					}
					else {
						moveBegin();
						GameState.nextTurn();
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
		System.out.println(selected);*/
	}
	
	private static void move (Pin p) {
		int destiny = p.getPathNum() + GameState.getDice();
		if(destiny>51)
			destiny= destiny -51;
		p.setPathNum(destiny);
		ViewMaster.refreshBoard();
		/*if(checkForMove(posX, posY)) {
			selected.setPosition(posX, posY);
			ViewMaster.refreshBoard();
			return true;
		}
		return false;*/
	}
	
	static void firstMove (Player player) {
		outOfBegin (player, player.getBeginPin());
	}
	
	static boolean moveFive() {
		Pin p = GameState.getTurnPlayer().getBeginPin();
		if (p==null)
			return false;
		outOfBegin (GameState.getTurnPlayer(), p);
		GameState.nextTurn();
		return true;		
	}
	
	private static void outOfBegin (Player player, Pin selected) {
		int number = player.getNumber();
		if(number ==1) {
			selected.setPath(Path.Common);
			selected.setPathNum(0);
		}
		else if(number ==2) {
			selected.setPath(Path.Common);
			selected.setPathNum(13);
		}
		else if(number ==3) {
			selected.setPath(Path.Common);
			selected.setPathNum(26);
		}
		else {
			selected.setPath(Path.Common);
			selected.setPathNum(39);
		}
		ViewMaster.refreshBoard();
	}

	
	private static boolean checkForMove(int posX, int posY) {
		return true;
	}
}
