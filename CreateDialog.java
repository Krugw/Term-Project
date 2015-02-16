import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*****************************************************************
CreateDialog handles several creation operations for the UseCase
program, including naming, confirming the name is valid, and giving 
the operator a chance to leave the program entirely if they choose.

@authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class CreateDialog implements ActionListener{
	private JPanel panel;
	private JDialog box;
	private JLabel label;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField fileNameTxt;
	private String directory;
	
	public CreateDialog(String s){
		
		/*****************************************************************
		Creation of JDialog box, centers location on pop-up.
		*****************************************************************/	
		box = new JDialog();		
		box.setLocationRelativeTo(null);
		box.setModal(true); 
		
		/*****************************************************************
		Creates JPanel within JDialog box, adds applicable JButtons for
		user operation and fields for user input.
		*****************************************************************/	
		label = new JLabel("File Name:");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		fileNameTxt = new JTextField(20);
		fileNameTxt.setText(s);		
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());		
		panel.add(label);
		panel.add(okButton);
		panel.add(cancelButton);
		panel.add(fileNameTxt);
		
		/*****************************************************************
		Dynamically sets fileNameTxt and box to visible, adds actionlisteners
		to applicable jbuttons.
		*****************************************************************/			
		fileNameTxt.setVisible(true);;
		box.add(panel);		

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		box.setSize(300,200);
		box.setVisible(true);
		
	}
	
	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	 **************************************************************/
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == okButton){
			
			if(!fileNameTxt.getText().equals("")){
				
				LoadFileBox lfb = new LoadFileBox(true);
				directory = lfb.getFileSelected();
				System.out.println(fileNameTxt.getText());
				box.dispose();				
			}
			
			else{
				JOptionPane.showMessageDialog(null,
				"Please Name Your Project\n", "Name Error",  //contents of the box
				JOptionPane.INFORMATION_MESSAGE);				
			}
		}
		
		if(e.getSource() == cancelButton){
			box.dispose();
		}
	}
	
	/*****************************************************************
	 Returns name of file currently located in fileNameTxt
	 @return String representation of fileNameTxt
	*****************************************************************/	
	public String getFileName(){
		return fileNameTxt.getText();
	}
	
	/*****************************************************************
	 Returns current absolute directory being accessed
	 @return String 
	*****************************************************************/	
	public String getDirectory(){
		return directory;
	}
	
	/*****************************************************************
	main method, creates the instance of CreateDialog
	*****************************************************************/	
	public static void main(String[] args){
		CreateDialog cd = new CreateDialog("");
	}
	
}
