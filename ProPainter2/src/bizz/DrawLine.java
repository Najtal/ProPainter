package bizz;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class DrawLine extends Drawable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DrawPoint pA;
	private DrawPoint pB;
	
	public DrawLine(DrawPoint pA, DrawPoint pB, Color color) {
		super(Drawable.DRAW_SQUARE, false, color);

		this.pA = pA;
		this.pB = pB;
	}

	
	@Override
	public void drawMe(Graphics g) {
		g.setColor(getColor());
		g.drawLine(pA.getX(), pA.getY(), pB.getX(), pB.getY());
	}
		

	@Override
	public boolean validValues() {
		return (pA.getX() != pB.getX() && pA.getY() != pB.getY());
	}


	public DrawPoint getpA() {
		return pA;
	}


	public void setpA(DrawPoint pA) {
		this.pA = pA;
	}


	public DrawPoint getpB() {
		return pB;
	}


	public void setpB(DrawPoint pB) {
		this.pB = pB;
	}


	

	
}
