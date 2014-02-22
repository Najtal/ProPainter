package gui;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Generates the Menu tool bar
 * 
 * @author jvdur_000
 * 
 */
public class ElmtMenu extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;

	private ArrayList<JButton> arrayJBController;

	private JButton jbNew;
	private JButton jbOpen;
	private JButton jbSave;
	private JButton jbRedo;
	private JButton jbUndo;
	private JButton jbExport;


	private static boolean jbNewTurn;
	private static boolean jbOpenTurn;
	private static boolean jbSaveTurn;
	private static boolean jbExportTurn;
	private static boolean jbUndoTurn;
	private static boolean jbRedoTurn;

	private ImageIcon tmp;


	/**
	 * Constructor
	 * 
	 * @param model
	 *            Model of the GUI
	 */
	public ElmtMenu(ModelIhm model) {

		this.model = model;

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.setBackground(AbstractVue.JMB_BACKGROUND);
		this.setForeground(AbstractVue.JMB_TEXT_COLOR);

		initMenu();

	}

	/**
	 * Initialise all the elements of the menu
	 */
	private void initMenu() {

		/*
		 * Liste des boutons du menu
		 */
		arrayJBController = new ArrayList<JButton>();

		// NEW
		jbNew = new JButton("New", AbstractVue.instance.iNew);
		arrayJBController.add(jbNew);
		this.add(jbNew);

		// OPEN
		jbOpen = new JButton("Open", AbstractVue.instance.iOpen);
		arrayJBController.add(jbOpen);
		this.add(jbOpen);

		// SAVE
		jbSave = new JButton("Save", AbstractVue.instance.iSave);
		arrayJBController.add(jbSave);
		this.add(jbSave);
		
		// EXPORT
		jbExport = new JButton("Export", AbstractVue.instance.iExport);
		arrayJBController.add(jbExport);
		this.add(jbExport);
		

		
		// UNDO
		jbUndo = new JButton("Undo", AbstractVue.instance.iUndo);
		arrayJBController.add(jbUndo);
		this.add(jbUndo);
		
		// REDO
		jbRedo = new JButton("Redo", AbstractVue.instance.iRedo);
		arrayJBController.add(jbRedo);
		this.add(jbRedo);
		
		
		/*
		// REDUCE TOOL
		jbRedTool = new JButton("Recude Tool panel", AbstractVue.iUndo);
		arrayJBController.add(jbRedTool);
		this.add(jbRedTool);
		
		// REDUCE LAYER
		jbRedLayer = new JButton("Reduce Layer panel", AbstractVue.iRedo);
		arrayJBController.add(jbRedLayer);
		this.add(jbRedLayer);
		*/


		
		
		
		for (int i = 0; i < arrayJBController.size(); i++) {

			final JButton jbm = arrayJBController.get(i);

			jbm.setForeground(AbstractVue.JMB_TEXT_COLOR);
			jbm.setBackground(AbstractVue.JMB_BACKGROUND);
			jbm.setBorder(new EmptyBorder(0, 10, 0, 0));
			jbm.setFocusPainted(false);
			jbm.setMargin(new Insets(0, 10, 5, 10));

			jbm.addActionListener(this);
			jbm.addMouseListener(this);

		}
	}

	/**
	 * Button click handler
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == jbNew)
			model.actionNew();
		else if (ae.getSource() == jbOpen)
			model.actionOpen();
		else if (ae.getSource() == jbSave)
			model.actionSave();
		else if (ae.getSource() == jbExport)
			model.actionExport();
		else if (ae.getSource() == jbRedo)
			model.actionRedo();
		else if (ae.getSource() == jbUndo)
			model.actionUndo();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		tmp = (ImageIcon) ((JButton) me.getSource()).getIcon();

		
		if (((JButton) me.getSource()) == jbNew) {
			if (!jbNewTurn) {
				jbNewTurn = true;
				makeTurn(jbNew);
			}
		}		
		else if (((JButton) me.getSource()) == jbOpen) {
			if (!jbOpenTurn) {
				jbOpenTurn = true;
				makeTurn(jbOpen);
			}
		}		
		else if (((JButton) me.getSource()) == jbSave) {
			if (!jbSaveTurn) {
				jbSaveTurn = true;
				makeTurn(jbSave);
			}
		}		
		else if (((JButton) me.getSource()) == jbExport) {
			if (!jbExportTurn) {
				jbExportTurn = true;
				makeTurn(jbExport);
			}
		}	
		else if (((JButton) me.getSource()) == jbRedo) {
			if (!jbUndoTurn) {
				jbUndoTurn = true;
				makeTurn(jbRedo);
			}
		}		
		else if (((JButton) me.getSource()) == jbUndo) {
			if (!jbRedoTurn) {
				jbRedoTurn = true;
				makeTurn(jbUndo);
			}
		}		
		
			

	}

	/**
	 * Thread make the icon turn
	 * 
	 * @param source
	 *            Jbutton who has to turn
	 * @param bl 
	 */
	private void makeTurn(final JButton source) {

		
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				
				for (int j = 0; j < 360; j++) {

					ImageIcon i = (ImageIcon) source.getIcon();

					AffineTransform lAt = new AffineTransform();
					lAt.translate(i.getIconWidth() / 2, i.getIconHeight() / 2);
					lAt.rotate(Math.toRadians(j));
					lAt.translate(-i.getIconWidth() / 2, -i.getIconHeight() / 2);

					BufferedImage lBi = new BufferedImage(i.getIconWidth(), i
							.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
					Graphics2D lG2 = lBi.createGraphics();
					lG2.drawImage(i.getImage(), lAt, null);

					ImageIcon lNewImage = new ImageIcon(lBi);
					source.setIcon(lNewImage);

					j++;

					try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (source == jbNew)
						source.setIcon(AbstractVue.instance.iNew);
					else if (source == jbOpen)
						source.setIcon(AbstractVue.instance.iOpen);
					else if (source == jbSave)
						source.setIcon(AbstractVue.instance.iSave);
					else if (source == jbExport)
						source.setIcon(AbstractVue.instance.iExport);
					else if (source == jbRedo)
						source.setIcon(AbstractVue.instance.iRedo);
					else if (source == jbUndo)
						source.setIcon(AbstractVue.instance.iUndo);
				}
				
				if (source == jbNew)
					jbNewTurn = false;		
				else if (source == jbOpen)
					jbOpenTurn = false;
				else if (source == jbSave)
					jbSaveTurn = false;
				else if (source == jbExport)
					jbExportTurn = false;
				else if (source == jbRedo)
					jbUndoTurn = false;
				else if (source == jbUndo)
					jbRedoTurn = false;
			}
		});

		thread.start();
		
		

	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
