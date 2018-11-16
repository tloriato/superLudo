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
	
}
