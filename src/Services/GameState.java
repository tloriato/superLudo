package Services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Pin;
import Models.Player;
import View.ViewMaster;

public class GameState {
	private static Player[] players= new Player[4];
	private static int turnPlayer;
	private static int round;
	private static int lastDice;
	private static int countSix;
	
	public static void initializeGame() {
		players[0] = new Player(1,2,2);
		players[1] = new Player(2,11,2);
		players[2] = new Player(3,11,11);
		players[3] = new Player(4,2,11);
		turnPlayer = 0;
		lastDice = 0;
		round = 0;
		countSix = 0;
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
		System.out.println(lastDice);
	}
	
	public static int getDice() {
		return lastDice;
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
		countSix =0;
	}
	
	public static void firstTurn() {
		Movement.firstMove (players[turnPlayer]);
	}
	
	public static int getCountSix() {
		return countSix;
	}
	
	public static void addCountSix() {
		countSix++;
	}
	
	public static void zeroCountSix() {
		countSix = 0;
	}
	
	// Modelo fullState(quebras de linhas somente para visualiza��o):
	// TP,RN,LD,CS
	// N,XY,XY,XY,XY
	// N,XY,XY,XY,XY
	// N,XY,XY,XY,XY
	// N,XY,XY,XY,XY
	// A primeira linha cuida das vari�veis globais de estado do jogo como descritos na classe
	// N � o numero de cada player, e XY as coordenadas de cada pe�o dos jogadores
	
	// retorna uma String no modelo fullState
	public static String getFullState() {
		List<String> state = new ArrayList<>();
		state.add(Integer.toString(turnPlayer));
		state.add(Integer.toString(round));
		state.add(Integer.toString(lastDice));
		state.add(Integer.toString(countSix));
		for (Player player: players) {
			state.add(Integer.toString(player.getNumber()));
			for (Pin pin: player.getPins()) {
				//state.add(Integer.toString(pin.getPosX()));
				//state.add(Integer.toString(pin.getPosY()));
			}
		}
		return String.join(",", state);
	}

	public static void loadState(String fullLoadedState) {
		String[] st = fullLoadedState.split(",");
		Player playerOne = new Player(st[4], st[5], st[6], st[7], st[8], st[9], st[10], st[11], st[12]);
		Player playerTwo = new Player(st[13], st[14], st[15], st[16], st[17], st[18], st[19], st[20], st[21]);
		Player playerThree = new Player(st[22], st[23], st[24], st[25], st[26], st[27], st[28], st[29], st[30]);
		Player playerFour = new Player(st[31], st[32], st[33], st[34], st[35], st[36], st[37], st[38], st[39]);
		
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
	
}
