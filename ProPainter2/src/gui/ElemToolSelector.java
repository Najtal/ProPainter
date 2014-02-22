package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * Manage the Jpanel who contains all the buttons tools
 * 
 * @author jvdur_000
 *
 */
public class ElemToolSelector extends JPanel implements ChangeListener, ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;

	private JPanel glButtonList;

	private static String[] toolNameTab = {"Draw line", "Draw triangle", "Draw square", "Draw filled Square", "Draw oval", "Draw Filled oval"};
	private ArrayList<JButton> jbToolList;

	
	
	public ElemToolSelector(ModelIhm model) {
		
		this.model = model;
		model.addChangeListener(this);
		this.setLayout(new BorderLayout());
		
		this.glButtonList = new JPanel(new GridLayout(
				toolNameTab.length/2 + toolNameTab.length%2, 2));
		
		glButtonList.setBorder(null);
		
		this.add(glButtonList, BorderLayout.NORTH);
		
		jbToolList = new ArrayList<JButton>(toolNameTab.length);
		populateToolList();
	}



	private void populateToolList() {
		
		for (int i = 0; i < toolNameTab.length; i++) {
			JButton jb = new JButton(toolNameTab[i]);
			
			// STYLE
			jb.setPreferredSize(new Dimension(135, 50));
			jb.setBackground(AbstractVue.MAIN_BG_COLOR);
			
			if (i%2 == 0) {
				if (i+2 >= toolNameTab.length) {
					jb.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
				} else {
					jb.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
				}
			} else {
				if (i+1 >= toolNameTab.length) {
					jb.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.LIGHT_GRAY));
				} else {
					jb.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
				}
			}

			// Listeners
			jb.addActionListener(this);
			jb.addMouseListener(this);
			
			// Gestion et intérgration
			jbToolList.add(jb);
			glButtonList.add(jb);
		}
				
	}



	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}



	@Override
	public void actionPerformed(ActionEvent ae) {
				
		int idToolCalled = -1;
		
		for (int i = 0; i < toolNameTab.length; i++) {
			if (jbToolList.get(i) == ae.getSource()) {
				idToolCalled = i;
				break;
			}
		}
				
		if (idToolCalled > -1) {
			
			// on remet le vieux bouton en bonne couleur
			if (model.getIdToolSelected() > -1)
				jbToolList.get(model.getIdToolSelected()).setBackground(AbstractVue.MAIN_BG_COLOR);
			
			// on colorie le nouveau
			model.setIdToolSelected(idToolCalled);
			jbToolList.get(idToolCalled).setBackground(AbstractVue.ON_SELECTED_BUTTON_COLOR);
		}
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
	}



	@Override
	public void mouseEntered(MouseEvent me) {
		int idt = -2;		
		for (int i = 0; i < jbToolList.size(); i++) {
			if (me.getSource() == jbToolList.get(i)) {
				idt = i;
				break;
			}

		}
		
		if (idt != model.getIdToolSelected()) {
			((JButton)me.getSource()).setBackground(AbstractVue.ON_HOVER_BUTTON_COLOR);
		}
	}



	@Override
	public void mouseExited(MouseEvent me) {
		int idt = -2;		
		for (int i = 0; i < jbToolList.size(); i++) {
			if (me.getSource() == jbToolList.get(i)) {
				idt = i;
				break;
			}
		}
				
		if (idt != model.getIdToolSelected()) {
			((JButton) me.getSource()).setBackground(AbstractVue.MAIN_BG_COLOR);
		}	
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

		
		