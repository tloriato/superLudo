package View;
import java.awt.*;
import javax.swing.*;

public class PrimFrame extends JFrame {
	/**
	 * Hello World of GUI 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public PrimFrame(int LARG_DEFAULT, int ALT_DEFAULT) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2- LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		this.setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
