package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bizz.PaperLayer;

public class ElmtStatusBar extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 1L;
	
	private ModelIhm model;
	private JLabel statusLabel;
	
	
	public ElmtStatusBar(ModelIhm model) {
		
		this.model = model;
		model.addChangeListener(this);

	    this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	    this.setPreferredSize(new Dimension(this.getWidth(), 16));
	    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	    this.setBorder(null);
		this.setBackground(AbstractVue.JMB_BACKGROUND);
	   
	    statusLabel = new JLabel("");
	    
	    this.add(statusLabel);
		
	    updateStatusBar("-", "-");
	    
	}
	
	private void updateStatusBar(String x, String y) {
		String txt = "status";
		if (model.isPaperOnBoard()) {
			txt = "Project name : " + model.getFileName() + " | ";
			txt += model.getPaper().getLayerList().size() + " layers | ";
			
			int k = 0;
			for (int i = 0; i < model.getPaper().getLayerList().size(); i++) {
				k += model.getPaper().getLayerList().get(i).getDrawList().size();
			}
			
			txt += k + " drawings | ";
			
			txt += " Coordonates X: " + x + " Y: " + y;

		}
		
		this.statusLabel.setText(txt);
		this.statusLabel.setForeground(AbstractVue.STATUS_BAR_TEXT_COLOR);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		updateStatusBar("-", "-");
	}

	public void updateMouseVal(String x, String y) {
		updateStatusBar(""+x, ""+y);
	}

	
}
