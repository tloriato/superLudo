package View;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import Services.ServiceFacade;

import java.awt.*;
import java.nio.charset.Charset;

public class CentralPanel extends JPanel {

	private Tabuleiro t;
	private static CentralPanel centralPanel = null;
	
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

		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		this.t.drawBoard(g2d);
		
		DrawPin.drawPins(g2d,t.getSquareSize());

	}

	public static void finalizeGame(int[] positions, int[] numPins) {
		
		String finalMessage = "";
		String firstPosition = "";
		String secondPosition = "";
		String thirdPosition = "";
		String forthPosition = "";
		
		// positions
		// positions[0] = 1 | o jogador 1 ficou na posição 1
		// positions[1] = 3 | o jogador 2 ficou na posição 3
		
		for (int i = 0; i < positions.length; i++) {
			if (positions[i] == 1) {
				firstPosition = winnerMessage(1, i, numPins[i]);
			} else if (positions[i] == 2) {
				if (secondPosition.equals("")) {
					secondPosition = winnerMessage(2, i, numPins[i]);
				} else if (thirdPosition.equals("")) {
					thirdPosition = winnerMessage(2, i, numPins[i]);
				} else if (forthPosition.equals("")) {
					forthPosition = winnerMessage(2, i, numPins[i]);
				}	
			} else if (positions [i] == 3) {
				if (thirdPosition.equals("")) {
					thirdPosition = winnerMessage(3, i, numPins[i]);
				} else if (forthPosition.equals("")) {
					forthPosition = winnerMessage(3, i, numPins[i]);
				}	 
			} else if (positions[i] == 4) {
				forthPosition = winnerMessage(4, i, numPins[i]);
			}
		}
		
		byte[] emojiTrophy = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x8F, (byte)0x86};
		String emojiTrophyString = new String(emojiTrophy, Charset.forName("UTF-8"));

		finalMessage = firstPosition + secondPosition + thirdPosition + forthPosition;
		JOptionPane.showMessageDialog(null, finalMessage, emojiTrophyString + " Fim de Jogo!", JOptionPane.PLAIN_MESSAGE);
		int choice = JOptionPane.showConfirmDialog(null, "Deseja jogar outra partida?", "Como prosseguir?", JOptionPane.YES_NO_OPTION);
		
		if (choice == JOptionPane.YES_OPTION) {
			ServiceFacade.resetGame();
		} else {
			System.exit(0);
		}
		
	}
	
	private static String winnerMessage(int place, int index, int points) {
		String hexColor;
		if (index == 0) {
			hexColor = "#00FF00";
		} else if (index == 1) {
			hexColor = "#BDB76B";
		} else if (index == 2) {
			hexColor = "#1E90FF";
		} else {
			hexColor = "#FF0000";
		}
		String sHex = "<font color=" + hexColor + ">";
		String message = "<html>" + place + "o Lugar: " + sHex + "Player #" + (index + 1) + "</font> com " + points + " pontos!" + "\n";
		return message;
	}
	
}
