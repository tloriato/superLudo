package Services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Controler.Observer;
import Controler.Subject;
import Models.Pin;
import Models.Player;
import View.ViewMaster;

public abstract class GameState {
	private static Player[] players= new Player[4];
	private static int turnPlayer;
	private static int round;
	private static int lastDice;
	private static int countSix;
	
	
	static void initializeGame() {
		players[0] = new Player(1,2,2);
		players[1] = new Player(2,11,2);
		players[2] = new Player(3,11,11);
		players[3] = new Player(4,2,11);
		turnPlayer = 0;
		lastDice = 0;
		round = 0;
		countSix = 0;
	}
	
	static Player getTurnPlayer() {
		System.out.println(turnPlayer);
		return players[turnPlayer];
	}
	
	static Player[] getPlayers() {
		return players;
	}
	
	static void setDice(int num) {
		lastDice = num;
		System.out.println(lastDice);
	}
	
	static int getDice() {
		return lastDice;
	}
	
	static void nextTurn() {
		if(turnPlayer==3) {
			turnPlayer=0;
			round++;
		}
		else
			turnPlayer++;
		if(round == 0) 
			firstTurn();
		lastDice = 0;
		countSix =0;
	}
	
	static void firstTurn() {
		Movement.firstMove (players[turnPlayer]);
	}
	
	static int getCountSix() {
		return countSix;
	}
	
	static void addCountSix() {
		countSix++;
	}
	
	static void zeroCountSix() {
		countSix = 0;
	}
	
	static String getFullState() {
		List<String> state = new ArrayList<>();
		state.add(Integer.toString(turnPlayer));
		state.add(Integer.toString(round));
		state.add(Integer.toString(lastDice));
		state.add(Integer.toString(countSix));
		for (Player player: players) {
			state.add(Integer.toString(player.getNumber()));
			for (Pin pin: player.getPins()) {
				state.add(pin.getPathType().name());
				state.add(Integer.toString(pin.getPathNum()));
				state.add(Integer.toString(pin.getInitialPos()));
			}
		}
		return String.join(",", state);
	}

	static void loadState(String fullLoadedState) {
		String[] st = fullLoadedState.split(",");
		
		Pin pOnePOne = new Pin(st[5], Integer.parseInt(st[6]), Integer.parseInt(st[7]));
		Pin pTwoPOne = new Pin(st[8], Integer.parseInt(st[9]), Integer.parseInt(st[10]));
		Pin pThreePOne = new Pin(st[11], Integer.parseInt(st[12]), Integer.parseInt(st[13]));
		Pin pFourPOne = new Pin(st[14], Integer.parseInt(st[15]), Integer.parseInt(st[16]));
		Player playerOne = new Player(Integer.parseInt(st[4]), pOnePOne, pTwoPOne, pThreePOne, pFourPOne);
		
		Pin pOnePTwo = new Pin(st[18], Integer.parseInt(st[19]), Integer.parseInt(st[20]));
		Pin pTwoPTwo = new Pin(st[21], Integer.parseInt(st[22]), Integer.parseInt(st[23]));
		Pin pThreePTwo = new Pin(st[24], Integer.parseInt(st[25]), Integer.parseInt(st[26]));
		Pin pFourPTwo = new Pin(st[27], Integer.parseInt(st[28]), Integer.parseInt(st[29]));
		Player playerTwo = new Player(Integer.parseInt(st[17]), pOnePTwo, pTwoPTwo, pThreePTwo, pFourPTwo);
		
		Pin pOnePThree = new Pin(st[31], Integer.parseInt(st[32]), Integer.parseInt(st[33]));
		Pin pTwoPThree = new Pin(st[34], Integer.parseInt(st[35]), Integer.parseInt(st[36]));
		Pin pThreePThree = new Pin(st[37], Integer.parseInt(st[38]), Integer.parseInt(st[39]));
		Pin pFourPThree = new Pin(st[40], Integer.parseInt(st[41]), Integer.parseInt(st[42]));
		Player playerThree = new Player(Integer.parseInt(st[30]), pOnePThree, pTwoPThree, pThreePThree, pFourPThree);
		
		Pin pOnePFour = new Pin(st[44], Integer.parseInt(st[45]), Integer.parseInt(st[46]));
		Pin pTwoPFour = new Pin(st[47], Integer.parseInt(st[48]), Integer.parseInt(st[49]));
		Pin pThreePFour = new Pin(st[50], Integer.parseInt(st[51]), Integer.parseInt(st[52]));
		Pin pFourPFour = new Pin(st[53], Integer.parseInt(st[54]), Integer.parseInt(st[55]));
		Player playerFour = new Player(Integer.parseInt(st[43]), pOnePFour, pTwoPFour, pThreePFour, pFourPFour);
		
		
		GameState.turnPlayer = Integer.parseInt(st[0]); 
		GameState.round = Integer.parseInt(st[1]);
		GameState.lastDice = Integer.parseInt(st[2]);
		GameState.countSix = Integer.parseInt(st[3]);
		GameState.players[0] = playerOne;
		GameState.players[1] = playerTwo;
		GameState.players[2] = playerThree;
		GameState.players[3] = playerFour;
		
		ViewMaster.refreshBoard();
	}
	
	static void resetGame() {
		GameState.players[0] = new Player(1,2,2);
		GameState.players[1] = new Player(2,11,2);
		GameState.players[2] = new Player(3,11,11);
		GameState.players[3] = new Player(4,2,11);
		GameState.turnPlayer = 0;
		GameState.lastDice = 0;
		GameState.round = 0;
		GameState.countSix = 0;	
		Movement.firstMove (players[turnPlayer]);
	}
}
