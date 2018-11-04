	

public class SuperLudo {
	
	//static private ArrayList<Pino> pinos = new ArrayList();
	private final static int LARG_DEFAULT = 960;
	private final static int ALT_DEFAULT = 720;
	
	private static PrimFrame f = null;
	private static SideMenu sideMenu = null;
	private static Controler controler= null;
	private static CentralPanel centralPanel = null;
	
	public static void main(String[] args) {
		Pino.initializePinos();
		sideMenu = new SideMenu(LARG_DEFAULT, ALT_DEFAULT);
		controler = new Controler();
		createBoard();
	}
	
	private static void createBoard() {
		centralPanel = new CentralPanel(ALT_DEFAULT);
		f = new PrimFrame(LARG_DEFAULT,ALT_DEFAULT);
		f.getContentPane().add(sideMenu);
		f.getContentPane().add(centralPanel);
		f.setTitle("Super Ludo");
		f.setVisible(true);
		f.addMouseListener(controler);
	}
	
	public static void refreshBoard() {
		f.setVisible(false);
		createBoard();	
	}
	

}
