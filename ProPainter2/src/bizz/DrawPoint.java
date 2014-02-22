package bizz;

import java.io.Serializable;

public class DrawPoint implements Serializable {

	private int x;
	private int y;
	
	public DrawPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
