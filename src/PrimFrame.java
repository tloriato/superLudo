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
		
		/* Menu Lateral */
		Panel pRight = new Panel(new GridLayout(6,1));
		pRight.setBackground(Color.gray);
		
		JButton newGame = new JButton("Novo Jogo");
		JButton loadGame = new JButton("Carregar Jogo");
		JButton saveGame = new JButton("Salvar");
		Label currentlyPlaying = new Label("À Jogar:");
		JButton throwDice = new JButton("Lançar Dado");
		
		pRight.add(newGame);
		pRight.add(loadGame);
		pRight.add(saveGame);
		pRight.add(currentlyPlaying);
		pRight.add(throwDice);		
		/*Fim do Menu Lateral */
		
		add(pRight, BorderLayout.EAST);
		
	}

}
