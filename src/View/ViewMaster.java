package View;

import Controler.Controler;
import Services.ButtonManagers;

public abstract class ViewMaster {
	private final static int LARG_DEFAULT = 960;
	private final static int ALT_DEFAULT = 746;
	
	private static PrimFrame f = null;
	private static SideMenu sideMenu = null;
	private static Controler controler= null;
	private static CentralPanel centralPanel = null;
	private static ButtonManagers buttonManagers = null;
	
	public static void InitializeView() {
		sideMenu = SideMenu.createSideMenu(LARG_DEFAULT, 720);
		buttonManagers = new ButtonManagers();
		sideMenu.register(buttonManagers);
		
		controler = Controler.createControler();
		centralPanel = CentralPanel.createCentralPanel(720);
		f = PrimFrame.createPrimFrame(LARG_DEFAULT,ALT_DEFAULT);
		f.getContentPane().add(sideMenu);
		f.getContentPane().add(centralPanel);
		f.setTitle("Super Ludo");
		f.setVisible(true);
		f.addMouseListener(controler);
	}
	
	public static void refreshBoard() {
		centralPanel.repaint();
	}
	
	public static void refreshDice() {
		SideMenu.refreshDice();
	}
	
	public static void findPin(int posX,int  posY) {
		DrawPin.findPin(posX, posY);
	}
	
	public static boolean isStable() {
		return PlayingDice.isStable();
	}
}
