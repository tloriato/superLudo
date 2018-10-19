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
		
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
