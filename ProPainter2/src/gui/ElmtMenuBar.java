package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * 
 * Menu bar element from the GUI
 * 
 * @author jvdur_000
 * 
 */
public class ElmtMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;

	/*
	 * JMENUS
	 */

	// FILE
	private JMenu jmFichier;

	// EDIT
	private JMenu jmEdit;

	private JComponent jmHelp;

	private JComponent jmView;

	private JComponent jmProject;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            The model of the GUI
	 */
	public ElmtMenuBar(final ModelIhm model) {

		this.model = model;

		this.setBackground(AbstractVue.JMB_BACKGROUND);
		this.setForeground(AbstractVue.JMB_TEXT_COLOR);
		this.setPreferredSize(new Dimension(WIDTH, 30));

		this.setBorderPainted(false);

				
		
		/*
		 * FILE
		 */
		jmFichier = new JMenu("File");
		jmFichier.setForeground(AbstractVue.JMB_TEXT_COLOR);
		jmFichier.setBorder(null);
		jmfile();
		this.add(jmFichier);

		/*
		 * EDIT
		 */
		jmEdit = new JMenu("Edit");
		jmEdit.setForeground(AbstractVue.JMB_TEXT_COLOR);
		jmEdit.setBorder(null);
		jmedit();
		this.add(jmEdit);

		/*
		 * VIEW
		 */
		jmView = new JMenu("View");
		jmView.setForeground(AbstractVue.JMB_TEXT_COLOR);
		jmView.setBorder(null);
		jmView();
		this.add(jmView);

		
		/*
		 * Project
		 */
		jmProject = new JMenu("Project");
		jmProject.setForeground(AbstractVue.JMB_TEXT_COLOR);
		jmProject.setBorder(null);
		jmProject();
		this.add(jmProject);
		
		
		/*
		 * HELP
		 */
		jmHelp = new JMenu("Help");
		jmHelp.setForeground(AbstractVue.JMB_TEXT_COLOR);
		jmHelp.setBorder(null);
		jmAbout();
		this.add(jmHelp);

	}



	/**
	 * Generates the FILE menu
	 */
	@SuppressWarnings("serial")
	private void jmfile() {

		// NEW

		final JMenuItem jmiNew = new JMenuItem("New", AbstractVue.instance.iNew);
		jmFichier.add(jmiNew);
		final Action actionNew = new AbstractAction("New", AbstractVue.instance.iNew) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action NEW file
				model.actionNew();
			}
		};

		jmiNew.addActionListener(actionNew);
		final KeyStroke ksNew = KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK);
		jmiNew.setAccelerator(ksNew);

		// Separator
		jmFichier.addSeparator();

		// OPEN

		final JMenuItem jmiOpen = new JMenuItem("Open", AbstractVue.instance.iOpen);
		jmFichier.add(jmiOpen);
		final Action actionOpen = new AbstractAction("Open", AbstractVue.instance.iOpen) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action OPEN file
				model.actionOpen();
			}
		};
		jmiOpen.addActionListener(actionOpen);
		final KeyStroke ksOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK);
		jmiOpen.setAccelerator(ksOpen);

		// SAVE

		final JMenuItem jmiSave = new JMenuItem("Save as", AbstractVue.instance.iSave);

		jmFichier.add(jmiSave);

		final Action actionSave = new AbstractAction("Save as",
				AbstractVue.instance.iSave) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {

				// action SAVE file
				model.actionSave();

			}
		};

		jmiSave.addActionListener(actionSave);
		final KeyStroke ksSave = KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK);
		jmiSave.setAccelerator(ksSave);

		// Separator
		jmFichier.addSeparator();

		// EXPORT
		final JMenuItem jmiExport = new JMenuItem("Export", AbstractVue.instance.iExport);
		jmFichier.add(jmiExport);
		final Action actionExport = new AbstractAction("Export",
				AbstractVue.instance.iSave) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action EXPORT file
				model.actionExport();
			}
		};

		jmiExport.addActionListener(actionExport);

		// Separator
		jmFichier.addSeparator();

		// CLOSE
		final JMenuItem jmiQuit = new JMenuItem("Exit", AbstractVue.instance.iQuit);
		jmFichier.add(jmiQuit);
		final Action actionQuit = new AbstractAction("Exit", AbstractVue.instance.iQuit) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action EXIT file
				model.actionExit();
			}
		};
		jmiQuit.addActionListener(actionQuit);
		final KeyStroke ksQuit = KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK);
		jmiQuit.setAccelerator(ksQuit);

	}

	/**
	 * Generates the EDIT menu
	 */
	@SuppressWarnings("serial")
	private void jmedit() {

		
		// COLOR CHOOSER

		final JMenuItem jmiColor = new JMenuItem("Change color",
				AbstractVue.instance.iColor);
		jmEdit.add(jmiColor);
		final Action actionColor = new AbstractAction("Change color",
				AbstractVue.instance.iColor) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action HELP file
				model.changeMainColor();
			}
		};
		jmiColor.addActionListener(actionColor);
		final KeyStroke ksUndo = KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK);
		jmiColor.setAccelerator(ksUndo);
		
		
		// UNDO

		final JMenuItem jmiUndo = new JMenuItem("Undo",
				AbstractVue.instance.iUndo);
		jmEdit.add(jmiUndo);
		final Action actionUndo = new AbstractAction("Undo",
				AbstractVue.instance.iUndo) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action HELP file
				model.actionUndo();
			}
		};
		jmiUndo.addActionListener(actionUndo);
		final KeyStroke ksColor = KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK);
		jmiUndo.setAccelerator(ksColor);

		
		// REDO

		final JMenuItem jmiRedo = new JMenuItem("Redo",
				AbstractVue.instance.iRedo);
		jmEdit.add(jmiRedo);
		final Action actionRedo = new AbstractAction("Redo",
				AbstractVue.instance.iRedo) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action HELP file
				model.actionRedo();
			}
		};
		jmiRedo.addActionListener(actionRedo);
		final KeyStroke ksRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK);
		jmiRedo.setAccelerator(ksRedo);
	}

	@SuppressWarnings("serial")
	private void jmView() {

		// Reduce Tool panel

		final JMenuItem jmiRedTool = new JMenuItem("Reduce Tool panel",
				AbstractVue.instance.iResizeTool);
		jmView.add(jmiRedTool);
		final Action actionUndo = new AbstractAction("Reduce Tool panel",
				AbstractVue.instance.iResizeTool) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action OPEN file
				model.actionReduceToolPanel();
			}
		};
		jmiRedTool.addActionListener(actionUndo);

		// Reduce Layer panel

		final JMenuItem jmiRedLayer = new JMenuItem("Reduce Layer panel",
				AbstractVue.instance.iResizeLayer);
		jmView.add(jmiRedLayer);
		final Action actionRedo = new AbstractAction("Reduce Layer panel",
				AbstractVue.instance.iResizeLayer) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action SAVE file
				model.actionReduceLayerPanel();

			}
		};
		jmiRedLayer.addActionListener(actionRedo);

	}
	
	
	
	@SuppressWarnings("serial")
	private void jmProject() {

		final JMenuItem jmiPProp = new JMenuItem("Project properties",
				AbstractVue.instance.iProject);
		jmProject.add(jmiPProp);
		final Action actionProject = new AbstractAction("Project properties",
				AbstractVue.instance.iProject) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action HELP file
				model.actionProjectProperties();
			}
		};
		jmiPProp.addActionListener(actionProject);
		
	}
	
	

	private void jmAbout() {

		// ABOUT

		final JMenuItem jmiABout = new JMenuItem("About Pro Painter",
				AbstractVue.instance.iAbout);
		jmHelp.add(jmiABout);
		final Action actionAbout = new AbstractAction("About Pro Painter",
				AbstractVue.instance.iAbout) {
			/**
			 * Défini les actions
			 */
			@Override
			public void actionPerformed(final ActionEvent e) {
				// action HELP file
				model.actionAbout();
			}
		};
		jmiABout.addActionListener(actionAbout);

	}

}
