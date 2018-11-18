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

public class SideMenu extends Panel {
	
	private static final long serialVersionUID = -3469803300168088129L;
	private static PlayingDice playingDice = null;
	
	private ActionListener saveDialog = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // Abre no diret�rio Desktop
			fileChooser.setDialogTitle("Onde desejar salvar o jogo: ");
			fileChooser.setAcceptAllFileFilterUsed(false);												// Filtra para mostrar arquivos .ssf
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SuperLudo Save File", "ssf");
			fileChooser.addChoosableFileFilter(filter);													// Amarra filtro � janela
			int file = fileChooser.showSaveDialog(null);												// Efetivamente abre a janela
			if (file == JFileChooser.APPROVE_OPTION) {
				String saveFilePath = fileChooser.getSelectedFile().toString();							// Monta string do arquivo pra salvar
				if (!saveFilePath.substring(saveFilePath.length() - 4).equals(".ssf")) {
					System.out.println(saveFilePath.substring(saveFilePath.length() - 4));
					saveFilePath = saveFilePath + ".ssf";
				}
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath));
					writer.write(GameState.getFullState());														
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
					JOptionPane.showMessageDialog(null, "Arquivo n�o compat�vel", "Extens�o errada!", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(loadFilePath));
						String fullLoadedState = reader.readLine();
						GameState.loadState(fullLoadedState);
					} catch (IOException err) {
						err.printStackTrace();
					}
				}
			}
		}
	};
	
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
		loadGame.addActionListener(loadDialog);
		
		JButton saveGame = new JButton("Salvar");
		saveGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 50 + 45 * 2, (int) (widthMenu * 0.75), 45);
		saveGame.addActionListener(saveDialog);
		
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
	    	  playingDice.throwDice();
	      }
	    });
		
		
		this.add(newGame);
		this.add(loadGame);
		this.add(saveGame);
		this.add(currentlyPlaying);
		this.add(throwDice);
		this.add(playingDice);
	}
	
	public static void refreshDice() {
		playingDice.repaint();
	}
	
}
