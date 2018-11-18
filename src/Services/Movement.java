package Services;

import Models.Path;
import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class Movement {
	
	private static Pin lastSelected = null;
	
	public static void select(Pin pin) {
		int dice =GameState.getDice();
		if(forcedMove())
			return;
		if(dice==0) {
			System.out.println("Jogue o Dado");
			return;
		}
		if(!checkForMove(pin)) {
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
	}
	
	public static boolean forcedMove() {
		int dice =GameState.getDice();
		if(dice==5) {
			return moveFive();
		}
		return false;
	}
	
	private static void move (Pin p) {
		int destiny = p.getPathNum() + GameState.getDice();
		int playerNum = GameState.getTurnPlayer().getNumber();
		Barrier.leaveBarrier(p);
		
		if(p.getPathType() == Path.Common) {
			if(enterLastRoad( playerNum,destiny, p.getPathNum(), p))
				return;
			if(destiny>51)
				destiny= destiny -51;
			p.setPathNum(destiny);
		}
		
		if(p.getPathType() == Path.LastRoad) {
			//if()
		}
		
		endMove(p);
		/*if(checkForMove(posX, posY)) {
			selected.setPosition(posX, posY);
			ViewMaster.refreshBoard();
			return true;
		}
		return false;*/
	}
	
	private static boolean enterLastRoad(int playerNum, int destiny, int originalPos, Pin p) {
		int enterHouse = lastRoadEntrace(playerNum);
		if(destiny > enterHouse && originalPos < enterHouse ){
			int diff = destiny - enterHouse;
			if(diff==6)
				p.setPath(Path.End);
			else {
				p.setPath(Path.LastRoad);
				p.setPathNum(destiny - enterHouse);
			}
			endMove(p);
			return true;
		}
		return false;
	}
	
	public static int lastRoadEntrace(int playerNum) {
		if(playerNum == 1) 
			return 50;
		if(playerNum == 2) 
			return 11;
		if(playerNum == 3) 
			return 24;
		else
			return 37;
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

	
	private static boolean checkForMove(Pin p) {
		int dice = GameState.getDice();
		int initial = p.getPathNum();
		if(p.isBeginZone() || p.isEndZone())
			return false;
		if(p.getPathType() == Path.LastRoad) {
			if(initial + dice >5)
				return false;
		}
		return true;
	}
	
	private static void endMove(Pin p) {
		Barrier.checkForNewBarrier(p);
		if(p.getPathType() == Path.Common) {
			if(!isShelter(p.getPathNum()) && !isExit(p.getPathNum())) {
				for (Player pl :  GameState.getPlayers()) {
					for(Pin pin: pl.getPins()) {
						if(pl != GameState.getTurnPlayer()) {
							if(p.getPathNum()==pin.getPathNum() && p.getPathType() == pin.getPathType()) {
								pin.sendHome();
							}
						}
					}
				}
			}
		}
		ViewMaster.refreshBoard();
	}
	
	static boolean isShelter(int n) {
		return (n==9 || n== 22 || n==35 || n==48);
	}
	
	static boolean isExit(int n) {
		return (n==13 || n== 26 || n==39 || n==0);
	}
}
