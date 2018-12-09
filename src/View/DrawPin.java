package View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Models.Path;
import Models.Pin;
import Models.Player;
import Services.Barrier;
import Services.ServiceFacade;

public abstract class DrawPin {
	
	private static Pin pins[] = new Pin[16];
	private static int players[] = new int[16];
	private static int positions[][] = new int[16][2];
	private static int shelter[][] = new int[4][2];
	private static int exit[][] = new int[4][2];
	
	public static void drawPins(Graphics2D g2d, int squareSize) {
		
		int count=0; 
		int pos[];
		for (Player pl : ServiceFacade.getPlayers() ) {
			for(Pin pi : pl.getPins()){
				pos=DrawPin.drawPin(g2d,pi,squareSize,pl.getNumber(),1);
				pins[count] = pi;
				positions[count]=pos;
				players[count]=pl.getNumber();
				count++;
			}
		}
		
		for(int i=0;i<16;i++) {
			for(int j=i+1;j<16;j++) {
				if(positions[i][0]==positions[j][0] && positions[i][1]==positions[j][1] && players[i]!=players[j]) {
					DrawPin.drawPin(g2d,pins[i],squareSize,players[i],1);
					DrawPin.drawPin(g2d,pins[j],squareSize,players[j],2.0/3);
				}
			}
		}
		
		ArrayList<Barrier> b =Barrier.getBarriers();
		//System.out.println(b);
		//System.out.println(b.size());
		for(int i=0;i<b.size();i++) {
			drawBarrier(g2d, b.get(i),squareSize);
		}
		
		
	}
	
	private static void drawBarrier(Graphics2D g2d,Barrier b ,int squareSize){
		Color color = getColor(b.getPlayer().getNumber());
		Path path = b.getPath();

		int pos[] = getPos(path, b.getPlayer().getNumber(), b.getPos());
		
		double centerX = (pos[0]+0.5)*squareSize;
		double centerY = (pos[1]+0.5)*squareSize;
		double pinRadius = squareSize*0.3;
	

		Ellipse2D circ= new Ellipse2D.Double();
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius,centerY+pinRadius);
		
