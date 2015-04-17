import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * @author Owner
 *
 */
public class AddActor extends JDialog implements ActionListener {
	/****************************************************
	 * Creates a panel for the JDialog box.
	 ***************************************************/
	private JPanel panel;
	/**
	 * combobox.
	 */
	private JComboBox<String> comboBox;

	/****************************************************
	 * Creates a JDialog box.
	 ***************************************************/
	private JDialog box;

	/****************************************************
	 * Creates an OK button to go in panel.
	 ***************************************************/
	private JButton addButton, deleteButton;
	/**
	 * del and add actor.
	 */
	private JRadioButton addActor, deleteActor;
	/**
	 * size.
	 */
	private final int boxLength = 300;
	/**
	 * size.
	 */
	private final int boxWidth = 100;
	/**
	 * flags.
	 */
	private Boolean deleteFlag = false, editFlag = false;
	/****************************************************
	 * Creates a cancel button to go in panel.
	 ***************************************************/
	private JButton cancelButton;
	/**
	 * proj actors.
	 */
	private ArrayList<Actor> projActors;
	/**
	 * uc actors.
	 */
	private ArrayList<String> ucActors;
	/**
	 * delactor.
	 */
	private Actor delActor = new Actor("", "");
	/**
	 * newlabel.
	 */
	private JLabel lblNewLabel;
	/**
	 * @param proActors is project actors
	 * @param Flag is flag
	 */
	public AddActor(final ArrayList<Actor> proActors, final Boolean Flag) {
		deleteFlag = true;
		editFlag = Flag;
		projActors = proActors;
		/**************************************************************
		 * Creation of JDialog box, centers location on pop-up.
		 **************************************************************/
		box = new JDialog();
		box.setModalityType(ModalityType.APPLICATION_MODAL);
		/**************************************************************
		 * Creates JPanel within JDialog box,
		 * * adds applicable JButtons for user
		 * operation and fields for user input.
		 **************************************************************/
		deleteButton = new JButton("");
		if (!editFlag) {
			deleteButton.setIcon(new ImageIcon(AddActor.class
					.getResource("/resources/ok.png")));
		}else{
			deleteButton.setIcon(new ImageIcon(AddActor.class
					.getResource("/resources/edit.png")));
		}
		deleteButton.setBounds(135, 114, 80, 23);
		cancelButton = new JButton("");
		cancelButton.setIcon(new ImageIcon(AddActor.class
				.getResource("/resources/cancel.png")));
		cancelButton.setBounds(270, 114, 80, 23);

		panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setLayout(null);
		// panel.setBackground(Color.CYAN);
		panel.add(deleteButton);
		panel.add(cancelButton);

		/*************************************************************
		 * Dynamically sets fileNameTxt and
		 * box to visible, adds actionlisteners
		 * to applicable jButtons.
		 **************************************************************/
		box.getContentPane().add(panel);

		createComboBox(projActors, true);

		panel.add(comboBox);

		lblNewLabel = new JLabel("");
		if(editFlag){
		lblNewLabel.setIcon(new ImageIcon(AddActor.class
				.getResource("/resources/editActor.png")));
			
		}else{
		lblNewLabel.setIcon(new ImageIcon(AddActor.class
				.getResource("/resources/RemoveActor.png")));
		}
		lblNewLabel.setBounds(0, 0, 471, 148);
		panel.add(lblNewLabel);
		comboBox.setBounds(101, 53, 267, 50);

		// createComboBox(projActors,true);
		// box.getContentPane().add(ComboBox,BorderLayout.SOUTH);
		deleteButton.addActionListener(this);
		cancelButton.addActionListener(this);
		box.setSize(487, 187);
		box.setVisible(true);
	}

