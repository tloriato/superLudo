package Models;

public class Pin {
	
	private int posX;
	private int posY;
	
	public Pin(int posX,int posY) {
		this.posX=posX;
		this.posY=posY;
	}
	
	public void setPosition(int posX,int posY) {
		this.posX=posX;
		this.posY=posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public boolean isBeginZone() {
		if(posX < 6 && posY < 6)
			return true;
		if(posX < 6 && posY > 8)
			return true;
		if(posX > 8 && posY < 6)
			return true;
		if(posX > 8 && posY > 8)
			return true;
		return false;
	}
	
	public boolean isEndZone() {
		if(posX > 5 && posX < 9 && posY >5 && posY < 9)
			return true;
		return false;
	}
}
