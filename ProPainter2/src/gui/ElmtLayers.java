package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bizz.Drawable;
import bizz.PaperLayer;

/**
 * 
 * Manage the layers
 * 
 * @author jvdur_000
 * 
 */
public class ElmtLayers extends JPanel implements ChangeListener, ActionListener {

	private ModelIhm model;

	// THE LAYERS
	private JPanel jpLayerList;
	private JScrollPane jspLayerList;

	private JScrollPane jspDrawingList;
	private JPanel jpDrawingList;
	private JPanel jsGridValuesD;
	private JPanel jsGridValuesL;
	private JSplitPane jSplitPane;
	private JPanel jsMainLayerVal;
	private JPanel jsContGridLayerVal;
	
	private ArrayList<JButton> alDrawingsDelButtons;
	private ArrayList<JButton> alDrawingsVisible;

	
	private ArrayList<JButton> alLayerDelButtons;
	private ArrayList<JButton> alLayerButtons;
	private ArrayList<JButton> alLayerVisible;

	private JButton jbAddNewLayer;

	private JPanel jpD;

	private JPanel jpDl;
		

	public ElmtLayers(ModelIhm model) {

		this.model = model;
		model.addChangeListener(this);

		this.setLayout(new BorderLayout(0, 5));
		this.setPreferredSize(new Dimension(model.getFrame().lwidth, 400));

		/*
		 * The layers
		 */
		jpLayerList = new JPanel(new BorderLayout());
		jspLayerList = new JScrollPane(jpLayerList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspLayerList.setPreferredSize(new Dimension(200, 250));

		initLayerValues();

		/*
		 * The drawings per layer
		 */
		jpDrawingList = new JPanel(new BorderLayout());
		jspDrawingList = new JScrollPane(jpDrawingList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		initDrawingValues();

		jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jspDrawingList, jspLayerList);
		jSplitPane.setResizeWeight(1);
		this.add(jSplitPane, BorderLayout.CENTER);

	}

	
	/**
	 * Initialize the Layer list
	 */
	private void initLayerValues() {

		if (!model.isPaperOnBoard())
			return;

		jsMainLayerVal = new JPanel(new BorderLayout(0, 0));
		jsContGridLayerVal = new JPanel(new BorderLayout());
		jsGridValuesL = new JPanel(new GridLayout(0, 1));

		jbAddNewLayer = new JButton("Add a layer");
		jbAddNewLayer.setBackground(AbstractVue.ADD_LAYER_BUTTON);
		jbAddNewLayer.addActionListener(this);
		
		
		ArrayList<PaperLayer> ad = model.getPaper().getLayerList();
		
		alLayerDelButtons = new ArrayList<JButton>(ad.size());
		alLayerButtons = new ArrayList<JButton>(ad.size());
		alLayerVisible = new ArrayList<JButton>(ad.size());


		for (int i = 0; i < ad.size(); i++) {

			jpDl = new JPanel(new BorderLayout());
			
			JButton jbDel = new JButton(AbstractVue.instance.ICON_LAYER_DELETE);
			jbDel.setBackground(AbstractVue.MAIN_BG_COLOR);
			alLayerDelButtons.add(jbDel);
			jbDel.addActionListener(this);
			
			JButton jbVisible = null;
			if (ad.get(i).isVivible()) {
				jbVisible = new JButton(AbstractVue.instance.ICON_EYE);
			} else {
				jbVisible = new JButton(AbstractVue.instance.ICON_EYE_INV);
			}
			alLayerVisible.add(jbVisible);
			jbVisible.addActionListener(this);
			jbVisible.setBackground(AbstractVue.MAIN_BG_COLOR);
			
			
			JButton jbD = new JButton(i + " " + ad.get(i).getName());
			jbD.setBackground(AbstractVue.MAIN_BG_COLOR);
			jbD.setHorizontalAlignment(SwingConstants.LEFT);
			alLayerButtons.add(jbD);
			jbD.addActionListener(this);
			
			
			
			JPanel jpFl = new JPanel(new BorderLayout(0, 0));
			jpFl.add(jbVisible, BorderLayout.WEST);
			jpFl.add(jbDel, BorderLayout.EAST);
			
			jpDl.add(jbD, BorderLayout.CENTER);
			jpDl.add(jpFl, BorderLayout.EAST);
			jsGridValuesL.add(jpDl);
			
			if (i == model.getSelectedLayer()) {
				jbD.setBackground(AbstractVue.ON_SELECTED_BUTTON_COLOR);
			} else {
				jbD.setBackground(AbstractVue.MAIN_BG_COLOR);
			}
		}
		
		jsContGridLayerVal.add(jsGridValuesL, BorderLayout.SOUTH);
		jsMainLayerVal.add(jsContGridLayerVal, BorderLayout.CENTER);
		jsMainLayerVal.add(jbAddNewLayer, BorderLayout.SOUTH);
		
		jpLayerList.add(jsMainLayerVal, BorderLayout.SOUTH);

	}


	/**
	 * Initialiez the Drawing list
	 */
	private void initDrawingValues() {

		if (!model.isPaperOnBoard())
			return;
		
		jsGridValuesD = new JPanel(new GridLayout(0, 1));

		ArrayList<Drawable> ad = model.getPaper().getLayerList()
				.get(model.getSelectedLayer()).getDrawList();
		
		alDrawingsDelButtons = new ArrayList<JButton>(ad.size());
		alDrawingsVisible = new ArrayList<JButton>(ad.size());

		for (int i = 0; i < ad.size(); i++) {
			
			jpD = new JPanel(new BorderLayout());
			
			JButton jbDel = new JButton(AbstractVue.instance.ICON_LAYER_DELETE);
			alDrawingsDelButtons.add(jbDel);
			jbDel.addActionListener(this);
			jbDel.setBackground(AbstractVue.MAIN_BG_COLOR);
			
			JButton jbVisible = null;
			if (ad.get(i).isVisible()) {
				jbVisible = new JButton(AbstractVue.instance.ICON_EYE);
			} else {
				jbVisible = new JButton(AbstractVue.instance.ICON_EYE_INV);
			}
			alDrawingsVisible.add(jbVisible);
			jbVisible.addActionListener(this);
			jbVisible.setBackground(AbstractVue.MAIN_BG_COLOR);
			
			int tn = ad.get(i).getType();
			if (ad.get(i).isFilled())
				tn++;
			JButton jbD = new JButton(i + " " +
					Drawable.tabToolNames[ad.get(i).getType()]);
			jbD.setBackground(AbstractVue.MAIN_BG_COLOR);
			jbD.setHorizontalAlignment(SwingConstants.LEFT);
			
			jpD.add(jbD, BorderLayout.CENTER);
			
			JPanel jpFl = new JPanel(new BorderLayout(0, 0));
			jpFl.add(jbVisible, BorderLayout.WEST);
			jpFl.add(jbDel, BorderLayout.EAST);
			jpD.add(jpFl, BorderLayout.EAST);
			jsGridValuesD.add(jpD);
		}
		
		jpDrawingList.add(jsGridValuesD, BorderLayout.SOUTH);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		jpLayerList.removeAll();
		initLayerValues();

		jpDrawingList.removeAll();
		initDrawingValues();
		
		repaint();
		this.revalidate();
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == jbAddNewLayer) {
			String name = JOptionPane.showInputDialog(null, "What is te layer name ?");

			if (name == null || name.equals(""))
				return;
			
			model.addNewLayer(name);
			return;
		}
		
		// new layer selected
		if (alLayerButtons.contains(ae.getSource())) {
			for (int i = 0; i < alLayerButtons.size(); i++) {
				// quand o le trouve
				if (alLayerButtons.get(i) == ae.getSource()) {
					model.setSelectedLayer(i);
				}
			}
			return;
		}
		
		// if delete layer
		if (alLayerDelButtons.contains(ae.getSource())) {
			
			if (alLayerDelButtons.size() == 1) {
				return;
			}
			
			// on parcours tous les layer buttons
			for (int i = 0; i < alLayerDelButtons.size(); i++) {
				// quand o le trouve
				if (alLayerDelButtons.get(i) == ae.getSource()) {
			
					model.getPaper().removeFromArray(i);

					jsGridValuesL.remove(jsGridValuesL.getComponent(i));
					removeFromLayer(i);
					
					if (i == 0) {
						model.setSelectedLayer(1);
					} else {
						model.setSelectedLayer(i-1);
					}
					
					model.getBoard().repaint();

					model.getFrame().getStatusPanel().updateMouseVal("-", "-");
					this.revalidate();
				}
			}
			return;
		}
		
		// if delete drawing
		if (alDrawingsDelButtons.contains(ae.getSource())) {
			for (int i = 0; i < alDrawingsDelButtons.size(); i++) {
				if (alDrawingsDelButtons.get(i) == ae.getSource()) {

					/*Drawable d = model.getPaper().getLayerList()
							.get(model.getSelectedLayer()).getDrawable(i);*/
					model.getPaper().getLayerList()
							.get(model.getSelectedLayer()).removeFromArray(i);

					jsGridValuesD.remove(jsGridValuesD.getComponent(i));
					removeFromDraw(i);

					model.getBoard().repaint();
					
					model.getFrame().getStatusPanel().updateMouseVal("-", "-");
					this.revalidate();
				}
			}
			return;
		}
		
		// if make drawing inv / visible again
		if (alDrawingsVisible.contains(ae.getSource())) {
			for (int i = 0; i < alDrawingsVisible.size(); i++) {
				if (alDrawingsVisible.get(i) == ae.getSource()) {
					
					model.getPaper().getLayerList()
							.get(model.getSelectedLayer()).getDrawable(i).changeVisibility();

					model.getBoard().repaint();
					
					model.getFrame().getStatusPanel().updateMouseVal("-", "-");
					this.stateChanged(null);
					//this.revalidate();
				}
			}
			return;
		}
		
		
		
		// if make layer inv / visible again
		if (alLayerVisible.contains(ae.getSource())) {
			for (int i = 0; i < alLayerVisible.size(); i++) {
				if (alLayerVisible.get(i) == ae.getSource()) {
					
					model.getPaper().getLayerList().get(i).changeVisibility();

					model.getBoard().repaint();
					
					model.getFrame().getStatusPanel().updateMouseVal("-", "-");
					this.stateChanged(null);
					//this.revalidate();
				}
			}
			return;
		}
		
	}
	
	
	private void removeFromDraw(int i) {
		ArrayList<JButton> newl = new ArrayList<JButton>(alDrawingsDelButtons.size()-1);
		for (int j = 0; j < alDrawingsDelButtons.size(); j++) {
			if (j != i) {
				newl.add(alDrawingsDelButtons.get(j));
			}
		}
		alDrawingsDelButtons = newl;
	}
	
	private void removeFromLayer(int i) {
		ArrayList<JButton> newl = new ArrayList<JButton>(alLayerDelButtons.size()-1);
		for (int j = 0; j < alLayerDelButtons.size(); j++) {
			if (j != i) {
				newl.add(alLayerDelButtons.get(j));
			}
		}
		alLayerDelButtons = newl;
	}

}
