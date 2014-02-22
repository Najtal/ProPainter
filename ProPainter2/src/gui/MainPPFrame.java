package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * Manage the JFrame
 * 
 * @author jvdur_000
 * 
 */
public class MainPPFrame extends JFrame implements ComponentListener, ChangeListener {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;

	private JMenuBar jmb;
	private ElmtStatusBar statusPanel;
	private ElmtMenu menu;
	private ElmtTool tools;
	private ElmtLayers layers;
	private ElmtPaper elemPaper;

	private Container paperBorder;
	private JPanel borderN;
	private JPanel borderE;
	private JPanel borderW;
	private JPanel borderS;

	private JScrollPane jspPaper;

	int twidth;
	int lwidth;

	/**
	 * Constructor
	 * 
	 * @param model
	 */
	public MainPPFrame() {

		this.model = ModelIhm.getInstance(this);
		this.model.addChangeListener(this);
		
		// JFrame variables

		this.twidth = 300;
		this.lwidth = 200;

		this.setSize(new Dimension(1200, 700));
		this.setMinimumSize(new Dimension(700, 480));
		this.setResizable(true);

		this.getContentPane().setBackground(AbstractVue.COLOR_BACKGROUND);
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				super.windowClosing(e);
				model.actionExit();
			}
		});

		// set frame icon
		final Image icon = ((ImageIcon) AbstractVue.instance.ICON_COIN_GAUCHE).getImage();
		this.setIconImage(icon);
		this.setName(AbstractVue.NOM_APP);
		this.setTitle(AbstractVue.NOM_APP);

		/*
		 * Menu bar
		 */
		jmb = new ElmtMenuBar(model);
		this.setJMenuBar(jmb);

		/*
		 * Statut bar (BOTTOM)
		 */
		statusPanel = new ElmtStatusBar(model);
		this.add(statusPanel, BorderLayout.SOUTH);

		/*
		 * Various options bar (TOP)
		 */
		menu = new ElmtMenu(model);
		this.add(menu, BorderLayout.NORTH);

		/*
		 * Tool bar (LEFT)
		 */
		tools = new ElmtTool(model);
		this.add(tools, BorderLayout.WEST);

		/*
		 * Layer bar (RIGHT)
		 */
		layers = new ElmtLayers(model);
		this.add(layers, BorderLayout.EAST);

		/*
		 * Drawable container (CENTER)
		 */
		paperBorder = new JPanel(new BorderLayout());
		paperBorder.setBackground(Color.orange);

		borderN = new JPanel();
		borderE = new JPanel();
		borderW = new JPanel();
		borderS = new JPanel();

		elemPaper = new ElmtPaper(model);

		paperBorder.add(borderN, BorderLayout.NORTH);
		paperBorder.add(borderE, BorderLayout.EAST);
		paperBorder.add(borderW, BorderLayout.WEST);
		paperBorder.add(borderS, BorderLayout.SOUTH);

		paperBorder.add(elemPaper, BorderLayout.CENTER);

		jspPaper = new JScrollPane(paperBorder,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jspPaper.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1,
				Color.DARK_GRAY));

		this.add(jspPaper, BorderLayout.CENTER);

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				resizePaperBorder();
			}
		});
		
		
		

		/*
		 * End JFRAME config
		 */
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public ElmtLayers getLayers() {
		return layers;
	}

	public JMenuBar getJmb() {
		return jmb;
	}

	public ElmtStatusBar getStatusPanel() {
		return statusPanel;
	}

	public ElmtMenu getMenu() {
		return menu;
	}

	public ElmtTool getTools() {
		return tools;
	}

	public ElmtPaper getElemPaper() {
		return elemPaper;
	}

	public Container getPaperBorder() {
		return paperBorder;
	}

	public void reduceToolLayer() {

		Thread thread = new Thread(new Runnable() {
			public void run() {

				for (int j = tools.getWidth()/2; j > 0; j--) {

					twidth = twidth-2;
					tools.setPreferredSize(new Dimension(twidth, tools.getHeight()));
					jspPaper.repaint();
					resizePaperBorder();
					revalidate();

					resizePaperBorder();

					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {}
				}
			}
		});

		thread.start();
	}
	
	
	public void openToolLayer() {
				
		Thread thread = new Thread(new Runnable() {
			public void run() {

				for (int j = 0; j < 300/2; j++) {

					twidth = twidth+2;
					
					tools.setPreferredSize(new Dimension(twidth, tools.getHeight()));
					jspPaper.repaint();
					resizePaperBorder();
					revalidate();

					resizePaperBorder();

					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {}
				}
			}
		});

		thread.start();
		
	}
	
	
	
	
	public void reduceLayerLayer() {

		Thread thread = new Thread(new Runnable() {


			public void run() {

				for (int j = layers.getWidth()/2; j > 0; j--) {

					lwidth = lwidth-2;
					
					layers.setPreferredSize(new Dimension(lwidth, layers.getHeight()));
					jspPaper.repaint();
					resizePaperBorder();
					revalidate();

					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {}
				}
			}
			
		});

		thread.start();		
	}

	public void openLayerLayer() {

		Thread thread = new Thread(new Runnable() {
			public void run() {

				for (int j = 0; j < 200/2; j++) {

					lwidth = lwidth+2;
					layers.setPreferredSize(new Dimension(lwidth, layers.getHeight()));
					jspPaper.repaint();
					resizePaperBorder();
					revalidate();

					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {}
				}
			}
		});

		thread.start();		
	}
	
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (model.isPaperOnBoard()) {
			this.setTitle(AbstractVue.NOM_APP + " - " + model.getFileName());
		} else {
			this.setTitle(AbstractVue.NOM_APP);
		}
	}
	

	/*
	 * WINDOWS LISTENERS (non-Javadoc)
	 * 
	 * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
	 * ComponentEvent)
	 */
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		resizePaperBorder();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Resize the paper borders
	 */
	void resizePaperBorder() {

		if (model.isPaperOnBoard())
			elemPaper.setPaper(model.getPaper());
		
		int jspW = jspPaper.getWidth();
		int jspH = jspPaper.getHeight();

		// System.out.println(jspW + " : " + jspH);

		int pW = elemPaper.getPaperWidth();
		int pH = elemPaper.getPaperHeight();

		int borderTB = (jspH - pH - 50) / 2;
		if (borderTB < 0)
			borderTB = 0;

		int borderEW = (jspW - pW - 50) / 2;
		if (borderEW < 0)
			borderEW = 0;

		if (borderTB <= 25)
			jspPaper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		else
			jspPaper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		if (borderEW <= 25)
			jspPaper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		else
			jspPaper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		borderN.setPreferredSize(new Dimension(WIDTH, borderTB + 30));
		borderS.setPreferredSize(new Dimension(WIDTH, borderTB + 70));
		borderE.setPreferredSize(new Dimension(borderEW + 70, HEIGHT));
		borderW.setPreferredSize(new Dimension(borderEW + 30, HEIGHT));

		paperBorder.revalidate();
		jspPaper.validate();
	}




}