	/**
	 * @param proActors is projactors
	 * @param uActors is u actors
	 */
	public AddActor(final ArrayList<Actor> proActors,
			final ArrayList<String> uActors) {
		ucActors = uActors;
		projActors = proActors;
		/**************************************************************
		 * Creation of JDialog box, centers location on pop-up.
		 *************************************************************/
		box = new JDialog();
		box.setModalityType(ModalityType.APPLICATION_MODAL);
		/*************************************************************
		 * Creates JPanel within JDialog box,
		 * adds applicable JButtons for user
		 * operation and fields for user input.
		 *************************************************************/
		deleteActor = new JRadioButton("Delete");
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		addActor = new JRadioButton("Add");

		addActor.setActionCommand("addActor");
		deleteActor.setActionCommand("deleteActor");
		addActor.addActionListener(this);
		deleteActor.addActionListener(this);
		addActor.setSelected(true);

		cancelButton = new JButton("Close");

		ButtonGroup group = new ButtonGroup();
		group.add(deleteActor);
		group.add(addActor);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.add(addActor);
		panel.add(deleteActor);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(cancelButton);

		/*************************************************************
		 * Dynamically sets fileNameTxt and box to visible,
		 * adds actionlisteners
		 * to applicable jButtons.
		 **************************************************************/
		box.getContentPane().add(panel);
		deleteButton.setVisible(false);
		createComboBox(projActors, true);
		box.getContentPane().add(comboBox, BorderLayout.SOUTH);
		// ADD ACTOR
		// INSIDE YO
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		cancelButton.addActionListener(this);
		box.setSize(boxLength, boxWidth);
		box.setVisible(true);

	}

	/**
	 * @param aCTors is actors
	 * @param flag is flag
	 */
	public final void createComboBox(final ArrayList<Actor> aCTors,
			final boolean flag) {

		Vector<String> actors = new Vector<String>();
		if (!aCTors.isEmpty()) {
			if (ucActors == null || ucActors.isEmpty()) {
				for (int i = 0; i < aCTors.size(); i++) {
					String id = aCTors.get(i).getName();
					actors.add(id);
				}
			} else {
				for (int i = 0; i < aCTors.size(); i++) {
					Boolean check = true;
					String id = aCTors.get(i).getName();
					for (String aS : ucActors) {
						if (aS.equals(id)) {
							check = false;
						}
					}
					if (check) {
						actors.add(id);
					}
				}
			}
		}
		ActorBox myModel = new ActorBox(actors);
		if (flag) {
			comboBox = new JComboBox<String>(myModel);
		}

		comboBox.setModel(myModel);
	}

	/**
	 * @param Actors
	 */
	public final void createComboBoxString(final ArrayList<String> Actors) {

		Vector<String> actors = new Vector<String>();
		if (!Actors.isEmpty()) {
			for (int i = 0; i < Actors.size(); i++) {
				String id = Actors.get(i);
				actors.add(id);
			}
		}
		ActorBox myModel = new ActorBox(actors);
		comboBox.setModel(myModel);
	}

	/**
	 * @return
	 */
	public final ArrayList<String> getucActorlist() {
		return ucActors;
	}

	/**
	 * @return
	 */
	public final Actor getdeleteActor() {
		return delActor;
	}

	/**
	 *
	 */
	public final void actionPerformed(final ActionEvent e) {
		if (e.getSource() == addButton) {
if (!ucActors.contains((String) comboBox.getSelectedItem())
			&& comboBox.getSelectedItem() != null
			&& ((String) comboBox.getSelectedItem()).length() > 1) {
			ucActors.add((String) comboBox.getSelectedItem());
		}
			createComboBox(projActors, false);
		}
		if (e.getSource() == deleteButton) {
			if (!deleteFlag) {
ucActors.remove((String) comboBox.getSelectedItem());
				createComboBoxString(ucActors);
			} else {
				for (Actor a : projActors) {
if (a.getName().equals(comboBox.getSelectedItem())) {
						delActor = a;
					}
					box.dispose();
				}
			}
		}
		if ("addActor".equals(e.getActionCommand())) {
			createComboBox(projActors, false);
			deleteButton.setVisible(false);
			addButton.setVisible(true);
		}
		if ("deleteActor".equals(e.getActionCommand())) {
			createComboBoxString(ucActors);
			addButton.setVisible(false);
			deleteButton.setVisible(true);
		}
		if (e.getSource() == cancelButton) {
			box.dispose();
		}
	}

	/**
	 *
	 */
	public final void close() {
		box.dispose();
	}
}
