
public class Movement {
	
	private static Pino selected = null;
	
	public static void select(int posX, int posY) {
		System.out.println(selected);
		if(selected != null) {
			System.out.println(selected.getColor());
			move(posX,posY);
			selected = null;
			
		}
		else {
			for (Pino p : Pino.pinos) {
				if(p.getPosX() == posX && p.getPosY() == posY ) {
					selected = p;
					//System.out.println(selected.getPosX());
					//System.out.println(selected.getPosY());
				}
			}
		}
		System.out.println(selected);
	}
	
	private static void move (int posX, int posY) {
		selected.setPosition(posX, posY);
		//System.out.println(selected.getPosX());
		//System.out.println(selected.getPosY());
		SuperLudo.refreshBoard();
	}
}
