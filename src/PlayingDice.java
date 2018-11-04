import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class PlayingDice extends Panel {
	private int width;
	private int height;
	private int player;
	private int dice;
	private BufferedImage[] image = new BufferedImage[6];
	
	public PlayingDice(int width, int height) {
		this.width = width;
		this.height = height;
		this.dice = 0;
		
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
		g.drawImage(this.image[this.dice], 29, 26, this);
	}

}
