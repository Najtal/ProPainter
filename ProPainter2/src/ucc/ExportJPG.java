package ucc;

import gui.ModelIhm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportJPG {

	private ModelIhm model;
	private JFileChooser fcSave;
	private FileOutputStream fop;
	private ObjectOutputStream oos;

	public ExportJPG(ModelIhm model) {
		
		
		this.model = model;

		
		FileNameExtensionFilter File_ext =
	            new FileNameExtensionFilter("Image file", "jpg");

		fcSave = new JFileChooser();
		fcSave.setSelectedFile(new File(model.getFileName()));
		fcSave.setFileFilter(File_ext);
		fcSave.showSaveDialog(null);
		
		
		// SAVE FILE
		try {
			
			ImageIO.write(model.getImageFromPaper(), "jpg", new File(fcSave.getSelectedFile() + ".jpg"));

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