		g2d.setPaint(Color.WHITE);
		g2d.fill(circ);
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius*0.8,centerY+pinRadius*0.8);
		g2d.setPaint(color);
		g2d.fill(circ);
		
		
	}
	private static int[] drawPin(Graphics2D g2d, Pin p,int squareSize,int playerNumber, double factor) {
			
		Color color = getColor(playerNumber);
		Path path = p.getPathType();
	
		int pos[] = getPos(path, playerNumber, p.getPathNum());
		
		double centerX = (pos[0]+0.5)*squareSize;
		double centerY = (pos[1]+0.5)*squareSize;
		double pinRadius = squareSize*0.45*factor;
	

		Ellipse2D circ= new Ellipse2D.Double();
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius,centerY+pinRadius);
		
		g2d.setPaint(Color.WHITE);
		g2d.fill(circ);
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius*0.8,centerY+pinRadius*0.8);
		g2d.setPaint(color);
		g2d.fill(circ);
		
		return pos;
		
	}
	
	static void findPin(int posX, int posY) {
		for(int i=0;i<16;i++) {
			if(posX==positions[i][0] && posY==positions[i][1] ) {
				//System.out.println(pins[i]);
				ServiceFacade.select(pins[i]);
				if(players[i]==ServiceFacade.getTurnPlayer().getNumber())
					break;
			}
		}
		
	}
	
	private static Color getColor(int playerNumber) {
		Color color;
		if(playerNumber == 1) 
			color = Color.GREEN;
		else if(playerNumber == 2)
			color = Color.YELLOW;
		else if(playerNumber == 3) 
			color = Color.BLUE;
		else 
			color = Color.RED;
		return color;
	}
	
	private static int[] getPos(Path path, int playerNum, int pathPos) {
		int pos[] = new int[2];
		
		if(path == Path.End) {
			if(playerNum==1) {
				pos[0] = 6;
				pos[1] = 7;
			}
			else if(playerNum==2) {
				pos[0] = 7;
				pos[1] = 6;
			}
			else if(playerNum==3) {
				pos[0] = 8;
				pos[1] = 7;
			}
			else {
				pos[0] = 7;
				pos[1] = 8;
			}
		}
		
		else if(path == Path.LastRoad) {
			if(playerNum==1) {
				pos[0] = 1+pathPos;
				pos[1] = 7;
			}
			else if(playerNum==2) {
				pos[0] = 7;
				pos[1] = 1+pathPos;
			}
			else if(playerNum==3) {
				pos[0] = 13-pathPos;
				pos[1] = 7;
			}
			else {
				pos[0] = 7;
				pos[1] = 13-pathPos;
			}
				
		}
		else if(path == Path.Begin) {
			if(playerNum==1) {
				switch (pathPos) {
				case 0:
					pos[0]=2;
					pos[1]=2;
					break;
				case 1:
					pos[0]=2;
					pos[1]=3;
					break;
				case 2:
					pos[0]=3;
					pos[1]=3;
					break;
				case 3:
					pos[0]=3;
					pos[1]=2;
					break;
				}
			}
			if(playerNum==2) {
				switch (pathPos) {
				case 0:
					pos[0]=11;
					pos[1]=2;
					break;
				case 1:
					pos[0]=11;
					pos[1]=3;
					break;
				case 2:
					pos[0]=12;
					pos[1]=3;
					break;
				case 3:
					pos[0]=12;
					pos[1]=2;
					break;
				}
			}
			if(playerNum==3) {
				switch (pathPos) {
				case 0:
					pos[0]=11;
					pos[1]=11;
					break;
				case 1:
					pos[0]=11;
					pos[1]=12;
					break;
				case 2:
					pos[0]=12;
					pos[1]=12;
					break;
				case 3:
					pos[0]=12;
					pos[1]=11;
					break;
				}
			}
			if(playerNum==4) {
				switch (pathPos) {
				case 0:
					pos[0]=2;
					pos[1]=11;
					break;
				case 1:
					pos[0]=2;
					pos[1]=12;
					break;
				case 2:
					pos[0]=3;
					pos[1]=12;
					break;
				case 3:
					pos[0]=3;
					pos[1]=11;
					break;
				}
			}
			
		}
		else { //Path = Common
			switch (pathPos) {
			case 0:
				pos[0]=1;
				pos[1]=6;
				break;
			case 1:
				pos[0]=2;
				pos[1]=6;
				break;
			case 2:
				pos[0]=3;
				pos[1]=6;
				break;
			case 3:
				pos[0]=4;
				pos[1]=6;
				break;
			case 4:
				pos[0]=5;
				pos[1]=6;
				break;
			case 5:
				pos[0]=6;
				pos[1]=5;
				break;
			case 6:
				pos[0]=6;
				pos[1]=4;
				break;
			case 7:
				pos[0]=6;
				pos[1]=3;
				break;
			case 8:
				pos[0]=6;
				pos[1]=2;
				break;
			case 9:
				pos[0]=6;
				pos[1]=1;
				break;
			case 10:
				pos[0]=6;
				pos[1]=0;
				break;
			case 11:
				pos[0]=7;
				pos[1]=0;
				break;
			case 12:
				pos[0]=8;
				pos[1]=0;
				break;
			case 13:
				pos[0]=8;
				pos[1]=1;
				break;
			case 14:
				pos[0]=8;
				pos[1]=2;
				break;
			case 15:
				pos[0]=8;
				pos[1]=3;
				break;
			case 16:
				pos[0]=8;
				pos[1]=4;
				break;
			case 17:
				pos[0]=8;
				pos[1]=5;
				break;
			case 18:
				pos[0]=9;
				pos[1]=6;
				break;
			case 19:
				pos[0]=10;
				pos[1]=6;
				break;
			case 20:
				pos[0]=11;
				pos[1]=6;
				break;
			case 21:
				pos[0]=12;
				pos[1]=6;
				break;
			case 22:
				pos[0]=13;
				pos[1]=6;
				break;
			case 23:
				pos[0]=14;
				pos[1]=6;
				break;
			case 24:
				pos[0]=14;
				pos[1]=7;
				break;
			case 25:
				pos[0]=14;
				pos[1]=8;
				break;
			case 26:
				pos[0]=13;
				pos[1]=8;
				break;
			case 27:
				pos[0]=12;
				pos[1]=8;
				break;
			case 28:
				pos[0]=11;
				pos[1]=8;
				break;
			case 29:
				pos[0]=10;
				pos[1]=8;
				break;
			case 30:
				pos[0]=9;
				pos[1]=8;
				break;
			case 31:
				pos[0]=8;
				pos[1]=9;
				break;
			case 32:
				pos[0]=8;
				pos[1]=10;
				break;
			case 33:
				pos[0]=8;
				pos[1]=11;
				break;
			case 34:
				pos[0]=8;
				pos[1]=12;
				break;
			case 35:
				pos[0]=8;
				pos[1]=13;
				break;
			case 36:
				pos[0]=8;
				pos[1]=14;
				break;
			case 37:
				pos[0]=7;
				pos[1]=14;
				break;
			case 38:
				pos[0]=6;
				pos[1]=14;
				break;
			case 39:
				pos[0]=6;
				pos[1]=13;
				break;
			case 40:
				pos[0]=6;
				pos[1]=12;
				break;
			case 41:
				pos[0]=6;
				pos[1]=11;
				break;
			case 42:
				pos[0]=6;
				pos[1]=10;
				break;
			case 43:
				pos[0]=6;
				pos[1]=9;
				break;
			case 44:
				pos[0]=5;
				pos[1]=8;
				break;
			case 45:
				pos[0]=4;
				pos[1]=8;
				break;
			case 46:
				pos[0]=3;
				pos[1]=8;
				break;
			case 47:
				pos[0]=2;
				pos[1]=8;
				break;
			case 48:
				pos[0]=1;
				pos[1]=8;
				break;
			case 49:
				pos[0]=0;
				pos[1]=8;
				break;
			case 50:
				pos[0]=0;
				pos[1]=7;
				break;
			case 51:
				pos[0]=0;
				pos[1]=6;
				break;
			}
			
		}
		return pos;
	}
	
	
}
