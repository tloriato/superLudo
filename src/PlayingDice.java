import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class PlayingDice extends Panel {
	private int width;
	private int height;
	private int player;
	private static int dice;
	private BufferedImage[] image = new BufferedImage[6];
	
	public PlayingDice(int width, int height) {
		this.width = width;
		this.height = height;
		PlayingDice.dice = 6;
		
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
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, width, height);
		g.drawImage(this.image[PlayingDice.dice - 1], 29, 26, this);
	}
	
	public int throwDice() {
		new Thread(new Runnable() {
			public void run() {
				int times = 3 + (int)(Math.random() * ((7 - 3) + 1));
				try {
					for(int i = 0; i < times; i++) {
						PlayingDice.dice = (int)(Math.random() * 6 + 1);
						repaint();
						Thread.sleep((225 * i/2) + 150);
					}
					PlayingDice.dice = (int)(Math.random() * 6 + 1);
					repaint();
					
				} catch (InterruptedException ie) {
					System.out.println("Error: animacao do dado || " + ie);
					PlayingDice.dice = (int)(Math.random() * 6 + 1);
					repaint();
				}
			}
		}).start();	
		return PlayingDice.dice;
	}
	
	public void blink(int n) {
		if (n < 0) return;
		//TODO: BlinkTwice after dice is set
	}
}
