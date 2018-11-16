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
		
		for (Player pl : GameState.getPlayers() ) {
			for(Pin pi : pl.getPins()){
				this.t.drawPin(g2d, pi.getPosX(),pi.getPosY(),pl.getNumber());
			}
		}
	}
	
}
