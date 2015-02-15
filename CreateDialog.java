import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateDialog implements ActionListener{
	private JPanel panel;
	private JDialog box;
	private JLabel label;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField fileNameTxt;
	private String directory;
	
	public CreateDialog(String s){
		box = new JDialog();
		label = new JLabel("File Name:");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		fileNameTxt = new JTextField(20);
		fileNameTxt.setText(s);
		box.setModal(true); 
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		panel.add(label);
		panel.add(okButton);
		panel.add(cancelButton);
		panel.add(fileNameTxt);
		
		fileNameTxt.setVisible(true);;
		box.add(panel);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		box.setSize(300,200);
		box.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == okButton){
			if(!fileNameTxt.getText().equals("")){
			LoadFileBox lfb = new LoadFileBox(true);
			directory = lfb.getFileSelected();
			System.out.println(fileNameTxt.getText());
			box.dispose();
			}else{
				JOptionPane.showMessageDialog(null,
						"Please Name Your Project\n", "Name Error",
						JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
		
		if(e.getSource() == cancelButton){
			box.dispose();
		}
	}
	
	public String getFileName(){
		return fileNameTxt.getText();
	}
	
	public String getDirectory(){
		return directory;
	}
	
	public static void main(String[] args){
		CreateDialog cd = new CreateDialog("");
	}
	
}
