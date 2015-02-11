import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class UseCaseEditor extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextPane nameTxt;
	private JTextPane IDTxt;
	private JTextPane descriptionTxt;
	private JTextPane primaryActorTxt;
	private JTextPane supportingActorTxt;
	private JTextPane triggersTxt;
	private JTextPane preconditionsTxt;
	private JTextPane primaryFlowTxt;
	private JTextPane alternateFlowTxt;
	private JTextPane minimalGuaranteeTxt;
	private JTextPane successGuaranteeTxt;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private int closeStatus = 0;
	
	public static final int OK = 0;
	public static final int CANCEL = 1;
	
	private UseCase uc = new UseCase();
	public UseCase getUC() {
		uc.setName(nameTxt.getText());
		uc.setID(IDTxt.getText());
		uc.setDescription(descriptionTxt.getText());
		uc.setPrimaryActors(primaryActorTxt.getText());
		uc.setSupportingActors(supportingActorTxt.getText());
		uc.setTriggers(triggersTxt.getText());
		uc.setPreconditions(preconditionsTxt.getText());
		uc.setAlternativeflow(alternateFlowTxt.getText());
		uc.setMinimalGuarantees(minimalGuaranteeTxt.getText());
		uc.setSuccessGuarantees(successGuaranteeTxt.getText());
		return uc;
	}
	public int getclosestatus() {
		return closeStatus;
	}
	public UseCaseEditor(){
		setTitle("Use Case Editor");
		closeStatus = CANCEL;
		setSize(400,200);
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(12,2));
		
		/**Sets up the textfields with default values**/
		textPanel.add(new JLabel("Name: "));
		nameTxt = new JTextPane();
		JScrollPane sp = new JScrollPane(nameTxt);
		textPanel.add(sp);		
		
		textPanel.add(new JLabel("ID: "));
		IDTxt = new JTextPane();
		sp = new JScrollPane(IDTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Description: "));
		descriptionTxt = new JTextPane();
		sp = new JScrollPane(descriptionTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Primary Actor: "));
		primaryActorTxt = new JTextPane();
		sp = new JScrollPane(primaryActorTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Supporting Actor: "));
		supportingActorTxt = new JTextPane();
		sp = new JScrollPane(supportingActorTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Triggers: "));
		triggersTxt = new JTextPane();
		sp = new JScrollPane(triggersTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Preconditions: "));
		preconditionsTxt = new JTextPane();
		sp = new JScrollPane(preconditionsTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Primary Flow: "));
		primaryFlowTxt = new JTextPane();
		sp = new JScrollPane(primaryFlowTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Alternate Flow: "));
		alternateFlowTxt = new JTextPane();
		sp = new JScrollPane(alternateFlowTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Minimal Guarentees"));
		minimalGuaranteeTxt = new JTextPane();
		sp = new JScrollPane(minimalGuaranteeTxt);
		textPanel.add(sp);
		
		textPanel.add(new JLabel("Success Guarantees"));
		successGuaranteeTxt = new JTextPane();
		sp = new JScrollPane(successGuaranteeTxt);
		textPanel.add(sp);
		
		getContentPane().add(textPanel, BorderLayout.CENTER);
		
		okButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		textPanel.add(okButton);
		textPanel.add(cancelButton);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		setSize(800,700);
		setVisible (true);	
	}
	public void setUC(UseCase usecase) {
		nameTxt.setText(usecase.getName());
		IDTxt.setText(usecase.getID());
		descriptionTxt.setText(usecase.getDescription());
		primaryActorTxt.setText(usecase.getPrimaryActors());
		supportingActorTxt.setText(usecase.getSupportingActors());
		triggersTxt.setText(usecase.getTriggers());
		preconditionsTxt.setText(usecase.getPreconditions());
		alternateFlowTxt.setText(usecase.getAlternativeflow());
		minimalGuaranteeTxt.setText(usecase.getMinimalGuaruntees());
		successGuaranteeTxt.setText(usecase.getSuccessGuarantees());
	};
	
	public void addSaveListener(ActionListener listener) {
		  okButton.addActionListener(listener);
	}
	
	public void actionPerformed(ActionEvent e){
		
		
		if(e.getSource() == okButton){
			closeStatus = OK;
			
			try{
				dispose();
			}catch(Exception e1){
				closeStatus = CANCEL;
				e1.printStackTrace();
			}
		}
		
		/**Sets close status to 1 and exits the pop-up box**/
		if(e.getSource() == cancelButton){
			closeStatus = CANCEL;
			dispose();
		}
		
	}
}
