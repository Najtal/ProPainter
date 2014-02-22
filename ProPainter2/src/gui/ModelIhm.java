package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.jws.Oneway;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ucc.ExportJPG;
import ucc.LoadDrawing;
import ucc.SaveDrawing;
import bizz.Drawable;
import bizz.PaperDraw;

public class ModelIhm {

	private static ModelIhm instance = null;

	// The JFRAME
	private MainPPFrame frame;

	// THE REGISTRED JPANELS TO UPDATE
	private final ArrayList<ChangeListener> listeVues;

	/*
	 * ColorManager
	 */
	private Color selectedColor;
	private ArrayList<Color> tabPreferedColors;

	/*
	 * Toolbar
	 */
	private int idToolSelected = -1;
	private boolean isTReduce = false;


	/*
	 * PAPER
	 */
	private boolean paperOnBoard = false;
	private PaperDraw paper;
	private ElmtPaper board;
	
	/*
	 * LAYERS
	 */
	//private int selectedLayer;
	private boolean isLReduce = false;
	

	
	/*
	 * REDO and UNDO
	 */
	private Stack<PaperDraw> stackUndo;
	private Stack<PaperDraw> stackRedo;

	
	
	/**
	 * Constructor
	 * 
	 * @param mainPPFrame
	 */
	private ModelIhm(MainPPFrame mainPPFrame) {

		this.frame = mainPPFrame;
		this.listeVues = new ArrayList<ChangeListener>();

		this.stackUndo = new Stack<PaperDraw>();
		this.stackRedo = new Stack<PaperDraw>();
		
		/*
		 * ColorManager
		 */
		this.tabPreferedColors = new ArrayList<Color>(10);

		// On initialise la table avec des couleurs de base
		this.tabPreferedColors.add(0, new Color(255, 255, 255));
		this.tabPreferedColors.add(1, new Color(0, 102, 102));
		this.tabPreferedColors.add(2, new Color(50, 50, 255));
		this.tabPreferedColors.add(3, new Color(0, 255, 255));
		this.tabPreferedColors.add(4, new Color(255, 200, 0));
		this.tabPreferedColors.add(5, new Color(50, 200, 0));
		this.tabPreferedColors.add(6, new Color(255, 50, 0));
		this.tabPreferedColors.add(7, new Color(255, 255, 100));
		this.tabPreferedColors.add(8, new Color(10, 10, 50));
		this.tabPreferedColors.add(9, new Color(200, 10, 150));

		this.selectedColor = new Color(102, 153, 255);

		traiterEvent(new ChangeEvent(this));

	}

	/**
	 * To get THE SSO instance of the model
	 * 
	 * @param mainPPFrame
	 * @return the sso
	 */
	public static ModelIhm getInstance(MainPPFrame mainPPFrame) {
		if (instance == null) {
			instance = new ModelIhm(mainPPFrame);
		}

		return instance;
	}

	/**
	 * Enregistre un listener
	 * 
	 * @param chl
	 *            Le listener à enregistrer
	 */
	public final synchronized void addChangeListener(final ChangeListener chl) {
		if (!listeVues.contains(chl)) {
			listeVues.add(chl);
		}
	}

	/**
	 * Notifie les listeners
	 * 
	 * @param e L'événement à notifier aux listeners
	 */
	protected final synchronized void traiterEvent(final ChangeEvent e) {
		for (final ChangeListener listener : listeVues) {
			listener.stateChanged(e);
		}
		frame.revalidate();
	}

	
	
	/*
	 * JMenuBar actions
	 */

	/**
	 * Open new file
	 */
	public void actionNew() {

		if (paperOnBoard) {
			int reponse = JOptionPane.showConfirmDialog(frame,
					"Do you want to save your Drawing ?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (reponse == JOptionPane.YES_OPTION) {
				actionSave();
			}
			new jopNewProject(this);
		} else {
			new jopNewProject(this);
		}
		
		traiterEvent(new ChangeEvent(this));
		stackRedo.empty();
		stackUndo.empty();
	}

