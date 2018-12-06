package View;
import java.awt.*;
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

import Services.GameState;
import Services.ServiceFacade;

public class SideMenu extends Panel {
	
	private static final long serialVersionUID = -3469803300168088129L;
	private static PlayingDice playingDice = null;
	private static SideMenu sideMenu = null;
	public static JButton throwDice = null;
	private static JButton newGame = null;
	public static JComboBox diceNumbers = null;
	
	public static SideMenu createSideMenu(int LARG_DEFAULT, int ALT_DEFAULT) {
		if(sideMenu != null)
			return null;
		sideMenu = new SideMenu(LARG_DEFAULT, ALT_DEFAULT);
		return sideMenu;
	}
	
	private ActionListener saveDialog = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
			fileChooser.setDialogTitle("Onde desejar salvar o jogo: ");
			fileChooser.setAcceptAllFileFilterUsed(false);												
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SuperLudo Save File", "ssf");
			fileChooser.addChoosableFileFilter(filter);													
			int file = fileChooser.showSaveDialog(null);												
			if (file == JFileChooser.APPROVE_OPTION) {
				String saveFilePath = fileChooser.getSelectedFile().toString();							
				if (!saveFilePath.substring(saveFilePath.length() - 4).equals(".ssf")) {
					System.out.println(saveFilePath.substring(saveFilePath.length() - 4));
					saveFilePath = saveFilePath + ".ssf";
				}
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
					writer.write(ServiceFacade.getFullState());														
					writer.close();
					JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!", "Arquivo salvo", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	};
	
	private ActionListener loadDialog = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Arquivo para carregar o jogo: ");
			fileChooser.setAcceptAllFileFilterUsed(false);			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SuperLudo Save File", "ssf");
			fileChooser.addChoosableFileFilter(filter);
			int file = fileChooser.showOpenDialog(null);
			if (file == JFileChooser.APPROVE_OPTION) {
				String loadFilePath = fileChooser.getSelectedFile().toString();
				if (!loadFilePath.substring(loadFilePath.length() - 4).equals(".ssf")) {
					JOptionPane.showMessageDialog(null, "Arquivo nao compativel", "Extencao errada!", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(loadFilePath));
						String fullLoadedState = reader.readLine();
						ServiceFacade.loadState(fullLoadedState);
					} catch (IOException err) {
						err.printStackTrace();
					}
				}
			}
		}
	};
	
	private ActionListener playDice = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SideMenu.throwDice.setEnabled(false);
			SideMenu.diceNumbers.setEnabled(false);
			if (SideMenu.diceNumbers.getSelectedIndex() == 0) {
				SideMenu.playingDice.throwDice();
			} else {
				SideMenu.playingDice.gameDice(SideMenu.diceNumbers.getSelectedIndex());
			}
	    }
	};
	
	private ActionListener resetGame = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ServiceFacade.resetGame();
		}
	};
	
	private SideMenu(int LARG_DEFAULT, int ALT_DEFAULT) {
		/* Menu Lateral */
		int xMenu = (int)(ALT_DEFAULT);
		int widthMenu = LARG_DEFAULT - xMenu;
		
		this.setLayout(null);
		this.setBackground(Color.gray);
		this.setBounds(xMenu, 0, widthMenu, ALT_DEFAULT);
		
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
	
}
