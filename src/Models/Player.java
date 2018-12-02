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
	
	public Player(int playerNumber, Pin one, Pin two, Pin three, Pin four) {
		this.playerNumber = playerNumber;
		this.pins[0] = one;
		this.pins[1] = two;
		this.pins[2] = three;
		this.pins[3] = four;
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
