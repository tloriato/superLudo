package Services;

import java.util.ArrayList;

import Models.Path;
import Models.Pin;
import Models.Player;

public class Barrier {
	
	private static ArrayList<Barrier> barriers= new ArrayList();
	private Pin pin1;
	private Pin pin2;
	private Player player;
	
	private Barrier(Pin p1, Pin p2, Player player) {
		this.pin1 =p1;
		this.pin2 =p2;
		this.player=player;
		barriers.add(this);
	}
	
	static void checkForNewBarrier(Pin p) {
		
		if(p.getPathType() == Path.Common && (Movement.isShelter(p.getPathNum()) || Movement.isExit(p.getPathNum()) ))
			return;
		if(p.isEndZone() || p.isBeginZone())
			return;
		Player player = GameState.getTurnPlayer();
		for ( Pin pin : player.getPins()) {
			if(pin != p && pin.getPathNum() == p.getPathNum() && p.getPathType() == pin.getPathType()) {
				new Barrier(p,pin,player);
				break; 
			}
		}
		
	}
	
	static void leaveBarrier(Pin p) {
		ArrayList<Barrier> b =Barrier.getBarriers();
		for(int j=0;j<b.size();j++)  {
			if(p.getPathNum() == b.get(j).getPos() && p.getPathType()==b.get(j).getPath()) {
				barriers.remove(barriers.indexOf(b));
				return;
			}
				
		}
		
	}
	
	public static boolean barrierOnTheWay(int pos, int dice, Player player, Path path) {
		
		ArrayList<Barrier> b =Barrier.getBarriers();
		for(int i=1;i<dice;i++) {
			for( int j=0;j<b.size();j++) {
				if(pos == b.get(j).getPos() && path==b.get(j).getPath() && player!=b.get(j).getPlayer())
					return true;
			}
			
			pos++;
			if(pos==52)
				pos=0;
			if(pos == Movement.lastRoadEntrace(player.getNumber())+1) {
				path = Path.LastRoad;
				pos = 0 ;
			}
		}
		
		
		for( int j=0;j<b.size();j++) {
			if(pos == b.get(j).getPos() && path==b.get(j).getPath() && player!=b.get(j).getPlayer())
				return true;
		}
		
		return false;
	}
	
	
	public int getPos() {
		return this.pin1.getPathNum();
	}
	
	public  Path getPath(){
		return this.pin1.getPathType();
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public static ArrayList<Barrier> getBarriers() {
		return barriers;
	}
	
	public static ArrayList<Pin> firstBarrier() {
		Player player = GameState.getTurnPlayer();
		ArrayList<Pin> pins = new ArrayList();
		ArrayList<Barrier> b =Barrier.getBarriers();
		for( int j=0;j<b.size();j++) {
			if(player == b.get(j).getPlayer()) {
				pins.add(b.get(j).pin1);
			}
		}
		
		return pins;
	}

	
}
