package ucc;

import gui.ModelIhm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * Allows to save the paperDraw in a file
 * 
 * @author jvdur_000
 * 
 */
public class SaveDrawing {

	private ModelIhm model;
	private JFileChooser fcSave;
	private FileOutputStream fop;
	private ObjectOutputStream oos;
	

	/**
	 * Constructor
	 * 
	 * @param model The GUI MODEL
	 */
	public SaveDrawing(ModelIhm model) {

		this.model = model;


		FileNameExtensionFilter File_ext =
	            new FileNameExtensionFilter("File for ProPainter", "ippj");

		fcSave = new JFileChooser();
		fcSave.setSelectedFile(new File(model.getFileName()));
		fcSave.setFileFilter(File_ext);
		int act = fcSave.showSaveDialog(null);
		
		if (act == JFileChooser.APPROVE_OPTION) {
		
			// SAVE FILE
			try {
				File file = new File(fcSave.getCurrentDirectory() + "/"
						+ fcSave.getSelectedFile() + ".ippj");
	
				System.out.println(fcSave.getCurrentDirectory() + file.getName());
				
				fop = new FileOutputStream(fcSave.getCurrentDirectory() + "\\" + file.getName());
				oos = new ObjectOutputStream(fop);
	
				oos.writeObject(model.getPaper());
	
			} catch (IOException e) {
	
				//e.printStackTrace();
				JOptionPane.showConfirmDialog(null,
						"The program has encoutered a problem while saving your file.\n\n Details:"
								+ e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.ERROR_MESSAGE);
	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fop.close();
					oos.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
