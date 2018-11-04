import java.awt.*;

@SuppressWarnings("serial")
public class PlayingDice extends Panel {
	private int width;
	private int height;
	private int player;
	
	public PlayingDice(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, width, height);  
	}

}
