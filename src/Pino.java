import java.awt.*;

public class Pino {
	
	public int posX;
	public int posY;
	public Color color;
	
	
	public Pino(int posX,int posY,Color color) {
		this.posX=posX;
		this.posY=posY;
		this.color=color;
	}
	
	public void setPosition(int posX,int posY) {
		this.posX=posX;
		this.posY=posY;
	}
}
