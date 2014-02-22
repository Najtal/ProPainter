package ucc;

import gui.ModelIhm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import bizz.PaperDraw;

public class LoadDrawing {

	private ModelIhm model;
	private JFileChooser fcSave;
	private FileInputStream fis;
	private ObjectInputStream ois;

	public LoadDrawing(ModelIhm model) throws Exception {

		this.model = model;

		FileNameExtensionFilter File_ext = new FileNameExtensionFilter(
				"File for ProPainter", "ippj");

		fcSave = new JFileChooser();
		fcSave.setFileFilter(File_ext);
		int res = fcSave.showSaveDialog(null);
		
		if (res == JFileChooser.APPROVE_OPTION) {

			// SAVE FILE
			try {

				File file = new File(fcSave.getCurrentDirectory() + "/"
						+ fcSave.getSelectedFile());

				System.out.println(fcSave.getCurrentDirectory()
						+ file.getName());

				fis = new FileInputStream(fcSave.getCurrentDirectory() + "\\"
						+ file.getName());

				ois = new ObjectInputStream(fis);

				PaperDraw pd = (PaperDraw) ois.readObject();

				
				model.setPapet(pd);
				model.openOkAndSetVal();

			} catch (ClassNotFoundException | IOException e) {

				JOptionPane.showConfirmDialog(null,
						"The program has encoutered a problem while loading your file.\n\n Details:"
								+ e.getMessage(), "Error",
						JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.ERROR_MESSAGE);
				
				throw new Exception();

			} finally {
				try {
					fis.close();
					ois.close();
				} catch (Exception e) {
				}

			}
		}

	}
}
