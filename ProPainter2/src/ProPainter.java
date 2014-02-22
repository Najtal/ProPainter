

import gui.AbstractVue;
import gui.MainPPFrame;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 
 * Program loader class
 * 
 * @author jvdur_000
 *
 */
public class ProPainter {
	
	// test // prod // home
	private static final String MODE = "home"; 
	
	// true: ne ferme pas l'application
	private static final boolean DEBUG = true;
	
	// Error message
	private final static String ERREUR_CRITIQUE = "" +
			"The program has encountered a critical error. We apologize for the inconvenience.";

	
	
	public static void main(String[] args) {

		/*
		 * Fatal exceptions handler
		 */
		
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(final Thread arg0,
					final Throwable arg1) {
				
				// Affiche l'erreur dans la console
				System.err.println(arg1.getMessage() + " :\n");
				arg1.printStackTrace();

				// Affiche un pop avertissant l'utilisateur de la cloture du
				// programme

				if (DEBUG) {
					
					ImageIcon iconError = null;
					try {
						iconError = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("images/ico_error.png")));
					} catch (IOException e) {
					}
					JOptionPane
							.showMessageDialog(
									null,
									ERREUR_CRITIQUE, "Critical Erreur",
									JOptionPane.ERROR_MESSAGE, iconError);
				} else {
					final ImageIcon iconError = new ImageIcon(
							"images/ico_error.png");
					JOptionPane
							.showMessageDialog(
									null,
									ERREUR_CRITIQUE, "Critical Erreur",
									JOptionPane.ERROR_MESSAGE, iconError);
					// Ferme le programme
					System.exit(1);
				}
			}
		});

		
		/*
		 * Load the classes
		 */
		
		AbstractVue.initialize();		
		// Load Jframe
		final MainPPFrame accueil = new MainPPFrame();
		// permet de ne pas avoir le focus auto sur le premier jtextfield
		accueil.requestFocus(); 

	}

}
