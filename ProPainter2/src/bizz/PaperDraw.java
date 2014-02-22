package bizz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PaperDraw implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private String fileName;
	private GregorianCalendar createDate;
	private int selectedLayer;

	private ArrayList<PaperLayer> layerList;


	public PaperDraw(String fileName, int width, int height) {

		this.fileName = fileName;
		this.createDate = new GregorianCalendar();

		this.width = width;
		this.height = height;

		layerList = new ArrayList<PaperLayer>();
		layerList.add(new PaperLayer("main layer"));

	}

	@Override
	public PaperDraw clone() throws CloneNotSupportedException {

		//PaperDraw pd = new PaperDraw(fileName, width, height);
		PaperDraw pd = (PaperDraw) super.clone();
		
		if (layerList.size() > 0) {
			pd.layerList = new ArrayList<PaperLayer>();
			
			for (int i = 0; i < this.layerList.size(); i++) {
				pd.layerList.add(i, this.layerList.get(i).clone());
			}
		}

		return pd;
	}
	
	
	
	public PaperLayer getLayer(int i) {
		if (i < layerList.size() && i >= 0)
			return layerList.get(i);
		return null;
	}
	
	public void addNewLayer(String name) {
		layerList.add(new PaperLayer(name));
	}
	
	public void removeFromArray(int i) {
				
		ArrayList<PaperLayer> newl = new ArrayList<PaperLayer>(layerList.size()-1);
		for (int j = 0; j < layerList.size(); j++) {
			if (j != i) {
				newl.add(layerList.get(j));
			}
		}
		layerList = newl;
	}

	


	/*
	 * GETTERS AND SETTERS
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GregorianCalendar getCreateDate() {
		return createDate;
	}

	public ArrayList<PaperLayer> getLayerList() {
		return layerList;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSelectedLayer() {
		return selectedLayer;
	}
	
	public void setSelectedLayer(int i) {
		selectedLayer = i;
	}
	
}
