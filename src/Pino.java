import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Pino {
	
	private int posX;
	private int posY;
	private Color color;
	public static ArrayList<Pino> pinos = new ArrayList();
	
	
	public Pino(int posX,int posY,Color color) {
		this.posX=posX;
		this.posY=posY;
		this.color=color;
		pinos.add(this);
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
	
	public Color getColor() {
		return color;
	}
	
	public void drawPin(Graphics2D g2d, int squareSize) {
		double centerX = (this.posX+0.5)*squareSize;
		double centerY = (this.posY+0.5)*squareSize;
		double pinRadius = squareSize*0.45;
		Ellipse2D circ= new Ellipse2D.Double();
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius,centerY+pinRadius);
		
		g2d.setPaint(Color.WHITE);
		g2d.fill(circ);
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius*0.8,centerY+pinRadius*0.8);
		g2d.setPaint(this.color);
		g2d.fill(circ);
		
	}
	
	public static void initializePinos() {
		new Pino(2,2,Color.GREEN);
		new Pino(2,3,Color.GREEN);
		new Pino(3,3,Color.GREEN);
		new Pino(3,2,Color.GREEN);
		
		new Pino(11,2,Color.YELLOW);
		new Pino(11,3,Color.YELLOW);
		new Pino(12,3,Color.YELLOW);
		new Pino(12,2,Color.YELLOW);
		
		new Pino(2,11,Color.RED);
		new Pino(2,12,Color.RED);
		new Pino(3,12,Color.RED);
		new Pino(3,11,Color.RED);
		
		new Pino(11,11,Color.BLUE);
		new Pino(11,12,Color.BLUE);
		new Pino(12,12,Color.BLUE);
		new Pino(12,11,Color.BLUE);

	}

	
}
