import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.List;

import javax.swing.JScrollPane;

import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/*****************************************************************
 UseCaseEditor creates a manages a custom JDialog window for use in
 accepting and creating instances of UseCase. In its current form
 it relies heavily on both Usecase.java and extends the default
 JDialog class.
 
 @author Wesley Krug, Gabriel Steponovich,
 Michael Brecker, Halston Raddatz
 @version Winter 2015
 *****************************************************************/
public class UseCaseEditor extends JDialog implements ActionListener {

    /**
     * Serial Version of our program.
     **/
    private static final long serialVersionUID = 1L;
    
    /**
     * JTextPane for nameTxt field of the usecase to be created.
     */
    private JTextPane nameTxt;

    /**
     * JTextPane for idTxt field of the usecase to be created.
     */
    private JTextPane idTxt;

    /**
     * JTextPane for descriptionTxt field of the usecase to be created.
     */
    private JTextPane descriptionTxt;

    /**
     * JTextPane for primaryActorTxt field of the usecase to be created.
     */
    private JTextPane primaryActorTxt;

    /**
     * JTextPane for supportingActorTxt field of the usecase to be created.
     */
    private JTextPane supportingActorTxt;

    /**
     * JTextPane for triggersActorTxt field of the usecase to be created.
     */
    private JTextPane triggersTxt;

    /**
     * JTextPane for preconditionsTxt field of the usecase to be created.
     */
    private JTextPane preconditionsTxt;

    /**
     * JTextPane for primaryFlowTxt field of the usecase to be created.
     */
    private JTextPane primaryFlowTxt;

    /**
     * JTextPane for alternateFlowTxt field of the usecase to be created.
     */
    private JTextPane alternateFlowTxt;

    /**
     * JTextPane for minimalGuaranteeTxt field of the usecase to be created.
     */
    private JTextPane minimalGuaranteeTxt;

    /**
     * JTextPane for successGuaranteeTxt field of the usecase to be created.
     */
    private JTextPane successGuaranteeTxt;

    /**
     * JButton for the OK function which creates a new usecase.
     */
    private JButton okButton;

    /**
     * JButton to cancel all actions close the Editor window.
     */
    private JButton cancelButton;

    /**
     * The new usecase to be created.
     */
    private UseCase uc = new UseCase(),uco = new UseCase();
    
    private ArrayList<Actor> Actors = new ArrayList<Actor>(), Actorso = new ArrayList<Actor>();
    /**
     * Width of the useCaseEditor JPanel.
     */
    public static final int EDITOR_WIDTH = 1190;

    /**
     * Heigth of the useCaseEditor JPanel.
     */
    public static final int EDITOR_HEIGHT = 719;

    /**
     * The Layout for the TextPanels.
     */
    public static final int TEXT_PANEL_LAYOUT = 12;

    /**
     * Width of the usecase.
     */
    public static final int USE_CASE_WIDTH = 1190;

    /**
     * Heigh of the usecase.
     */
    public static final int USE_CASE_HEIGHT = 719;
    public AddActor ActorDialog;

    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane1;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane2;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane3;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane4;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane5;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane6;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane7;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane8;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane9;
    
    /**
     * Scrollpane.
     */
    private JScrollPane scrollPane10;
    private ArrayList<Actor> proActors;
    private ArrayList<String> pA = new ArrayList<String>(),sA= new ArrayList<String>();
    private Boolean Flag = true, cancelFlag = true;
    private JButton addactor1;
    private JButton addactor2;
    private JButton addstep1;
    private JButton addstep2;
    
