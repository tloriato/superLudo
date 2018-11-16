package Services;

import Models.Player;

public class GameState {
	private static Player[] players= new Player[4];
	private static int turnPlayer;
	private static int round;
	private static int lastDice;
	
	public static void initializeGame() {
		players[0] = new Player(1,2,2);
		players[1] = new Player(2,11,2);
		players[2] = new Player(3,11,11);
		players[3] = new Player(4,2,11);
		turnPlayer = 0;
		lastDice = 0;
		round = 0;
	}
	
	public static Player getTurnPlayer() {
		System.out.println(turnPlayer);
		return players[turnPlayer];
	}
	
	public static Player[] getPlayers() {
		return players;
	}
	
	public static void setDice(int num) {
		lastDice = num;
	}
	
	public static int getDice() {
		return 5;
		//return lastDice;
	}
	
	public static void nextTurn() {
		if(turnPlayer==3) {
			turnPlayer=0;
			round++;
		}
		else
			turnPlayer++;
		if(round == 0) 
			firstTurn();
		lastDice = 0;
	}
	
	public static void firstTurn() {
		Movement.FirstMove (players[turnPlayer]);
	}
	
}
