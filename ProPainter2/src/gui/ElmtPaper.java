package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bizz.DrawCircle;
import bizz.DrawLine;
import bizz.DrawPoint;
import bizz.DrawSquare;
import bizz.DrawTriangle;
import bizz.Drawable;
import bizz.PaperDraw;
import bizz.PaperLayer;

/**
 * 
 * Manage the whole display of all the drawables
 * 
 * @author jvdur_000
 * 
 */
public class ElmtPaper extends JPanel implements ChangeListener, MouseListener,
		MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;
	private PaperDraw paper;

	// WHILE CREATING
	private boolean creating;
	private int toolCode;
	private Drawable newFigure;

	private Image imPencil;

	private Toolkit toolkit;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            The GUI Model
	 */
	public ElmtPaper(ModelIhm model) {

		this.model = model;
		this.model.setElmtPaper(this);
		model.addChangeListener(this);

		this.setPreferredSize(new Dimension(150, 60));

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(new PopClickListener());

		this.add(new JLabel("Pro Painter !", AbstractVue.instance.ICON_COIN_GAUCHE,
				JLabel.HORIZONTAL));

		// change cursor
		toolkit = Toolkit.getDefaultToolkit();
		imPencil = AbstractVue.instance.ICON_PENCIL.getImage();

	}

	public PaperDraw getPaper() {
		return paper;
	}

	public void setPaper(PaperDraw paper) {
		this.paper = paper;
		this.removeAll();
		this.setPreferredSize(new Dimension(paper.getWidth(), paper.getHeight()));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (model.getPaper() != null && model.isPaperOnBoard()) {
			drawMeLikeOneOfYourFrenchGirls(g);
		}

	}

	public BufferedImage getMeAnImage() {
		BufferedImage image = new BufferedImage(model.getPaper().getWidth(),
				model.getPaper().getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, model.getPaper().getWidth(), model.getPaper()
				.getHeight());

		drawMeLikeOneOfYourFrenchGirls(g);

		return image;
	}

	private void drawMeLikeOneOfYourFrenchGirls(Graphics g) {

		paper = model.getPaper();
		
		PaperLayer pl;
		Drawable d;
		
		for (int i = 0; i < paper.getLayerList().size(); i++) {
			
			pl = paper.getLayerList().get(i);
			if (pl.isVivible()) {
				
				for (int j = 0; j < paper.getLayerList().get(i).getDrawList()
						.size(); j++) {
		
					d = paper.getLayerList().get(i).getDrawList().get(j);
					if (d.isVisible()) {
						d.drawMe(g);
					}
					
				}
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}

	public int getPaperWidth() {
		if (paper == null)
			return 150;
		return paper.getWidth();
	}

	public int getPaperHeight() {
		if (paper == null)
			return 60;
		return paper.getHeight();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		if (model.isPaperOnBoard()) {
			Cursor c = toolkit.createCustomCursor(imPencil, new Point(0, 0), "img");
			this.setCursor(c);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		model.getFrame().setCursor(Cursor.DEFAULT_CURSOR);
		model.getFrame().getStatusPanel().updateMouseVal("-", "-");
	}

	@Override
	public void mousePressed(MouseEvent me) {

		Drawable d = null;

			// LINE
		if (model.getIdToolSelected() == Drawable.DRAW_LINE) {
			d = new DrawLine(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()),
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);
			
			// TRIANGLE
		} else if (model.getIdToolSelected() == Drawable.DRAW_TRIANGLE) {
			d = new DrawTriangle(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()),
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);
			
			// SQUARE
		} else if (model.getIdToolSelected() == Drawable.DRAW_SQUARE) {
			d = new DrawSquare(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()), false,
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);

			// SQUARE FILL
		} else if (model.getIdToolSelected() == Drawable.DRAW_SQUARE_FILL) {
			d = new DrawSquare(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()), true,
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);

			// CIRCLE
		} else if (model.getIdToolSelected() == Drawable.DRAW_CIRCLE) {
			d = new DrawCircle(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()), false,
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);

			// CIRCLE FILL
		} else if (model.getIdToolSelected() == Drawable.DRAW_CIRCLE_FILL) {
			d = new DrawCircle(new DrawPoint(me.getX(), me.getY()),
					new DrawPoint(me.getX(), me.getY()), true,
					model.getSelectedColor());
			model.addDrawableToCurrentLayer(d);
		}

		this.toolCode = model.getIdToolSelected();
		this.creating = true;
		this.newFigure = d;

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		this.creating = false;
		if (model.getIdToolSelected() > -1 && !newFigure.validValues()) {
			model.getPaper().getLayerList().get(model.getSelectedLayer())
					.getDrawList().remove(newFigure);
			model.getFrame().getLayers().stateChanged(new ChangeEvent(this));
		}
	}

	@Override
	public void mouseDragged(MouseEvent me) {

		if (creating) {

				// LINE
			if (toolCode == Drawable.DRAW_LINE) {
				DrawLine d = (DrawLine) newFigure;
				d.setpB(new DrawPoint(me.getX(), me.getY()));

				// TRIANGLE
			} else if (toolCode == Drawable.DRAW_TRIANGLE) {
				DrawTriangle d = (DrawTriangle) newFigure;
				d.setpB(new DrawPoint(me.getX(), me.getY()));
				
				
				// SQUARE
			} else if (toolCode == Drawable.DRAW_SQUARE
					|| toolCode == Drawable.DRAW_SQUARE_FILL) {
				DrawSquare d = (DrawSquare) newFigure;
				d.setpB(new DrawPoint(me.getX(), me.getY()));

				// CIRCLE
			} else if (toolCode == Drawable.DRAW_CIRCLE
					|| toolCode == Drawable.DRAW_CIRCLE_FILL) {
				DrawCircle d = (DrawCircle) newFigure;
				d.setpB(new DrawPoint(me.getX(), me.getY()));

				// LINE
			} else if (toolCode == Drawable.DRAW_LINE) {
				DrawLine d = (DrawLine) newFigure;
				d.setpB(new DrawPoint(me.getX(), me.getY()));
			}

			this.repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent me) {
		model.getFrame().getStatusPanel()
				.updateMouseVal("" + me.getX(), "" + me.getY());

		if (me.getSource() == this) {
			this.requestFocus();
		}

	}
	
	
	/**
	 * 
	 * The Mouse Adapter class who Listen to a right click ;)
	 * 
	 * @author jvdur_000
	 *
	 */
	class PopClickListener extends MouseAdapter implements MouseListener {
		
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	       if (model.isPaperOnBoard()) {
	    	   PopUpDemo menu = new PopUpDemo();
	    	   menu.show(e.getComponent(), e.getX(), e.getY());
	       }
	    }

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	
	/**
	 * 
	 * This class manage generates Popup menu on right click
	 * 
	 * @author jvdur_000
	 *
	 */
	class PopUpDemo extends JPopupMenu implements ActionListener {
	    
		JMenuItem jiRLP;
		JMenuItem jiGetColor;
		private AbstractButton jiRTP;
		
	    public PopUpDemo(){
	        
	        jiRLP = new JMenuItem("Reduce Layer panel");
	        jiRLP.addActionListener(this);
	        add(jiRLP);	
	        
	        jiRTP = new JMenuItem("Reduce Tool panel");
	        jiRTP.addActionListener(this);
	        add(jiRTP);
	        
	        jiGetColor = new JMenuItem("Change color");
	        jiGetColor.addActionListener(this);
	        add(jiGetColor);
	    }

		@Override
		public void actionPerformed(ActionEvent ae) {

			if (ae.getSource() == jiRLP) {
				model.actionReduceLayerPanel();
			} else if (ae.getSource() == jiRTP) {
				model.actionReduceToolPanel();
			} else if (ae.getSource() == jiGetColor) {
				model.changeMainColor();
			}
		}
	}
}
