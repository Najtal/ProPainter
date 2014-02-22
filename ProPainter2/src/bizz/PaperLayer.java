package bizz;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * This class manage all the drawables from a same layer
 * 
 * @author jvdur_000
 *
 */
public class PaperLayer implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Drawable> drawList;
	private String name;
	private boolean visible;

	
	/**
	 * Constructor
	 * @param model
	 */
	public PaperLayer(String name) {
		
		this.drawList = new ArrayList<Drawable>();
		this.name = name;
		this.visible = true;
	}
	
	

	public void addDrawable(Drawable d) {
		drawList.add(d);
	}
	

	@Override
	public PaperLayer clone() throws CloneNotSupportedException {
		
		//PaperLayer pl = new PaperLayer(name);
		PaperLayer pl = (PaperLayer) super.clone();
		
		pl.drawList = new ArrayList<Drawable>();
		for (int i = 0; i < drawList.size(); i++) {
			pl.drawList.add(i, drawList.get(i));
		}
		
		return pl;
	}
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	public ArrayList<Drawable> getDrawList() {
		return drawList;
	}
	
	public Drawable getDrawable (int i) {
		if (i < drawList.size() && i >= 0)
			return drawList.get(i);
		return null;
	}

	public void removeFromArray(int i) {
		ArrayList<Drawable> newl = new ArrayList<Drawable>(drawList.size()-1);
		for (int j = 0; j < drawList.size(); j++) {
			if (j != i) {
				newl.add(drawList.get(j));
			}
		}
		drawList = newl;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isVivible() {
		return visible;
	}


	public void setVivible(boolean vivible) {
		this.visible = vivible;
	}



	public void changeVisibility() {
		visible = !visible;
	}
}
