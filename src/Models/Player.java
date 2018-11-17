package Models;

import java.util.ArrayList;

public class Player {
	private Pin[] pins= new Pin[4];
	private int playerNumber;

	public Player(int number, int posX, int posY) {
		this.playerNumber = number;
		this.pins[0] = new Pin(posX,posY);
		this.pins[1] = new Pin(posX,posY+1);
		this.pins[2] = new Pin(posX+1,posY+1);
		this.pins[3] = new Pin(posX+1,posY);		
	}
	
	public Pin checkForPin(int posX, int posY) {
		for (Pin p : pins) {
			if(p.getPosX()==posX && p.getPosY()==posY)
				return p;
		}
		return null;
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
