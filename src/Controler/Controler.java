package Controler;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Services.Movement;
import View.DrawPin;
import View.PlayingDice;
import View.ViewMaster;


public class Controler implements  MouseListener {
	private static Controler controler = null;
	
	private Controler() {
		
	}
	
	public static Controler createControler() {
		if(controler!=null)
			return null;
		controler = new Controler();
		return controler;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x, y;

		x = e.getX();
		y = e.getY();
		
		int posX = x / 48;
		int posY = (y - 26) /48;
		
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(posX);
		//System.out.println(posY);
		if(ViewMaster.isStable()) 
			ViewMaster.findPin(posX, posY);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