    /*****************************************************************
     Uses .getText() to transfer current values of JTextPanes into their
     associated parameters within the UseCase object.
     @return UseCase The usecase to returned.
     *****************************************************************/
    public final UseCase getUC() {
    	if(!cancelFlag){
        uc.setName(nameTxt.getText());
        uc.setID(idTxt.getText());
        uc.setDescription(descriptionTxt.getText());
        uc.setTriggers(triggersTxt.getText());
        uc.setPreconditions(preconditionsTxt.getText());
        uc.setPrimaryflow(primaryFlowTxt.getText());
        uc.setAlternativeflow(alternateFlowTxt.getText());
        uc.setMinimalGuarantees(minimalGuaranteeTxt.getText());
        uc.setSuccessGuarantees(successGuaranteeTxt.getText());
        return uc;
    	}else{
    		System.out.println(uco.getPrimString());
    		uco.setPrimaryActor(pA);
    		uco.setSupportingActor(sA);
    		System.out.println(uco.getPrimString());
    		return uco;
    	}
    }
    public final ArrayList<Actor> getActors(){
    	for(String s: uc.getPrimaryActors()){
    		for(Actor A: Actors){
    			if(A.getName().equals(s))
    				A.addUsecase(uc);
    		}
    	}
    	for(String s: uc.getSupportingActors()){
    		for(Actor A: Actors){
    			if(A.getName().equals(s))
    				A.addUsecase(uc);
    		}
    	}
    	return Actors;
    }
    public final void addActorUC(Actor A){
    	uc.addPrimaryActor(A.getName());
    	A.addUsecase(uc);
    }
    public final void removeActorUC(Actor A){
    	A.removeUseCase(uc);
    }
    /*****************************************************************
     Instantiates a custom dialog box and adds applicable jTextFields,
     jButtons, and JComboBoxes. Sets box to modal as well as centering
     it on click.
     *****************************************************************/
    public UseCaseEditor(final ArrayList<Actor> pActors) {
        setVisible(false);
    	setModalityType(ModalityType.APPLICATION_MODAL);
        proActors = pActors;
    	setResizable(false);
        setTitle("EDITING");
        setSize(1200, 730);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(null);
        getContentPane().add(textPanel, BorderLayout.CENTER);
                
		URL boximage = GUI.class.getResource("/resources/editmode.png");
		URL checkmark = GUI.class.getResource("/resources/checkmark.png");
		URL xmark = GUI.class.getResource("/resources/xmark.png");
		
        okButton = new JButton("");
        okButton.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/saveusecase.png")));
        okButton.setOpaque(false);
        okButton.setBounds(476, 657, 110, 23);
        cancelButton = new JButton("");
        cancelButton.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/cancel2.png")));
        cancelButton.setOpaque(false);
        cancelButton.setBounds(607, 657, 110, 23);
        
        addstep1 = new JButton("");
        addstep1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the first add step button");
        	}
        });
        addstep1.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/addstep.png")));
        addstep1.setBounds(495, 367, 89, 23);
        textPanel.add(addstep1);
        
        addactor2 = new JButton("");
        addactor2.addActionListener(this);
        addactor2.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/addactor.png")));
        addactor2.setBounds(253, 367, 89, 23);
        textPanel.add(addactor2);
        
        addactor1 = new JButton("");
        addactor1.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/addactor.png")));
        addactor1.addActionListener(this);
        addactor1.setBounds(20, 367, 89, 23);
        textPanel.add(addactor1);
        textPanel.add(okButton);
        textPanel.add(cancelButton);
        
        JScrollPane scrollPane = new JScrollPane(); 
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        scrollPane3 = new JScrollPane();
        scrollPane3.setOpaque(false);
        scrollPane3.setBounds(10, 149, 207, 207);
        scrollPane3.setBorder(null);
        textPanel.add(scrollPane3);
        
        primaryActorTxt = new JTextPane();
        scrollPane3.setViewportView(primaryActorTxt);
        primaryActorTxt.setForeground(Color.WHITE);
        primaryActorTxt.setEditable(false);
        primaryActorTxt.setBorder(null);
        primaryActorTxt.setOpaque(false);
        
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setBounds(348, 60, 318, 40);
        textPanel.add(scrollPane1);
        idTxt = new JTextPane();
        scrollPane1.setViewportView(idTxt);
        idTxt.setForeground(Color.WHITE);
        idTxt.setBorder(null);
        idTxt.setOpaque(false);
        scrollPane.setBounds(10, 56, 318, 44);
        textPanel.add(scrollPane);
        nameTxt = new JTextPane();
        scrollPane.setViewportView(nameTxt);
        nameTxt.setOpaque(false);
        nameTxt.setBackground(Color.GRAY);
        nameTxt.setForeground(Color.WHITE);
        nameTxt.setBorder(null);
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setOpaque(false);
        scrollPane2.setBounds(687, 60, 477, 40);
        textPanel.add(scrollPane2);
        descriptionTxt = new JTextPane();
        scrollPane2.setViewportView(descriptionTxt);
        descriptionTxt.setForeground(Color.WHITE);
        descriptionTxt.setBorder(null);
        descriptionTxt.setOpaque(false);
        scrollPane4 = new JScrollPane();
        scrollPane4.setOpaque(false);
        scrollPane4.setBounds(243, 149, 213, 207);
        textPanel.add(scrollPane4);
        
        supportingActorTxt = new JTextPane();
        scrollPane4.setViewportView(supportingActorTxt);
        supportingActorTxt.setForeground(Color.WHITE);
        supportingActorTxt.setEditable(false);
        supportingActorTxt.setBorder(null);
        supportingActorTxt.setOpaque(false);
        scrollPane5 = new JScrollPane();
        scrollPane5.setOpaque(false);
        scrollPane5.setBounds(10, 440, 268, 198);
        textPanel.add(scrollPane5);
        
        triggersTxt = new JTextPane();
        scrollPane5.setViewportView(triggersTxt);
        triggersTxt.setForeground(Color.WHITE);
        triggersTxt.setOpaque(false);
        scrollPane6 = new JScrollPane();
        scrollPane6.setOpaque(false);
        scrollPane6.setBounds(316, 440, 268, 198);
        textPanel.add(scrollPane6);
        
        preconditionsTxt = new JTextPane();
        scrollPane6.setViewportView(preconditionsTxt);
        preconditionsTxt.setForeground(Color.WHITE);
        preconditionsTxt.setBorder(null);
        preconditionsTxt.setOpaque(false);
        scrollPane7 = new JScrollPane();
        scrollPane7.setOpaque(false);
        scrollPane7.setBounds(476, 150, 318, 206);
        textPanel.add(scrollPane7);
        
          primaryFlowTxt = new JTextPane();
          scrollPane7.setViewportView(primaryFlowTxt);
          primaryFlowTxt.setForeground(Color.WHITE);
          primaryFlowTxt.setBorder(null);
          primaryFlowTxt.setOpaque(false);
        
        scrollPane8 = new JScrollPane();
        scrollPane8.setOpaque(false);
        scrollPane8.setBounds(818, 149, 346, 207);
        textPanel.add(scrollPane8);        
        
        alternateFlowTxt = new JTextPane();
        scrollPane8.setViewportView(alternateFlowTxt);
        alternateFlowTxt.setForeground(Color.WHITE);
        alternateFlowTxt.setBorder(null);
        alternateFlowTxt.setOpaque(false);
        scrollPane9 = new JScrollPane();
        scrollPane9.setOpaque(false);
        scrollPane9.setBounds(896, 440, 268, 198);
        textPanel.add(scrollPane9);
        
        minimalGuaranteeTxt = new JTextPane();
        scrollPane9.setViewportView(minimalGuaranteeTxt);
        minimalGuaranteeTxt.setForeground(Color.WHITE);
        minimalGuaranteeTxt.setBorder(null);
        minimalGuaranteeTxt.setOpaque(false);
        scrollPane10 = new JScrollPane();
        scrollPane10.setOpaque(false);
        scrollPane10.setBounds(607, 440, 262, 198);
        textPanel.add(scrollPane10);
        
        successGuaranteeTxt = new JTextPane();
        scrollPane10.setViewportView(successGuaranteeTxt);
        successGuaranteeTxt.setForeground(Color.WHITE);
        successGuaranteeTxt.setBorder(null);
        successGuaranteeTxt.setOpaque(false);
        
        addstep2 = new JButton("");
        addstep2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the second add step button");
        	}
        });
        addstep2.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/addstep.png")));
        addstep2.setBounds(853, 367, 89, 23);
        textPanel.add(addstep2);
        
        JButton deleteactor1 = new JButton("");
        deleteactor1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the first remove actor button");
        	}
        });
        deleteactor1.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/delactor.png")));
        deleteactor1.setBounds(128, 367, 89, 23);
        textPanel.add(deleteactor1);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        scrollPane.setBorder(null);
        scrollPane1.setBorder(null);
        scrollPane2.setBorder(null);
        scrollPane3.setBorder(null);
        scrollPane4.setBorder(null);
        scrollPane5.setBorder(null);
        scrollPane6.setBorder(null);
        scrollPane7.setBorder(null);
        scrollPane8.setBorder(null);
        scrollPane9.setBorder(null);;
        scrollPane10.setBorder(null);
        
        JButton deleteactor2 = new JButton("");
        deleteactor2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.out.println("This is the second delete actor button");
        	}
        });
        deleteactor2.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/delactor.png")));
        deleteactor2.setBounds(367, 367, 89, 23);
        textPanel.add(deleteactor2);
        
        JButton deletestep1 = new JButton("");
        deletestep1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the first remove step button");
        	}
        });
        deletestep1.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/delstep.png")));
        deletestep1.setBounds(591, 367, 89, 23);
        textPanel.add(deletestep1);
        
        JButton modify1 = new JButton("");
        modify1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the first modify step button");
        	}
        });
        modify1.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/Modify.png")));
        modify1.setBounds(687, 367, 89, 23);
        textPanel.add(modify1);
        
        JButton deletestep2 = new JButton("");
        deletestep2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the second delete step button");
        	}
        });
        deletestep2.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/delstep.png")));
        deletestep2.setBounds(952, 367, 89, 23);
        textPanel.add(deletestep2);
        
        JButton modify2 = new JButton("");
        modify2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("This is the second modify step button");
        	}
        });
        modify2.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/Modify.png")));
        modify2.setBounds(1051, 367, 89, 23);
        textPanel.add(modify2);
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(UseCaseEditor.class.getResource("/resources/ID.png")));
        label.setBounds(-11, -101, 1469, 919);
        textPanel.add(label);
        
        setUpScrollPanes();
        setSize(USE_CASE_WIDTH, USE_CASE_HEIGHT);
    }
    
    /**
     * Sets up the scrollpanes.
     */
    public final void setUpScrollPanes() {
        scrollPane1.getViewport().setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        scrollPane3.getViewport().setOpaque(false);
        scrollPane4.getViewport().setOpaque(false);
        scrollPane5.getViewport().setOpaque(false);
        scrollPane6.getViewport().setOpaque(false);
        scrollPane7.getViewport().setOpaque(false);
        scrollPane8.getViewport().setOpaque(false);
        scrollPane9.getViewport().setOpaque(false);
        scrollPane10.getViewport().setOpaque(false);
    }

    /*****************************************************************
     setUC(UseCase) is to be used to set the default values of the
     fields, by pulling the data from the already existing values from
     within the currently accessed UseCase object.
     @param usecase - The current UseCase object being accessed
     *****************************************************************/
    public final void setUC(UseCase usecase, ArrayList<Actor> actors) {
    	Actors = actors;
    	uc = new UseCase();
    	uc = usecase;
    	uco = uc;
    	pA.addAll(usecase.getPrimaryActors());
    	sA.addAll(usecase.getSupportingActors());
        display();
    }
    public Boolean getflag(){
    	return cancelFlag;
    }
    public final void display(){
    	nameTxt.setText(uc.getName());
        idTxt.setText(uc.getID());
        descriptionTxt.setText(uc.getDescription());
        primaryActorTxt.setText(uc.getPrimString());
        supportingActorTxt.setText(uc.getSupString());
        triggersTxt.setText(uc.getTriggers());
        preconditionsTxt.setText(uc.getPreconditions());
        primaryFlowTxt.setText(uc.getPrimaryflow());
        alternateFlowTxt.setText(uc.getAlternativeflow());
        minimalGuaranteeTxt.setText(uc.getMinimalGuaruntees());
        successGuaranteeTxt.setText(uc.getSuccessGuarantees());
    }
    public final void displayactors(){
        primaryActorTxt.setText(uc.getPrimString());
        supportingActorTxt.setText(uc.getSupString());
    }
    /**************************************************************
     Manages the action listeners that are currently connected to
     GUI objects.
     @param e the event
     **************************************************************/
    public final void actionPerformed(final ActionEvent e) {
    	
        if (e.getSource() == okButton) {
        	cancelFlag = false;
            /**prevents user from attempting to save with no title **/
            if (!idTxt.getText().equals("")) {
                try {
                	dispose();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "ID must be set to save.\n", "ID ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == addactor1){
        	Flag = true;
        	ActorDialog = new AddActor(proActors, uc.getPrimaryActors());
        	uc.setPrimaryActor(ActorDialog.getucActorlist());
        	displayactors();
        }
        if(e.getSource() == addactor2){
        	Flag = false;
        	ActorDialog = new AddActor(proActors, uc.getSupportingActors());
        	uc.setSupportingActor(ActorDialog.getucActorlist());
        	displayactors();
        }
        /** Sets close status to 1 and exits the pop-up box **/
        if (e.getSource() == cancelButton) {
        	dispose();
        }
    }
}
