package View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Controler.Observer;
import Services.ServiceFacade;

public class ButtonManagers implements Observer {
	
	private int nounce = 0;

	@Override
	public void update() {
		int action = SideMenu.getLastPressed();
		int nounce = SideMenu.getNounce();
		
		if (nounce > this.nounce) {
			if (action == 0) {
				resetButton();
				updateNounce();				
			}
			
			else if (action == 1) {
				loadButton();
				updateNounce();				
			}
			
			else if (action == 2) {
				saveButton();
				updateNounce();				
			}
			
			else if (action == 3) {
				throwButton();
				updateNounce();				
			}
		}		
	}
	
	private void updateNounce() {
		this.nounce = this.nounce + 1;	
	}

	private void resetButton() {
		ServiceFacade.resetGame();
	}
	
	private void loadButton() {
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
					reader.close();
					ServiceFacade.loadState(fullLoadedState);
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
		
	}
	
	private void saveButton() {
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
	
	private void throwButton() {
		SideMenu.throwDice.setEnabled(false);
		SideMenu.diceNumbers.setEnabled(false);
		if (SideMenu.diceNumbers.getSelectedIndex() == 0) {
			SideMenu.playingDice.throwDice();
		} else {
			SideMenu.playingDice.gameDice(SideMenu.diceNumbers.getSelectedIndex());
		}
	}
}
