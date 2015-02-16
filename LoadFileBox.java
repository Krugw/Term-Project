import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/*****************************************************************
LoadFileBox creates and manages a custom JFileChooser for use by the
other parts of the GUI

@authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class LoadFileBox implements ActionListener {
	private JFileChooser fileChooser;
	private JDialog popUp;
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
		popUp.setLocationRelativeTo(null);
	}

    /*****************************************************************
    Instantiates a custom JfileChooser 
    @param create - boolean request for creation of LoadFileBox
    *****************************************************************/
	public LoadFileBox(boolean create) {		
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
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
    Returns absolute path of users desired file
    @return selectedFile - String representation of absolute path
    *****************************************************************/
	public String getFileSelected() {
		return selectedFile;
	}

	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	**************************************************************/
	public void actionPerformed(ActionEvent e) {

	}
	
	/*****************************************************************
	main method, creates the instance of LoadFileBox()
	*****************************************************************/	
	public static void main(String[] args) {
		LoadFileBox LBF = new LoadFileBox();

	}
}
