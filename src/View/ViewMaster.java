package View;

import Controler.Controler;

public abstract class ViewMaster {
	private final static int LARG_DEFAULT = 960;
	private final static int ALT_DEFAULT = 746;
	
	private static PrimFrame f = null;
	private static SideMenu sideMenu = null;
	private static Controler controler= null;
	private static CentralPanel centralPanel = null;
	
	public static void InitializeView() {
		sideMenu = SideMenu.createSideMenu(LARG_DEFAULT, 720);
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
}
