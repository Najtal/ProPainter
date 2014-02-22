package bizz;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class DrawTriangle extends Drawable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DrawPoint pA;
	private DrawPoint pB;
	
	private DrawLine dlGauche;
	private DrawLine dlDroite;
	private DrawLine dlBas;
	
	public DrawTriangle(DrawPoint pA, DrawPoint pB, Color color) {
		super(Drawable.DRAW_SQUARE, false, color);

		this.pA = pA;
		this.pB = pB;
	}

	@Override
	public void drawMe(Graphics g) {
	
		generateLines();
		
		dlGauche.drawMe(g);
		dlDroite.drawMe(g);
		dlBas.drawMe(g);
	}

	private void generateLines() {
		dlBas = new DrawLine(pA, pB, getColor());
		DrawPoint pC = new DrawPoint(getHeight()+pA.getX(), getWidth()+pB.getY());
		dlGauche = new DrawLine(pA, pC, getColor());
		dlDroite = new DrawLine(pB, pC, getColor());
		
		/*
		DrawPoint pMilieuLigne = new DrawPoint((int) Math.floor(getWidth()/2), (int) Math.floor(getHeight()/2));
		int longueurLigne = */
		
	}

	@Override
	public boolean validValues() {
		return (pA.getX() != pB.getY() && pA.getY() != pB.getY());
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
	

}