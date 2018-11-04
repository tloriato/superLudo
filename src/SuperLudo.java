import java.awt.Color;
import java.util.ArrayList;

public class SuperLudo {
	
	//static private ArrayList<Pino> pinos = new ArrayList();
	private final static int LARG_DEFAULT = 960;
	private final static int ALT_DEFAULT = 720;
	
	public static void main(String[] args) {	
		Tabuleiro t = new Tabuleiro(ALT_DEFAULT);
		initializePinos(t);;
		
		SideMenu sideMenu = new SideMenu(LARG_DEFAULT, ALT_DEFAULT);
		PrimFrame f = new PrimFrame(LARG_DEFAULT,ALT_DEFAULT);
		f.getContentPane().add(sideMenu);
		f.getContentPane().add(t);
		f.setTitle("Super Ludo");
		f.setVisible(true);
	}
	
	private static void initializePinos(Tabuleiro t) {
		t.addPin(new Pino(2,2,Color.GREEN));
		t.addPin(new Pino(2,3,Color.GREEN));
		t.addPin(new Pino(3,3,Color.GREEN));
		t.addPin(new Pino(3,2,Color.GREEN));
		
		t.addPin(new Pino(11,2,Color.YELLOW));
		t.addPin(new Pino(11,3,Color.YELLOW));
		t.addPin(new Pino(12,3,Color.YELLOW));
		t.addPin(new Pino(12,2,Color.YELLOW));
		
		t.addPin(new Pino(2,11,Color.RED));
		t.addPin(new Pino(2,12,Color.RED));
		t.addPin(new Pino(3,12,Color.RED));
		t.addPin(new Pino(3,11,Color.RED));
		
		t.addPin(new Pino(11,11,Color.BLUE));
		t.addPin(new Pino(11,12,Color.BLUE));
		t.addPin(new Pino(12,12,Color.BLUE));
		t.addPin(new Pino(12,11,Color.BLUE));

	}
	

}
