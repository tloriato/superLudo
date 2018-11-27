package View;
import javax.swing.JPanel;

import Models.Pin;
import Models.Player;
import Services.GameState;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class CentralPanel extends JPanel {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Tabuleiro t;
	private static CentralPanel centralPanel = null;
	//private int squareNumbers=15;
	
	private CentralPanel(int size) {
		this.t = Tabuleiro.createTabuleiro(size);
	}
	
	public static CentralPanel createCentralPanel(int size) {
		if(centralPanel != null)
			return null;
		centralPanel = new CentralPanel(size);
		return centralPanel;
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//g.drawString("Primeiro Programa Gráfico",TXT_X,TXT_Y);
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		this.t.drawBoard(g2d);
		
		DrawPin.drawPins(g2d,t.getSquareSize());

	}
	
}
