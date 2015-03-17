import java.import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.net.URL;


/*****************************************************************
 CreateDialog handles several creation operations for the UseCase
 program, including naming, confirming the name is valid, and giving
 the operator a chance to leave the program entirely if they choose.

 @author Wesley Krug, Gabriel Steponovich,
 Michael Brecker, Halston Raddatz
 @version Winter 2015
 *****************************************************************/
public class CreateDialog implements ActionListener {
    /****************************************************
     * Creates a panel for the JDialog box.
     ***************************************************/
    private JPanel panel;

    /****************************************************
     * Creates a JDialog box.
     ***************************************************/
    private JDialog box;

    /****************************************************
     * Creates a label to go in panel.
     ***************************************************/
    private JLabel label;

    /****************************************************
     * Creates an OK button to go in panel.
     ***************************************************/
    private JButton okButton;

    /****************************************************
     * Creates a cancel button to go in panel.
     ***************************************************/
    private JButton cancelButton;

    /****************************************************
     * Creates a text field to write desired file name.
     ***************************************************/
    private JTextField fileNameTxt;

    /****************************************************
     * Creates a string for the directory path.
     ***************************************************/
    private String directory;

    /**
     * This class creates a small dialog box for the user to write their
     * desired name for the file they are creating/saving.
     *
     * @param s is the default text in the file name text field.
     */
    public CreateDialog(final String s) {

        final int textfieldSize = 20;
        final int boxLength = 300;
        final int boxWidth = 200;
        /*****************************************************************
         * Creation of JDialog box, centers location on pop-up.
         *****************************************************************/
        box = new JDialog();
        box.setLocationRelativeTo(null);
        box.setModal(true);

        /*****************************************************************
         * Creates JPanel within JDialog box, adds applicable JButtons for
         * user operation and fields for user input.
         *****************************************************************/
        okButton = new JButton("OK");
        okButton.setBounds(62, 116, 80, 23);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(147, 116, 81, 23);
        fileNameTxt = new JTextField(textfieldSize);
        fileNameTxt.setOpaque(false);
        fileNameTxt.setBounds(49, 67, 196, 38);
        fileNameTxt.setText(s);

        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(fileNameTxt);

        /*************************************************************
         * Dynamically sets fileNameTxt and box to visible, adds
         * actionlisteners to applicable jButtons.
         **************************************************************/
        fileNameTxt.setVisible(true);
        box.getContentPane().add(panel);
        label = new JLabel("File Name:");
        URL boximage = GUI.class.getResource(
                "/resources/filebox.png");

        label.setIcon(new ImageIcon(boximage));
        label.setForeground(Color.WHITE);
        label.setBounds(0, 0, 284, 161);
        panel.add(label);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        box.setSize(boxLength, boxWidth);
        box.setVisible(true);

    }

    /**************************************************************
     Manages the action listeners that are currently connected to
     GUI objects.
     @param e the event
     **************************************************************/
    public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == okButton) {

            if (!fileNameTxt.getText().equals("")) {
                LoadFileBox lfb = new LoadFileBox(true);
                directory = lfb.getFileSelected();
                box.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please Name Your Project\n", "Name Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == cancelButton) {
            box.dispose();
        }
    }

    /*****************************************************************
     * Returns name of file currently located in fileNameTxt.
     *
     * @return String representation of fileNameTxt
     *****************************************************************/
    public final String getFileName() {
        return fileNameTxt.getText();
    }

    /*****************************************************************
     Returns current absolute directory being accessed.

     @return directory which is the file selected
     *****************************************************************/
    public final String getDirectory() {
        return directory;
    }

}
awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.net.URL;


/*****************************************************************
CreateDialog handles several creation operations for the UseCase
program, including naming, confirming the name is valid, and giving 
the operator a chance to leave the program entirely if they choose.

@author Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class CreateDialog implements ActionListener {
	/****************************************************
	 * Creates a panel for the JDialog box.
	 ***************************************************/
	private JPanel panel;
	
	/****************************************************
	 * Creates a JDialog box.
	 ***************************************************/
	private JDialog box;
	
	/****************************************************
	 * Creates a label to go in panel.
	 ***************************************************/
	private JLabel label;
	
	/****************************************************
	 * Creates an OK button to go in panel.
	 ***************************************************/
	private JButton okButton;
	
	/****************************************************
	 * Creates a cancel button to go in panel.
	 ***************************************************/
	private JButton cancelButton;
	
	/****************************************************
	 * Creates a text field to write desired file name.
	 ***************************************************/
	private JTextField fileNameTxt;
	
	/****************************************************
	 * Creates a string for the directory path.
	 ***************************************************/
	private String directory;
	
	/**
	 * This class creates a small dialog box for the user to write their
	 * desired name for the file they are creating/saving.
	 * 
	 * @param s is the default text in the file name text field.
	 */
	public CreateDialog(final String s) {
		
		final int textfieldSize = 20;
		final int boxLength = 300;
		final int boxWidth = 200;
		/*****************************************************************
		* Creation of JDialog box, centers location on pop-up.
		*****************************************************************/	
		box = new JDialog();		
		box.setLocationRelativeTo(null);
		box.setModal(true); 
		
		/*****************************************************************
		* Creates JPanel within JDialog box, adds applicable JButtons for
		* user operation and fields for user input.
		*****************************************************************/	
		okButton = new JButton("OK");
		okButton.setBounds(62, 116, 80, 23);
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(147, 116, 81, 23);
		fileNameTxt = new JTextField(textfieldSize);
		fileNameTxt.setOpaque(false);
		fileNameTxt.setBounds(49, 67, 196, 38);
		fileNameTxt.setText(s);		
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.add(okButton);
		panel.add(cancelButton);
		panel.add(fileNameTxt);
		
		/*************************************************************
		* Dynamically sets fileNameTxt and box to visible, adds 
		* actionlisteners to applicable jButtons.
		**************************************************************/			
		fileNameTxt.setVisible(true);
		box.getContentPane().add(panel);
		label = new JLabel("File Name:");
		URL boximage = GUI.class.getResource(
		"/resources/filebox.png");
		
		label.setIcon(new ImageIcon(boximage));
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, 284, 161);
		panel.add(label);

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		box.setSize(boxLength, boxWidth);
		box.setVisible(true);
		
	}
	
	/**************************************************************
	 Manages the action listeners that are currently connected to
	 GUI objects.
	 @param e the event
	 **************************************************************/
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == okButton) {
			
			if (!fileNameTxt.getText().equals("")) {
				LoadFileBox lfb = new LoadFileBox(true);
				directory = lfb.getFileSelected();
				System.out.println(fileNameTxt.getText());
				box.dispose();	
			} else {
				JOptionPane.showMessageDialog(null,
				"Please Name Your Project\n", "Name Error", 
				JOptionPane.INFORMATION_MESSAGE);				
			}
		}
		
		if (e.getSource() == cancelButton) {
			box.dispose();
		}
	}
	
	/*****************************************************************
	 * Returns name of file currently located in fileNameTxt.
	 *
	 * @return String representation of fileNameTxt
	*****************************************************************/	
	public final String getFileName() {
		return fileNameTxt.getText();
	}
	
	/*****************************************************************
	 Returns current absolute directory being accessed.
	 
	 @return directory which is the file selected
	*****************************************************************/	
	public final String getDirectory() {
		return directory;
	}
	
}
