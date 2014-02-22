package bizz;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class DrawCircle extends Drawable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DrawPoint pA;
	private DrawPoint pB;
	
	public DrawCircle(DrawPoint pA, DrawPoint pB, boolean filled, Color color) {
		super(Drawable.DRAW_SQUARE, filled, color);

		this.pA = pA;
		this.pB = pB;
	}
	

	@Override
	public boolean validValues() {
		return ((pA.getX()-pB.getX()) != 0 && (pA.getY()-pB.getY()) != 0);
	}



	@Override
	public void drawMe(Graphics g) {
		g.setColor(getColor());
		
		if (isFilled()) {
			g.fillOval(getTopLeftCorner().getX(), getTopLeftCorner().getY(), getWidth(), getHeight());
		} else {
			g.drawOval(getTopLeftCorner().getX(), getTopLeftCorner().getY(), getWidth(), getHeight());
		}
		
	}

	
	
	private DrawPoint getTopLeftCorner() {
		int x = pB.getX();
		int y = pB.getY();
		if (pA.getX() < pB.getX()) {
			x = pA.getX();
		}
		if (pA.getY() < pB.getY()) {
			y = pA.getY();
		}
		return new DrawPoint(x, y);
	}
	
	private int getHeight() {
		if (pA.getY() < pB.getY()) {
			return pB.getY() - pA.getY();
		}
		return pA.getY() - pB.getY();
	}
	
	private int getWidth() {
		if (pA.getX() < pB.getX()) {
			return pB.getX() - pA.getX();
		}
		return pA.getX() - pB.getX();
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