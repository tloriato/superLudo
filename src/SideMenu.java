import java.awt.*;
import javax.swing.*;

public class SideMenu extends Panel {
	
	private static final long serialVersionUID = -3469803300168088129L;

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
		
		Label currentlyPlaying = new Label("À Jogar:");
		currentlyPlaying.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 100 + 45 * 3, (int) (widthMenu * 0.75), 45);
		currentlyPlaying.setAlignment(Label.CENTER);
		currentlyPlaying.setFont(new Font("Serif", Font.BOLD, 21));
		
		PlayingDice currentlyDice = new PlayingDice((int) (widthMenu * 0.75), (int) (widthMenu * 0.75));
		currentlyDice.setBounds((int) (widthMenu * 0.175), (int) (ALT_DEFAULT * 0.125) + 150 + 45 * 3, (int) (widthMenu * 0.65), (int) (widthMenu * 0.65));
		
		
		JButton throwDice = new JButton("Lançar Dado");
		throwDice.setBounds((int) (widthMenu * 0.125), ((int) (ALT_DEFAULT * 0.125) + 200 + 45 * 3) + (int) (widthMenu * 0.65), (int) (widthMenu * 0.75), 45);
		
		
		
		this.add(newGame);
		this.add(loadGame);
		this.add(saveGame);
		this.add(currentlyPlaying);
		this.add(throwDice);
		this.add(currentlyDice);
	}
}
