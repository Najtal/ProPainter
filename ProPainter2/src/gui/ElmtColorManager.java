package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Manage the color picker and alow to choo colors
 * 
 * @author jvdur_000
 * 
 */
public class ElmtColorManager extends JPanel implements ChangeListener,
		ActionListener {

	private static final long serialVersionUID = 1L;

	private ModelIhm model;

	private JPanel glSelectedColor;
	private JButton jbSelectedColor;

	private JPanel glColorTab;
	private ArrayList<JButton> alPanels;

	private JLabel colorInfoRGB;

	/**
	 * Constructor of the color manager
	 * 
	 * @param model
	 */
	public ElmtColorManager(ModelIhm model) {

		this.model = model;

		model.addChangeListener(this);

		this.alPanels = new ArrayList<JButton>(10);

		this.setLayout(new BorderLayout(15, 15));
		this.setBorder(new EmptyBorder(20, 30, 10, 30));

		initComponents();

	}

	private void initComponents() {

		/*
		 * Main Color
		 */
		glSelectedColor = new JPanel(new GridLayout(1, 1));

		jbSelectedColor = new JButton();
		jbSelectedColor.setBackground(model.getSelectedColor());
		jbSelectedColor.setPreferredSize(new Dimension(200, 40));
		jbSelectedColor.addActionListener(this);

		glSelectedColor.add(jbSelectedColor);

		this.add(glSelectedColor, BorderLayout.NORTH);

		/*
		 * Color details
		 */
		colorInfoRGB = new JLabel("Red: " + model.getSelectedColor().getRed()
				+ "    Green: " + model.getSelectedColor().getGreen()
				+ "    Blue: " + model.getSelectedColor().getBlue());

		this.add(colorInfoRGB, BorderLayout.CENTER);

		/*
		 * 10 Colors
		 */
		glColorTab = new JPanel(new GridLayout(2, 5, 10, 10));

		ArrayList<Color> ca = model.getTabPreferedColors();

		for (int i = 0; i < ca.size(); i++) {
			JButton c = new JButton();
			c.setBackground(ca.get(i));
			c.setPreferredSize(new Dimension(30, 30));
			c.addActionListener(this);
			alPanels.add(i, c);
			glColorTab.add(c);
		}

		this.add(glColorTab, BorderLayout.SOUTH);
	}

	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.removeAll();
		initComponents();
	}

	public void changeColor() {
		Color newSelectedColor = JColorChooser.showDialog(null,
	            "Choose the new color", model.getSelectedColor());
		if (newSelectedColor != null) {
			model.actionNewSelectedColor(newSelectedColor);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == jbSelectedColor) {			
			changeColor();
		} else {
			int i = 0;
			for (JButton b : alPanels) {
				if (ae.getSource() == b) {
					model.actionChangeCouleurSelectionnee(i);
					break;
				}
				i++;
			}
		}
				
	}

}
