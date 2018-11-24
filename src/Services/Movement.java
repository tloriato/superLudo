package Services;

import java.util.ArrayList;

import Models.Path;
import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class Movement { 
	
	private static Pin lastSelected = null;
	
	public static void select(Pin pin) {
		int dice =GameState.getDice();
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
		if(move(pin)) {
			GameState.setDice(0);
			if(dice== 6) 
				lastSelected = pin;
			else 
				GameState.zeroCountSix();
			return;
				
		}
		if(dice== 6) {
			GameState.setDice(0);
			lastSelected = pin;
			return;
		}
		GameState.nextTurn();
	}
	
	public static void forcedMove() {
		int dice =GameState.getDice();
		if(dice==5) {
			if(moveFive())
				return;
		}
		if(dice== 6) {
			GameState.addCountSix();
			if(GameState.getCountSix()>=3) {
				lastSelected.sendHome();
				ViewMaster.refreshBoard();
				GameState.nextTurn();
				return;
			}
			ArrayList<Pin> pins = Barrier.firstBarrier();
			if(pins.size()==1) {
				if(checkForMove(pins.get(0))) {
					move(pins.get(0));
					GameState.setDice(0);
					lastSelected = pins.get(0);
					return;
				}
			}
			if(pins.size()==2) {
				int i = pinAhead(pins.get(0),pins.get(1));
				if(checkForMove(pins.get(i))) {
					move(pins.get(i));
					GameState.setDice(0);
					lastSelected = pins.get(i);
					return;
				}
				if(i==0)
					i=1;
				else
					i=0;
				if(checkForMove(pins.get(i))) {
					move(pins.get(i));
					GameState.setDice(0);
					lastSelected = pins.get(i);
					return;
				}
			}
		}
		
		Pin pin=null;
		for(Pin p : GameState.getTurnPlayer().getPins()) {
			if(checkForMove(p)) {
				if(pin==null)
					pin=p;
				else
					return;
			}
				
		}
		
		if(pin==null) {
			GameState.nextTurn();
		}
		
		if(move(pin)) {
			GameState.setDice(0);
			if(dice== 6) 
				lastSelected = pin;
			else 
				GameState.zeroCountSix();
			return;
			
		}
		if(dice== 6) {
			GameState.setDice(0);
			lastSelected = pin;
			return;
		}
		GameState.nextTurn();
		
	}
	private static int pinAhead(Pin p1, Pin p2) {
		if(p1.getPathType() == Path.LastRoad && p2.getPathType() == Path.Common)
			return 0;
		if(p2.getPathType() == Path.LastRoad && p1.getPathType() == Path.Common)
			return 1;
		if(p1.getPathType() == Path.LastRoad) {
			if(p1.getPathNum()>p1.getPathNum()) {
				return 0;
			}
			return 1;
		}
		int ent = lastRoadEntrace(GameState.getTurnPlayer().getNumber());
		
		if(p1.getPathNum()< ent && p2.getPathNum()>ent)
			return 0;
		if(p2.getPathNum()< ent && p1.getPathNum()>ent)
			return 1;
		if(p1.getPathNum()>p2.getPathNum())
			return 0;
		return 1;
		
		
	}
	
	private static boolean move (Pin p) {
		int destiny = p.getPathNum() + GameState.getDice();
		int playerNum = GameState.getTurnPlayer().getNumber();
		Barrier.leaveBarrier(p);
		
		if(p.getPathType() == Path.Common) {
			if(enterLastRoad( playerNum,destiny, p.getPathNum(), p))
				return false;
			if(destiny>51)
				destiny= destiny -52;
			p.setPathNum(destiny);
		}
		
		if(p.getPathType() == Path.LastRoad) {
			if(destiny ==5) {
				p.setPath(Path.End);
				return true;
			}
			else
				p.setPathNum(destiny);
		}
		
		return endMove(p);
		/*if(checkForMove(posX, posY)) {
			selected.setPosition(posX, posY);
			ViewMaster.refreshBoard();
			return true;
		}
		return false;*/
	}
	
	private static boolean enterLastRoad(int playerNum, int destiny, int originalPos, Pin p) {
		int enterHouse = lastRoadEntrace(playerNum);
		if(destiny > enterHouse && originalPos <= enterHouse ){
			int diff = destiny - enterHouse;
			if(diff==6)
				p.setPath(Path.End);
			else {
				p.setPath(Path.LastRoad);
				p.setPathNum(destiny - enterHouse-1);
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
		Pin p = player.getBeginPin();
		int exit = outOfBegin (player);
		p.setPath(Path.Common);
		p.setPathNum(exit);
		ViewMaster.refreshBoard();
	}
	
	static boolean moveFive() {
		Pin p = GameState.getTurnPlayer().getBeginPin();
		int exit = outOfBegin (GameState.getTurnPlayer());
		if (p==null)
			return false;
		Pin pinInExit =null;
		for (Player pl :  GameState.getPlayers()) {
			for(Pin pin: pl.getPins()) {
				if(exit==pin.getPathNum() && Path.Common == pin.getPathType() ) {
					if(pl == GameState.getTurnPlayer()) 
						return false;
					pinInExit = pin;
					break;
				}
			}
		}
		if(pinInExit != null) {			
			pinInExit.sendHome();
			p.setPath(Path.Common);
			p.setPathNum(exit);
			ViewMaster.refreshBoard();
			GameState.setDice(0);
			forcedMove();
			return true;	
		}

		p.setPath(Path.Common);
		p.setPathNum(exit);
		ViewMaster.refreshBoard();
		GameState.nextTurn();
		return true;		
	}
	
	private static int outOfBegin (Player player) {
		int number = player.getNumber();
		if(number ==1) 
			return 0;
		else if(number ==2) 
			return 13;
		else if(number ==3) 
			return 26;
		else 
			return 39;
	}

	
	private static boolean checkForMove(Pin p) {
		int dice = GameState.getDice();
		int initial = p.getPathNum();
		int destiny = initial+dice;
		if(p.isBeginZone() || p.isEndZone())
			return false;
		if(p.getPathType() == Path.LastRoad) {
			if(destiny >5)
				return false;
		}
		if(Barrier.barrierOnTheWay(p.getPathNum(), dice, GameState.getTurnPlayer(), p.getPathType()))
			return false;
		int count=0;
		if(isExit(destiny) && p.getPathType() == Path.Common) {
			for (Player pl :  GameState.getPlayers()) {
				for(Pin pin: pl.getPins()) {
					if(destiny==pin.getPathNum() && Path.Common == pin.getPathType()) {
						if(outOfBegin(pl)!=destiny)
							return false;
						count++;
					}
				}
			}
		}
		if(count==2)
			return false;
		count=0;
		if(isShelter(destiny) && p.getPathType() == Path.Common) {
			for (Player pl :  GameState.getPlayers()) {
				for(Pin pin: pl.getPins()) {
					if(destiny==pin.getPathNum() && Path.Common == pin.getPathType()) {
						if(pl.getNumber()==GameState.getTurnPlayer().getNumber())
							return false;
						count++;
					}
				}
			}
		}
		if(count==2)
			return false;
		
		return true;
	}
	
	private static boolean endMove(Pin p) {
		Barrier.checkForNewBarrier(p);
		if(p.getPathType() == Path.Common) {
			if(!isShelter(p.getPathNum()) && !isExit(p.getPathNum())) {
				for (Player pl :  GameState.getPlayers()) {
					for(Pin pin: pl.getPins()) {
						if(pl != GameState.getTurnPlayer()) {
							if(p.getPathNum()==pin.getPathNum() && p.getPathType() == pin.getPathType()) {
								pin.sendHome();
								ViewMaster.refreshBoard();
								return true;
							}
						}
					}
				}
			}
		}
		ViewMaster.refreshBoard();
		return false;
	}
	
	static boolean isShelter(int n) {
		return (n==9 || n== 22 || n==35 || n==48);
	}
	
	static boolean isExit(int n) {
		return (n==13 || n== 26 || n==39 || n==0);
	}
}
