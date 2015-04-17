import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateActorDialog implements ActionListener {
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
    private JTextField actorName,actorDescription;
    private JLabel lblNewLabel;
    private String saveName = "",saveDes = "";

    public CreateActorDialog(String name, String Description) {
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
        okButton = new JButton("");
        okButton.setIcon(new ImageIcon(CreateActorDialog.class.getResource("/resources/ok.png")));
        okButton.setBounds(163, 147, 80, 23);
        cancelButton = new JButton("");
        cancelButton.setIcon(new ImageIcon(CreateActorDialog.class.getResource("/resources/cancel.png")));
        cancelButton.setBounds(253, 147, 81, 23);
        actorName = new JTextField(textfieldSize);
        actorName.setText(name);
        actorName.setBorder(null);
        actorName.setBounds(106, 28, 263, 32);
        actorName.setOpaque(false);
        actorDescription = new JTextField(textfieldSize);
        actorDescription.setText(Description);
        actorDescription.setBorder(null);
        actorDescription.setBounds(106, 92, 263, 44);
        actorDescription.setOpaque(false);

        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(actorName);
        panel.add(actorDescription);

        /*************************************************************
         * Dynamically sets fileNameTxt and box to visible, adds
         * actionlisteners to applicable jButtons.
         **************************************************************/
        actorName.setVisible(true);
        box.getContentPane().add(panel);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(CreateActorDialog.class.getResource("/resources/AddActorPage.png")));
        lblNewLabel.setBounds(0, -16, 468, 203);
        panel.add(lblNewLabel);
        /*label = new JLabel("File Name:");
        URL boximage = GUI.class.getResource(
                "/resources/filebox.png");

        label.setIcon(new ImageIcon(boximage));
        label.setForeground(Color.WHITE);
        label.setBounds(0, 0, 284, 161);
        panel.add(label); */

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);

        box.setSize(484, 209);
        box.setVisible(true);

    }

	public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == okButton) {
            if (!actorName.getText().equals("")) {
            	saveName = actorName.getText();
            	saveDes = actorDescription.getText();
                box.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Please Name Your Actor\n", "Name Error",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == cancelButton) {
            box.dispose();
        }
    }
	 public final String getActorName() {
	        return saveName;
	    }
	 public final String getActorDescription() {
	        return saveDes;
	    }
}
