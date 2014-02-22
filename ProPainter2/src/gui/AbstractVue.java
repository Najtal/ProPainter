package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Reprend toutes les caracteristiques communes ds vue
 * 
 * @author Jean-Vital Durieu
 */
public class AbstractVue extends JPanel {

	public Icon ICON_COIN_GAUCHE;
	public Icon ICON_INFO;
	public ImageIcon iNew;
	public ImageIcon iOpen;
	public ImageIcon iSave;
	public ImageIcon iQuit;
	public ImageIcon iExport;
	public ImageIcon iProject;
	public ImageIcon iAbout;
	public ImageIcon iColor;
	public ImageIcon iResizeTool;
	public ImageIcon iResizeLayer;
	public ImageIcon iUndo;
	public ImageIcon iRedo;
	
	public Icon ICON_EYE;
	public Icon ICON_EYE_INV;

	
	public Icon ICON_JMB_NEW;
	public ImageIcon ICON_PENCIL;
	public Icon ICON_LAYER_DELETE;

	/**
	 * Instance
	 */
	public static AbstractVue instance;

	/**
	 * Constructor
	 * Init the Images
	 */
	private AbstractVue() {
		try {
			ICON_COIN_GAUCHE = new ImageIcon(ImageIO.read(this.getClass()
					.getResourceAsStream("/images/icon_App.png")));

			ICON_INFO = new ImageIcon(ImageIO.read(this.getClass()
					.getResourceAsStream("/images/icon_Info.png")));

			iNew = new ImageIcon(ImageIO.read(this.getClass()
					.getResourceAsStream("/images/icon_New.png")));
			iNew = new ImageIcon(iNew.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));

			iOpen = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Open.png")));
			iOpen = new ImageIcon(iOpen.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iSave = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Save.png")));
			iSave = new ImageIcon(iSave.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iQuit = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Quit.png")));
			iQuit = new ImageIcon(iQuit.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iExport = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Export.png")));
			iExport = new ImageIcon(iExport.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iProject = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Project.png")));
			iProject = new ImageIcon(iProject.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iAbout = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_About.png")));
			iAbout = new ImageIcon(iAbout.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iColor = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Color.png")));
			iColor = new ImageIcon(iColor.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));

			iResizeTool = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Tool.png")));
			iResizeTool = new ImageIcon(iResizeTool.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iResizeLayer = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Layer.png")));
			iResizeLayer = new ImageIcon(iResizeLayer.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iRedo = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Redo.png")));
			iRedo = new ImageIcon(iRedo.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));
			
			iUndo = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					"/images/icon_Undo.png")));
			iUndo = new ImageIcon(iUndo.getImage().getScaledInstance(
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					AbstractVue.JMB_CONTROLLER_ICON_SIZE,
					java.awt.Image.SCALE_SMOOTH));

			ICON_JMB_NEW = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_New.png")));
			
			ICON_PENCIL = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Cursor_Pencil.png")));
			
			ICON_EYE = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Eye.png")));
			
			ICON_EYE_INV = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_EyeInv.png")));
			
			ICON_LAYER_DELETE = new ImageIcon(ImageIO.read(getClass()
					.getResourceAsStream("/images/icon_Layer_Delete.png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialize() {
		instance = new AbstractVue();
	}

	private static final long serialVersionUID = 1L;

	static final String NOM_APP = "Pro Painter";
	static final String PP_VERSION = "v0.4.58";

	// FRAME
	static final Color COLOR_BACKGROUND = Color.LIGHT_GRAY;
	// static final Icon ICON_COIN_GAUCHE = new
	// ImageIcon("images/icon_App.png");

	static final Color MAIN_BG_COLOR = new Color(238, 238, 238);

	// JMENU BAR
	static final int JMB_CONTROLLER_ICON_SIZE = 50;
	static final Color JMB_BACKGROUND = Color.DARK_GRAY;

	static final Color JMB_TEXT_COLOR = Color.WHITE;

	// STATUS BAR
	static final Color STATUS_BAR_TEXT_COLOR = Color.LIGHT_GRAY;

	// MENU BAR
	static final int MENU_ICON_SIZE = 40;

	// TOOL BAR
	static final Color ON_SELECTED_BUTTON_COLOR = new Color(150, 240, 100);
	static final Color ON_HOVER_BUTTON_COLOR = new Color(150, 200, 255);

	// LAYERS
	static final Dimension DEL_BUTTON_SIZE = new Dimension(HEIGHT, 20);
	static final Color ADD_LAYER_BUTTON = new Color(175, 230, 150);

	// ABOUT
	static final String ABOUT_MSG = "Pro Painter "
			+ PP_VERSION
			+ "\n\nFeatures:\n"
			+ "\n\t- Create a project"
			+ "\n\t- Edit your project throught the many tools"
			+ "\n\t- Manage your layers and drawings"
			+ "\n\t- Save your project"
			+ "\n\t- Export your project to JPG file"
			+ "\n\nThis program has been developped \nby Jean-Vital Durieu in late 2013 for DIT"
			+ "\n\nCopyright 2013";

}
