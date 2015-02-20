import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*****************************************************************
LoadFileBox creates and manages a custom JFileChooser for use by the
other parts of the GUI.

@author Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class LoadFileBox implements ActionListener {
	/**
	 * filechooser box.
	 */
	private JFileChooser fileChooser;
	
	/**
	 * Jdialog box for filechooser parameter.
	 */
	private JDialog popUp;
	
	/**
	 * String of the selected file path.
	 */
	private String selectedFile;

    /*****************************************************************
	Instantiates a JfileChooser and sets default directory
	to the user.home. Displays absolute path and allows user
	to make next operation.
    *****************************************************************/
	public LoadFileBox() {		
		
	fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	int result = fileChooser.showOpenDialog(popUp);
	
		if (result == JFileChooser.APPROVE_OPTION) {
			
			selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println(selectedFile);			
		}
		
		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel("Choose File: "));		
		
	}

    /*****************************************************************
    Instantiates a custom JfileChooser 
    @param create - boolean request for creation of LoadFileBox
    *****************************************************************/
	/**
	 * @param create a new project/save current project as new.
	 */
	public LoadFileBox(final boolean create) {		
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty(""
				+ "user.home")));
		
		int result = fileChooser.showOpenDialog(popUp);
		if (result == JFileChooser.APPROVE_OPTION) {
			
			/** gets absolute path of operators chosen file **/
			selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println(selectedFile);
		}
		
		JPanel messagePane = new JPanel();	
		messagePane.add(new JLabel("Choose File: "));


	}
    /*****************************************************************
    Returns absolute path of users desired file.
    @return selectedFile - String representation of absolute path
    *****************************************************************/
	public final String getFileSelected() {
		return selectedFile;
	}

	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	**************************************************************/
	public void actionPerformed(final ActionEvent e) {

	}
	
}
