import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Tabuleiro extends JPanel {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int boardSize;
	private double squareSize;
	private double pinRadius;
	public ArrayList<Pino> pinos = new ArrayList();
	//private int squareNumbers=15;
	
	public Tabuleiro(int size) {
		this.boardSize = size;
		this.squareSize=(1.0*this.boardSize)/15;
		this.pinRadius = squareSize*0.4;
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		//g.drawString("Primeiro Programa Gráfico",TXT_X,TXT_Y);
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		Rectangle2D[][] board=new Rectangle2D[15][15];
		
		int size = (int) this.squareSize;
		
		makeSquare(9*size,0,6*size, g2d, Color.YELLOW);
		makeSquare(0,9*size,6*size, g2d, Color.RED);
		makeSquare(0,0,6*size, g2d, Color.GREEN);
		makeSquare(9*size,9*size,6*size, g2d, Color.BLUE);
		
		makePath(6,9,0,6,board,g2d,Color.YELLOW);
		makePath(6,9,9,15,board,g2d,Color.RED);
		makePath(0,6,6,9,board,g2d,Color.GREEN);
		makePath(9,15,6,9,board,g2d,Color.BLUE);
		
		makeTriangle(size*6,size*6,15*size/2,15*size/2,size*9,size*6,g2d,Color.YELLOW );
		makeTriangle(size*6,size*9,15*size/2,15*size/2,size*9,size*9,g2d,Color.RED );
		makeTriangle(size*6,size*6,15*size/2,15*size/2,size*6,size*9,g2d,Color.GREEN );
		makeTriangle(size*9,size*6,15*size/2,15*size/2,size*9,size*9,g2d,Color.BLUE);
		
		makeSquare(10*size,1*size,4*size , g2d, Color.YELLOW);
		makeSquare(1*size,10*size,4*size , g2d, Color.RED);
		makeSquare(1*size,1*size,4*size  , g2d, Color.GREEN);
		makeSquare(10*size,10*size,4*size, g2d, Color.BLUE);
		
		//makeCenter(11,2,size, g2d , Color.WHITE);
		//makeCenter(2,11,size, g2d , Color.WHITE);
		//makeCenter(2,2,size, g2d , Color.WHITE);
		//makeCenter(11,11,size, g2d , Color.WHITE);
		
		for (Pino p : pinos) {
			drawPin(p,g2d);
		}
		
	}
	
	private void makePath(int initX,int endX,int initY,int endY, Rectangle2D[][] board, Graphics2D g2d, Color color) {
		double leftX=squareSize*initX;
		double topY=squareSize*initY;
		for(int i=initY;i<endY;i++) {
			for(int j=initX;j<endX;j++) {
				board[i][j]=new Rectangle2D.Double(leftX,topY,squareSize,squareSize);
				if(initX == 6) {
					if(j==7 && i!=0 && i!=14 || j==8 && i==1 || j==6 && i==13) {
						g2d.setPaint(color);
						g2d.fill(board[i][j]);
					}
				}
				else {
					if(i==7 && j!=0 && j!=14 || i==6 && j==1 || i==8 && j==13) {
						g2d.setPaint(color);
						g2d.fill(board[i][j]);
					}
				}
				g2d.setPaint(Color.BLACK);
				g2d.draw(board[i][j]);
				leftX = leftX +squareSize;
			}
			topY = topY +squareSize;
			leftX = squareSize*initX;
		}
	}
	
	private void makeTriangle(int x0,int y0,int x1,int y1,int x2,int y2, Graphics2D g2d, Color color) {
		
		int polygonX[] = new int[3];
		polygonX[0]=x0; polygonX[1]=x1; polygonX[2]=x2;
		int polygonY[] = new int[3];
		polygonY[0]=y0; polygonY[1]=x1; polygonY[2]=y2;
		 
		Polygon p = new Polygon(polygonX, polygonY, 3);
		
		g2d.setPaint(color);
		g2d.fill(p);
		
		g2d.setPaint(Color.BLACK);
		g2d.draw(p);
		
	}
	
	private void makeSquare(double x0, double y0, double size,Graphics2D g2d, Color color ) {
		Rectangle2D r = new Rectangle2D.Double(x0,y0,size,size);
		
		g2d.setPaint(color);
		g2d.fill(r);	
	}
	
	private void makeCenter(int x,int y, int size,Graphics2D g2d,Color color ) {
		makeSquare(x*size,y*size,size, g2d, color);
		makeSquare((x+1)*size,y*size,size, g2d, color);
		makeSquare((x+1)*size,(y+1)*size,size, g2d, color);
		makeSquare(x*size,(y+1)*size,size, g2d, color);
	}
	
	public void addPin(Pino p) {
		this.pinos.add(p);
	}
	
	private void drawPin(Pino p, Graphics2D g2d) {
		double centerX = (p.posX+0.5)*this.squareSize;
		double centerY = (p.posY+0.5)*this.squareSize;
		Ellipse2D circ= new Ellipse2D.Double();
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius,centerY+this.pinRadius);
		
		g2d.setPaint(Color.WHITE);
		g2d.fill(circ);
		circ.setFrameFromCenter(centerX ,centerY,centerX+pinRadius*0.8,centerY+this.pinRadius*0.8);
		g2d.setPaint(p.color);
		g2d.fill(circ);
	}
	
}
