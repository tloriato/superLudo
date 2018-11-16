package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Services.PlayingDice;

public class SideMenu extends Panel {
	
	private static final long serialVersionUID = -3469803300168088129L;
	private static PlayingDice playingDice = null;

	public SideMenu(int LARG_DEFAULT, int ALT_DEFAULT) {
		/* Menu Lateral */
		int xMenu = (int)(ALT_DEFAULT);
		int widthMenu = LARG_DEFAULT - xMenu;
		
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setBounds(xMenu, 0, widthMenu, ALT_DEFAULT);
		
		JButton newGame = new JButton("Novo Jogo");
		newGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125), (int) (widthMenu * 0.75), 45);
		
		JButton loadGame = new JButton("Carregar Jogo");
		loadGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 25 + 45 * 1, (int) (widthMenu * 0.75), 45);
		
		JButton saveGame = new JButton("Salvar");
		saveGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 50 + 45 * 2, (int) (widthMenu * 0.75), 45);
		
		Label currentlyPlaying = new Label("� Jogar:");
		currentlyPlaying.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 100 + 45 * 3, (int) (widthMenu * 0.75), 45);
		currentlyPlaying.setAlignment(Label.CENTER);
		currentlyPlaying.setFont(new Font("Serif", Font.BOLD, 21));
		
		PlayingDice playingDice = new PlayingDice((int) (widthMenu * 0.75), (int) (widthMenu * 0.75));
		playingDice.setBounds((int) (widthMenu * 0.175), (int) (ALT_DEFAULT * 0.125) + 150 + 45 * 3, (int) (widthMenu * 0.65), (int) (widthMenu * 0.65));
		
		JButton throwDice = new JButton("Lan�ar Dado");
		throwDice.setBounds((int) (widthMenu * 0.125), ((int) (ALT_DEFAULT * 0.125) + 200 + 45 * 3) + (int) (widthMenu * 0.65), (int) (widthMenu * 0.75), 45);
		throwDice.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  System.out.println(playingDice.throwDice());
	      }
	    });
		
		
		this.add(newGame);
		this.add(loadGame);
		this.add(saveGame);
		this.add(currentlyPlaying);
		this.add(throwDice);
		this.add(playingDice);
	}
}
