import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Tabuleiro t;
	//private int squareNumbers=15;
	
	public CentralPanel(int size) {
		this.t = new Tabuleiro(size);
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//g.drawString("Primeiro Programa Gráfico",TXT_X,TXT_Y);
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		this.t.drawBoard(g2d);
		
		int squareSize= this.t.getSquareSize();
		for (Pino p : Pino.pinos) {
				p.drawPin(g2d, squareSize);
			}
	}
	
}
