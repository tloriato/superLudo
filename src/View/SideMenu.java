package View;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Controler.Observer;
import Controler.Subject;
import Services.ServiceFacade;

public class SideMenu extends Panel implements Subject {
	public static PlayingDice playingDice = null;
	private static SideMenu sideMenu = null;
	public static JButton throwDice = null;
	private static JButton newGame = null;
	public static JComboBox diceNumbers = null;
	
	private static int lastPressed;
	private static List<Observer> observers;
	private static int nounce;
	
	public static SideMenu createSideMenu(int LARG_DEFAULT, int ALT_DEFAULT) {
		if(sideMenu != null)
			return null;
		sideMenu = new SideMenu(LARG_DEFAULT, ALT_DEFAULT);
		return sideMenu;
	}
	
	private ActionListener saveDialog = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SideMenu.lastPressed = 2;
			SideMenu.nounce += 1;
			notifyObservers();
		}
	};
	
	private ActionListener loadDialog = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SideMenu.lastPressed = 1;
			SideMenu.nounce += 1;
			notifyObservers();
		}
	};
	
	private ActionListener playDice = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SideMenu.lastPressed = 3;
			SideMenu.nounce += 1;
			notifyObservers();
	    }
	};
	
	private ActionListener resetGame = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SideMenu.lastPressed = 0;
			SideMenu.nounce += 1;
			notifyObservers();
		}
	};
	
	private SideMenu(int LARG_DEFAULT, int ALT_DEFAULT) {
		/* Menu Lateral */
		int xMenu = (int)(ALT_DEFAULT);
		int widthMenu = LARG_DEFAULT - xMenu;
		
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setBounds(xMenu, 0, widthMenu, ALT_DEFAULT);
		SideMenu.observers=new ArrayList<>();
		SideMenu.nounce = 0;
		
		SideMenu.newGame = new JButton("Novo Jogo");
		SideMenu.newGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125), (int) (widthMenu * 0.75), 45);
		SideMenu.newGame.addActionListener(resetGame);
		
		JButton loadGame = new JButton("Carregar Jogo");
		loadGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 25 + 45 * 1, (int) (widthMenu * 0.75), 45);
		loadGame.addActionListener(loadDialog);
		
		JButton saveGame = new JButton("Salvar");
		saveGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 50 + 45 * 2, (int) (widthMenu * 0.75), 45);
		saveGame.addActionListener(saveDialog);
		
		Label currentlyPlaying = new Label("A Jogar:");
		currentlyPlaying.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 100 + 45 * 3, (int) (widthMenu * 0.75), 45);
		currentlyPlaying.setAlignment(Label.CENTER);
		currentlyPlaying.setFont(new Font("Serif", Font.BOLD, 21));
		
		SideMenu.playingDice = PlayingDice.createPlayingDice((int) (widthMenu * 0.75), (int) (widthMenu * 0.75));
		playingDice.setBounds((int) (widthMenu * 0.175), (int) (ALT_DEFAULT * 0.125) + 150 + 45 * 3, (int) (widthMenu * 0.65), (int) (widthMenu * 0.65));
		
		SideMenu.throwDice = new JButton("Lancar Dado");
		SideMenu.throwDice.setBounds((int) (widthMenu * 0.125), ((int) (ALT_DEFAULT * 0.125) + 200 + 45 * 3) + (int) (widthMenu * 0.65), (int) (widthMenu * 0.75), 45);
		SideMenu.throwDice.addActionListener(playDice);
		
		String[] numbers = {"Dado Aleatorio", "Dado #1", "Dado #2", "Dado #3", "Dado #4", "Dado #5", "Dado #6"};
		SideMenu.diceNumbers = new JComboBox(numbers);
		SideMenu.diceNumbers.setSelectedIndex(0);
		SideMenu.diceNumbers.setBounds((int) (widthMenu * 0.125), ((int) (ALT_DEFAULT * 0.125) + 200 + 45 * 4) + (int) (widthMenu * 0.65), (int) (widthMenu * 0.75), 45);
		
		this.add(newGame);
		this.add(loadGame);
		this.add(saveGame);
		this.add(currentlyPlaying);
		this.add(throwDice);
		this.add(playingDice);
		this.add(diceNumbers);
	}
	
	public static void refreshDice() {
		playingDice.repaint();
	}

	
	public static int getLastPressed() {
		return SideMenu.lastPressed;
	}
	public static int getNounce() {
		return SideMenu.nounce;
	}
	
	@Override
	public void register(Observer obj) {
		if(!observers.contains(obj)) observers.add(obj);
	}
	

	@Override
	public void unregister(Observer obj) {
		observers.remove(obj);
		
	}

	@Override
	public void notifyObservers() {
		for (Observer obj : SideMenu.observers) {
			obj.update();
		}
	}

	
}
