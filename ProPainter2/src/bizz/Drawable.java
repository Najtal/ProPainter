package bizz;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


public abstract class Drawable implements Serializable {

	private static final long serialVersionUID = 1L;

	private int type;
	private boolean filled;
	private Color color;
	private boolean visible;
	
	public static final String[] tabToolNames = {"Line", "Triangle", "Square", "Square fill", "Cricle", "Circle fill"};
	
	public static final int DRAW_LINE = 0;
	public static final int DRAW_TRIANGLE = 1;
	public static final int DRAW_SQUARE = 2;
	public static final int DRAW_SQUARE_FILL = 3;
	public static final int DRAW_CIRCLE = 4;
	public static final int DRAW_CIRCLE_FILL = 5;
	
	public Drawable(int type, boolean filled, Color color) {
		this.type = type;
		this.filled = filled;
		this.color = color;
		this.visible = true;
	}


	public abstract boolean validValues();
	
	public abstract void drawMe(Graphics g);


	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	

	public boolean isFilled() {
		return filled;
	}


	public void setFilled(boolean filled) {
		this.filled = filled;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public int getType() {
		return type;
	}
	
	private int getMax(int x, int y) {
		if (x > y)
			return x;
		return y;
	}
	
	private int min (int x, int y) {
		if (x <= y)
			return x;
		return y;
	}


	public void changeVisibility() {
		visible = !visible;
	}
	
}
