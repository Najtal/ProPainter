package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * Manage the TOOL Panel
 * 
 * @author jvdur_000
 *
 */
public class ElmtTool extends JPanel  {

	
	private ModelIhm model;
	
	
	private JScrollPane jspTools;
	private JPanel jpListToolsContainer;
	private ElemToolSelector jpListTools;

	private ElmtColorManager jpColorManager;


	
	/**
	 * Constructor
	 * @param model The model of the GUI
	 */
	public ElmtTool(ModelIhm model) {
		
		this.model = model;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(model.getFrame().twidth, HEIGHT));
		
		
		// List of tools
		jpListTools = new ElemToolSelector(model);
		jpListToolsContainer = new JPanel();
		jpListToolsContainer.add(jpListTools);
		
		jspTools = new JScrollPane(jpListToolsContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspTools.setViewportBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		jspTools.setBorder(null);
		this.add(jspTools, BorderLayout.CENTER);
		
		
		// Colors
		jpColorManager = new ElmtColorManager(model);
		this.add(jpColorManager, BorderLayout.SOUTH);
	}



	public ElmtColorManager getColorManager() {
		return jpColorManager;
	}

	
	
}
