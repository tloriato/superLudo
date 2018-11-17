package Models;

import java.util.ArrayList;

public class Player {
	private Pin[] pins= new Pin[4];
	private int playerNumber;

	public Player(int number, int posX, int posY) {
		this.playerNumber = number;
		this.pins[0] = new Pin(0);
		this.pins[1] = new Pin(1);
		this.pins[2] = new Pin(2);
		this.pins[3] = new Pin(3);		
	}
	
	public Player(String number, String p1x, String p1y, String p2x, String p2y, String p3x, String p3y, String p4x, String p4y) {
		this.playerNumber = Integer.parseInt(number);
		//this.pins[0] = new Pin(Integer.parseInt(p1x), Integer.parseInt(p1y));
		//this.pins[1] = new Pin(Integer.parseInt(p2x), Integer.parseInt(p2y));
		//this.pins[2] = new Pin(Integer.parseInt(p3x), Integer.parseInt(p3y));
		//this.pins[3] = new Pin(Integer.parseInt(p4x), Integer.parseInt(p4y));
	}
	
	public Pin checkForPin(int posX, int posY) {
		/*for (Pin p : pins) {
			if(p.getPosX()==posX && p.getPosY()==posY)
				return p;
		}
		return null;*/
		return null;
	}
	
	public boolean isPinOwner(Pin p) {
		for (Pin pi : pins) {
			if(pi == p)
				return true;
		}
		return false;
	}
	
	public Pin[] getPins() {
		return this.pins;
	}
	
	public int getNumber() {
		return this.playerNumber;
	}
	
	public Pin getBeginPin() {
		for (Pin p: pins) {
			if(p.isBeginZone())
				return p;
		}
		return null;
	}
}
