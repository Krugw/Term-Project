import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LoadFileBox implements ActionListener{
	private JFileChooser fileChooser;
	private JDialog popUp;
	private File selectedFile;
	public LoadFileBox(){
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(popUp);
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
		}
		selectedFile = fileChooser.getSelectedFile();
		
		
		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel("Choose File: "));
		
	    //popUp.setVisible(true);
	}
	
	public File getFileSelected(){
		return selectedFile;
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	

}