	/**
	 * Open existing file
	 */
	public void actionOpen() {

		if (paperOnBoard) {
			int reponse = JOptionPane.showConfirmDialog(frame,
					"Do you want to save your Drawing ?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (reponse == JOptionPane.YES_OPTION) {
				actionSave();
			}
		}
		
		try {
			new LoadDrawing(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void openOkAndSetVal() {
		this.paperOnBoard = true;
		board.setPaper(this.paper);

		frame.resizePaperBorder();
		frame.getLayers().stateChanged(new ChangeEvent(this));
		
		stackRedo.empty();
		stackUndo.empty();		
	}

	/**
	 * Save file
	 */
	public void actionSave() {
		if (paperOnBoard) {
			new SaveDrawing(this);
		} else {
			JOptionPane.showMessageDialog(frame, "You have to create a project first",
					"Error", JOptionPane.DEFAULT_OPTION,
					AbstractVue.instance.iSave);
		}
	}

	/**
	 * Exit program
	 */
	public void actionExit() {
		if (paperOnBoard) {
			int reponse = JOptionPane.showConfirmDialog(frame,
					"Do you want to save your Drawing ?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (reponse == JOptionPane.YES_OPTION) {
				actionSave();
			} else {
				System.exit(0);
			}

		} else {

			int reponse = JOptionPane.showConfirmDialog(frame,
					"Do you really want to quit ?", "Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (reponse == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

	}
	
	/**
	 * Export to JPG
	 */
	public void actionExport() {
		if (paperOnBoard) {
			new ExportJPG(this);
		} else {
			JOptionPane.showMessageDialog(frame, "You have to create a project first",
					"Error", JOptionPane.DEFAULT_OPTION,
					AbstractVue.instance.iExport);
		}
	}

	/**
	 * Undo action
	 */
	public void actionReduceToolPanel() {
		if (!isTReduce) {
			frame.reduceToolLayer();
		} else {
			frame.openToolLayer();
		}
		isTReduce = !isTReduce;
	}

	/**
	 * Redo action
	 */
	public void actionReduceLayerPanel() {

		if (!isLReduce) {
			frame.reduceLayerLayer();
		} else {
			frame.openLayerLayer();
		}
		isLReduce = !isLReduce;
		
	}
	
	
	public void actionProjectProperties() {
		if (paperOnBoard) {
			new jopProjectProperties(this);
		} else {
			JOptionPane.showMessageDialog(frame, "You have to create a project first",
					"Error", JOptionPane.DEFAULT_OPTION,
					AbstractVue.instance.iProject);
		}
		
	}
	
	/**
	 * Get the about Hessage
	 */
	public void actionAbout() {

		JOptionPane.showMessageDialog(frame, AbstractVue.ABOUT_MSG,
				"About Pro Painter", JOptionPane.DEFAULT_OPTION,
				AbstractVue.instance.iAbout);
		
	}
	


	public void actionUndo() {

		if (paperOnBoard) {
			try {
				stackRedo.push((PaperDraw) paper.clone());
			} catch (CloneNotSupportedException e) {
			}
			
			if (!stackUndo.empty()) {
				this.paper = stackUndo.pop();
					
				//frame.getElemPaper().stateChanged(new ChangeEvent(this));
				frame.getLayers().stateChanged(new ChangeEvent(this));
			}
			
			traiterEvent(new ChangeEvent(this));
		}
	}

	public void actionRedo() {
		
		if (paperOnBoard) {
			try {
				stackUndo.push((PaperDraw) paper.clone());
			} catch (CloneNotSupportedException e) {
	
			}
			
			if (!stackRedo.empty())
				setPapet(stackRedo.pop());
	
			traiterEvent(new ChangeEvent(this));
		}
	}

	
	
	
	
	

	public BufferedImage getImageFromPaper() {
		if (paperOnBoard) {
			return this.frame.getElemPaper().getMeAnImage();
		}
		return null;
	}
	

	/**
	 * inverse les couleurs dans le color manager
	 * 
	 * @param indiceTableau
	 */
	public void actionChangeCouleurSelectionnee(int indiceTableau) {

		Color tmp = tabPreferedColors.get(indiceTableau);
		tabPreferedColors.set(indiceTableau, selectedColor);
		selectedColor = tmp;

		traiterEvent(new ChangeEvent(this));
	}

	/**
	 * When a new color is picked throught the color chooser
	 * 
	 * @param newSelectedColor
	 */
	public void actionNewSelectedColor(Color newSelectedColor) {
		this.selectedColor = newSelectedColor;
		traiterEvent(new ChangeEvent(this));
	}

	public void setElmtPaper(ElmtPaper elmtPaper) {
		this.board = elmtPaper;
	}

	/*
	 * Draw functions
	 */
	public void addDrawableToCurrentLayer(Drawable d) {
		if (paperOnBoard) {
		
			try {
				stackUndo.push((PaperDraw) paper.clone());
				stackRedo.clear();
			} catch (CloneNotSupportedException e) {
				stackUndo.clear();
				stackRedo.clear();
			}
			
			paper.getLayerList().get(paper.getSelectedLayer()).addDrawable(d);
			
			traiterEvent(new ChangeEvent(this));
		}
	}
	
	
	public void addNewLayer(String name) {

		if (paperOnBoard) {
			
			try {
				stackUndo.push((PaperDraw) paper.clone());
				stackRedo.clear();
			} catch (CloneNotSupportedException e) {
				stackUndo.clear();
				stackRedo.clear();
			}
			
			
			paper.addNewLayer(name);		
			
			traiterEvent(new ChangeEvent(this));
		}
		
	}

	/*
	 * GETTERS AND SETTERS
	 */
	public String getFileName() {
		if (paperOnBoard)
			return paper.getFileName();
		return null;
	}

	public MainPPFrame getFrame() {
		return frame;
	}
	
	public boolean isPaperOnBoard() {
		return paperOnBoard;
	}

	public PaperDraw getPaper() {
		return paper;
	}

	public void setPapet(PaperDraw paper) {
		this.paper = paper;
	}

	public ElmtPaper getBoard() {
		return board;
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public ArrayList<Color> getTabPreferedColors() {
		return tabPreferedColors;
	}

	public int getIdToolSelected() {
		return idToolSelected;
	}

	public void setIdToolSelected(int idToolSelected) {
		this.idToolSelected = idToolSelected;
	}

	public int getSelectedLayer() {
		return paper.getSelectedLayer();
	}

	public void setSelectedLayer(int selectedLayer) {
		if (selectedLayer > paper.getLayerList().size()-1)
			paper.setSelectedLayer(0);
		else
			paper.setSelectedLayer(selectedLayer);
		
		
		frame.getLayers().stateChanged(null);
		//traiterEvent(new ChangeEvent(this));
	}
	
	
	public void changeMainColor() {
		frame.getTools().getColorManager().changeColor();
	}


	
	public class jopProjectProperties {
		
		public jopProjectProperties(ModelIhm model) {
			JTextField nameField = new JTextField();
			JTextField xField = new JTextField();
			JTextField yField = new JTextField();

			JPanel maincontainer = new JPanel();
			maincontainer.setLayout(new BorderLayout(20, 20));

			JPanel topcontainer = new JPanel(new GridLayout(2, 1));
			topcontainer.add(new JLabel("What is the name of your project ?"));
			topcontainer.add(nameField);
			nameField.setText(model.getPaper().getFileName());
			maincontainer.add(topcontainer, BorderLayout.NORTH);

			maincontainer.add(Box.createHorizontalStrut(15));

			JPanel botcontainer = new JPanel(new GridLayout(2, 2));
			botcontainer.add(new JLabel("Width :"));
			botcontainer.add(xField);
			xField.setText(model.getPaper().getWidth()+"");
			botcontainer.add(new JLabel("Height :"));
			yField.setText(model.getPaper().getHeight()+"");
			botcontainer.add(yField);

			maincontainer.add(botcontainer);

			maincontainer
					.add(Box.createHorizontalStrut(15), BorderLayout.SOUTH);

			int result = JOptionPane.showConfirmDialog(null, maincontainer,
					"Creat a new project !", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					int x = Integer.parseInt(xField.getText());
					int y = Integer.parseInt(yField.getText());
					
					model.getPaper().setFileName(nameField.getText());
					model.getPaper().setWidth(x);
					model.getPaper().setHeight(y);
					
					frame.resizePaperBorder();
					traiterEvent(new ChangeEvent(this));
					

				} catch (Exception e) {
					e.printStackTrace();
					
					JOptionPane.showConfirmDialog(frame, "Wrong value !",
							"Error", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}
	
	

	/**
	 * 
	 * New project dialog box
	 * 
	 * @author jvdur_000
	 * 
	 */
	public class jopNewProject {

		public jopNewProject(ModelIhm model) {
			JTextField nameField = new JTextField();
			JTextField xField = new JTextField();
			JTextField yField = new JTextField();

			JPanel maincontainer = new JPanel();
			maincontainer.setLayout(new BorderLayout(20, 20));

			JPanel topcontainer = new JPanel(new GridLayout(2, 1));
			topcontainer.add(new JLabel("What is the name of your project ?"));
			topcontainer.add(nameField);
			maincontainer.add(topcontainer, BorderLayout.NORTH);

			maincontainer.add(Box.createHorizontalStrut(15));

			JPanel botcontainer = new JPanel(new GridLayout(2, 2));
			botcontainer.add(new JLabel("Width :"));
			botcontainer.add(xField);
			xField.setText(""+800);
			botcontainer.add(new JLabel("Height :"));
			yField.setText(""+400);
			botcontainer.add(yField);

			maincontainer.add(botcontainer);

			maincontainer
					.add(Box.createHorizontalStrut(15), BorderLayout.SOUTH);

			int result = JOptionPane.showConfirmDialog(null, maincontainer,
					"Creat a new project !", JOptionPane.OK_CANCEL_OPTION);
			if (result != JOptionPane.OK_OPTION) {
			
			} else {
				try {
					int x = Integer.parseInt(xField.getText());
					int y = Integer.parseInt(yField.getText());

					if (x < 1 || y < 1)
						throw new Exception();
					
					model.paper = new PaperDraw(nameField.getText(), x,
							y);
					model.paperOnBoard = true;
					board.setPaper(model.paper);

					frame.resizePaperBorder();
					model.setSelectedLayer(0);

				} catch (Exception e) {
					e.printStackTrace();
					
					JOptionPane.showConfirmDialog(frame, "Wrong value !",
							"Error", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}


}
