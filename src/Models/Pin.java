package Models;

public class Pin {
	
	private int pathNum;
	private Path path;
	private int initialPos;
	
	
	public Pin(int pinNum) {
		this.path = Path.Begin;
		this.pathNum = pinNum;
		this.initialPos = this.pathNum; 
	}
	
	public Pin(String path, int pinNum, int initialPos) {
		this.path = Path.valueOf(path);
		this.pathNum = pinNum;
		this.initialPos = initialPos;	
	}
	
	public Path getPathType() {
		return this.path;
	}
	
	public int getPathNum() {
		return this.pathNum;
	}
	
	public void setPath(Path path) {
		this.path= path;
	}
	
	public void setPathNum(int num) {
		this.pathNum= num;
	}	
	
	public boolean isBeginZone() {
		if(this.path == Path.Begin)
			return true;
		return false;
	}
	
	public boolean isEndZone() {
		if(this.path==Path.End)
			return true;
		return false;
	}
	
	public void sendHome() {
		if(this.path == Path.End)
			return;
		this.path=Path.Begin;
		this.pathNum=this.initialPos;
	}
	
	public int getInitialPos() {
		return this.initialPos;
	}
}
