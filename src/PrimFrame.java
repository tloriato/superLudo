import java.awt.*;
import javax.swing.*;

public class PrimFrame extends JFrame {
	/**
	 * Hello World of GUI 
	 */
	
	private static final long serialVersionUID = 1L;
	public final int LARG_DEFAULT = 960;
	public final int ALT_DEFAULT = 720;
	
	public PrimFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		int x=screenSize.width/2-LARG_DEFAULT/2;
		int y=screenSize.height/2-ALT_DEFAULT/2;
		
		this.setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		/* Menu Lateral */
		int xMenu = (int)(LARG_DEFAULT * 0.80);
		int widthMenu = LARG_DEFAULT - xMenu;
		
		Panel pRight = new Panel(null);
		pRight.setBackground(Color.gray);
		pRight.setBounds(xMenu, 0, widthMenu, ALT_DEFAULT);
		
		JButton newGame = new JButton("Novo Jogo");
		newGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125), (int) (widthMenu * 0.75), 45);
		
		JButton loadGame = new JButton("Carregar Jogo");
		loadGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 25 + 45 * 1, (int) (widthMenu * 0.75), 45);
		
		JButton saveGame = new JButton("Salvar");
		saveGame.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 50 + 45 * 2, (int) (widthMenu * 0.75), 45);
		
		Label currentlyPlaying = new Label("À Jogar:");
		currentlyPlaying.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 100 + 45 * 3, (int) (widthMenu * 0.75), 45);
		currentlyPlaying.setAlignment(Label.CENTER);
		
		JButton throwDice = new JButton("Lançar Dado");
		throwDice.setBounds((int) (widthMenu * 0.125), (int) (ALT_DEFAULT * 0.125) + 150 + 45 * 4, (int) (widthMenu * 0.75), 45);
		
		pRight.add(newGame);
		pRight.add(loadGame);
		pRight.add(saveGame);
		pRight.add(currentlyPlaying);
		pRight.add(throwDice);		
		/*Fim do Menu Lateral */
		
		add(pRight);
		
	}

}
