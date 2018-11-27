package View;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Services.DiceLogic;
import Services.GameState;
import Services.Movement;
import Services.ServiceFacade;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class PlayingDice extends Panel {
	private int width;
	private int height;
	private int player;
	private static int animatedDice;
	private static int blink;
	private BufferedImage[] image = new BufferedImage[6];
	private static PlayingDice playingDice = null;
	
	private static boolean stable = true;
	
	public PlayingDice(int width, int height) {
		this.width = width;
		this.height = height;
		animatedDice = 6;
		blink = 2;
		for (int i = 0; i < 6; i++) {
			String path = "resources/Dado" + (i+1) + ".png";
			try {
				this.image[i] = ImageIO.read(new File(path));
			} catch (IOException ex) {
				System.out.println("Erro na leitura das imagens");
				System.out.println(ex.toString());
				System.exit(99); // Should we actually exited here? Better treated on main.
			}
		}
		
		
	}
	
	public static PlayingDice createPlayingDice(int width, int height) {
		if(playingDice != null)
			return null;
		playingDice = new PlayingDice( width, height);
		return playingDice;
	}

	
	public void paint(Graphics g) {
		int player = ServiceFacade.getTurnPlayer().getNumber();
		Color color;
		if(player ==1)
			color = Color.green;
		else if (player == 2)
			color = Color.yellow;
		else if(player ==3)
			color = Color.blue;
		else
			color = Color.red;
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.drawImage(this.image[animatedDice -1], 29, 26, this);
		if(blink<2) {
			blink(g);
		}
	}
	
	public void throwDice() {
		if(!stable)
			return;
		stable = false;
		new Thread(new Runnable() {
			public void run() {
				if(ServiceFacade.runDice()) {
					animate();
					PlayingDice.animatedDice = ServiceFacade.getDice();
					blink=0;
					repaint();
				}
				else {
					System.out.println("Dado ja foi jogado");
				}
				ServiceFacade.forcedMove();
				stable = true;
			}
		}).start();
	}
	
	private void animate() {
		try {
			blink = 2;
			int times = 3 + (int)(Math.random() * ((7 - 3) + 1));
			for(int i = 0; i < times; i++) {
				PlayingDice.animatedDice = (int)(Math.random() * 6 + 1);
				repaint();
				Thread.sleep((225 * i/2) + 150);
			}
			repaint();
			
		} catch (InterruptedException ie) {
			System.out.println("Error: animacao do dado || " + ie);
		}
	}
	
	public void blink(Graphics g) {
		try { 
			Thread.sleep (100);
		} 
		catch (InterruptedException ex) {
			System.out.println("Erro Blink");
		}
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		try { 
			Thread.sleep (100);
		} 
		catch (InterruptedException ex) {
			System.out.println("Erro Blink");
		}
		blink++;
		repaint();
	}
	
	static boolean isStable() {
		return stable;
	}
	
	
}
