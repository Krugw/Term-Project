
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
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
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class AddActor extends JDialog implements ActionListener {
	 /****************************************************
     * Creates a panel for the JDialog box.
     ***************************************************/
    private JPanel panel;
    private JComboBox<String> ComboBox;

    /****************************************************
     * Creates a JDialog box.
     ***************************************************/
    private JDialog box;

    /****************************************************
     * Creates an OK button to go in panel.
     ***************************************************/
    private JButton addButton, deleteButton;
    private JRadioButton addActor,deleteActor;
    final int boxLength = 300;
    final int boxWidth = 100;
    private Boolean deleteFlag = false;
    /****************************************************
     * Creates a cancel button to go in panel.
     ***************************************************/
    private JButton cancelButton;
    private ArrayList<Actor> projActors;
    private ArrayList<String> ucActors;
    private Actor delActor = new Actor("","");
    private JLabel lblNewLabel;
    /**
     * @wbp.parser.constructor
     */
    public AddActor(final ArrayList<Actor> proActors){
    	deleteFlag = true;
        projActors = proActors;
        /*****************************************************************
         * Creation of JDialog box, centers location on pop-up.
         *****************************************************************/
        box = new JDialog();
        box.setModalityType(ModalityType.APPLICATION_MODAL);
        /*****************************************************************
         * Creates JPanel within JDialog box, adds applicable JButtons for
         * user operation and fields for user input.
         *****************************************************************/
        deleteButton = new JButton("");
        deleteButton.setIcon(new ImageIcon(AddActor.class.getResource("/resources/ok.png")));
        deleteButton.setBounds(135, 114, 80, 23);
        cancelButton = new JButton("");
        cancelButton.setIcon(new ImageIcon(AddActor.class.getResource("/resources/cancel.png")));
        cancelButton.setBounds(270, 114, 80, 23);

        panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(null);
     //   panel.setBackground(Color.CYAN);
        panel.add(deleteButton);
        panel.add(cancelButton);

        /*************************************************************
         * Dynamically sets fileNameTxt and box to visible, adds
         * actionlisteners to applicable jButtons.
         **************************************************************/
        box.getContentPane().add(panel);
        
        createComboBox(projActors,true);
        

        panel.add(ComboBox);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(AddActor.class.getResource("/resources/RemoveActor.png")));
        lblNewLabel.setBounds(0, 0, 471, 148);
        panel.add(lblNewLabel);
        ComboBox.setBounds(101, 53, 267, 50);
        
      //  createComboBox(projActors,true);
       // box.getContentPane().add(ComboBox,BorderLayout.SOUTH);
        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);
        box.setSize(487, 187);
        box.setVisible(true);
    }
    public AddActor(final ArrayList<Actor> proActors, final ArrayList<String> uActors) {
        ucActors = uActors;
        projActors = proActors;
        /*****************************************************************
         * Creation of JDialog box, centers location on pop-up.
         *****************************************************************/
        box = new JDialog();
        box.setModalityType(ModalityType.APPLICATION_MODAL);
        /*****************************************************************
         * Creates JPanel within JDialog box, adds applicable JButtons for
         * user operation and fields for user input.
         *****************************************************************/
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
         * Dynamically sets fileNameTxt and box to visible, adds
         * actionlisteners to applicable jButtons.
         **************************************************************/
        box.getContentPane().add(panel);
        deleteButton.setVisible(false);
        createComboBox(projActors,true);
        box.getContentPane().add(ComboBox,BorderLayout.SOUTH); //ADD ACTOR INSIDE YO
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);
        box.setSize(boxLength, boxWidth);
        box.setVisible(true);

    }
    public void createComboBox(ArrayList<Actor> Actors, boolean flag) {
    	
    	        Vector<String> actors = new Vector<String>();
    	        if(!Actors.isEmpty()) {
    	        	if(ucActors == null || ucActors.isEmpty()){
    	            for (int i = 0; i < Actors.size(); i++) {
    	                String id = Actors.get(i).getName();
    	                actors.add(id);
    	            }
    	        	}else{
    	        	for (int i = 0; i < Actors.size(); i++) {
    	        			Boolean check = true;
    	        			String id = Actors.get(i).getName();
        	                for(String S: ucActors){
        	                	if(S.equals(id))
        	                		check = false;
        	                }
        	               if(check)
        	                actors.add(id);
    	        		}
    	        	}
    	       }
    	        ActorBox myModel = new ActorBox(actors);
    	        if(flag)
    	        ComboBox = new JComboBox<String>(myModel);
    	        
    	        ComboBox.setModel(myModel);
    	    }
    
    public void createComboBoxString(ArrayList<String> Actors) {
    	
        Vector<String> actors = new Vector<String>();
        if(!Actors.isEmpty()) {
            for (int i = 0; i < Actors.size(); i++) {
                String id = Actors.get(i);
                actors.add(id);
            }
       }
        ActorBox myModel = new ActorBox(actors);
        ComboBox.setModel(myModel);
    }
    public ArrayList<String> getucActorlist(){
		return ucActors;
    }
    public Actor getdeleteActor(){
		return delActor;
    }
	@Override
	public final void actionPerformed(final ActionEvent e) {
        if (e.getSource() == addButton) {
        	if(!ucActors.contains((String) ComboBox.getSelectedItem()) && ComboBox.getSelectedItem() != null && ((String) ComboBox.getSelectedItem()).length()>1)
        	ucActors.add((String) ComboBox.getSelectedItem());
        	createComboBox(projActors,false);
        }
        if (e.getSource() == deleteButton) {
        	if(!deleteFlag){
        	ucActors.remove((String) ComboBox.getSelectedItem());
        	createComboBoxString(ucActors);
        	}else{
        		for(Actor a: projActors){
        		if(a.getName().equals(ComboBox.getSelectedItem()))
        		delActor = a;
        		}
        		box.dispose();
        	}
        }
        if("addActor".equals(e.getActionCommand())){
        	createComboBox(projActors,false);
        	deleteButton.setVisible(false);
        	addButton.setVisible(true);
        }
        if("deleteActor".equals(e.getActionCommand())){
        	createComboBoxString(ucActors);
        	addButton.setVisible(false);
        	deleteButton.setVisible(true);
        }
        if (e.getSource() == cancelButton) {
            box.dispose();
        }
    }
	public void close(){
		box.dispose();
	}
}
