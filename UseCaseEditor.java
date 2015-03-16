import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.net.URL;

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
    private UseCase uc = new UseCase();

    /**
     * Width of the useCaseEditor JPanel.
     */
    public static final int EDITOR_WIDTH = 400;

    /**
     * Heigth of the useCaseEditor JPanel.
     */
    public static final int EDITOR_HEIGHT = 200;

    /**
     * The Layout for the TextPanels.
     */
    public static final int TEXT_PANEL_LAYOUT = 12;

    /**
     * Width of the usecase.
     */
    public static final int USE_CASE_WIDTH = 800;

    /**
     * Heigh of the usecase.
     */
    public static final int USE_CASE_HEIGHT = 700;

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
        
    /*****************************************************************
     Uses .getText() to transfer current values of JTextPanes into their
     associated parameters within the UseCase object.
     @return UseCase The usecase to returned.
     *****************************************************************/
    public final UseCase getUC() {
        uc.setName(nameTxt.getText());
        uc.setID(idTxt.getText());
        uc.setDescription(descriptionTxt.getText());
        uc.setPrimaryActors(primaryActorTxt.getText());
        uc.setSupportingActors(supportingActorTxt.getText());
        uc.setTriggers(triggersTxt.getText());
        uc.setPreconditions(preconditionsTxt.getText());
        uc.setPrimaryflow(primaryFlowTxt.getText());
        uc.setAlternativeflow(alternateFlowTxt.getText());
        uc.setMinimalGuarantees(minimalGuaranteeTxt.getText());
        uc.setSuccessGuarantees(successGuaranteeTxt.getText());
        return uc;
    }
    
    /*****************************************************************
     Instantiates a custom dialog box and adds applicable jTextFields,
     jButtons, and JComboBoxes. Sets box to modal as well as centering
     it on click.
     *****************************************************************/
    public UseCaseEditor() {
        setTitle("Use Case Editor");
        setSize(EDITOR_WIDTH, EDITOR_HEIGHT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(null);
        getContentPane().add(textPanel, BorderLayout.CENTER);
                
		URL boximage = GUI.class.getResource("/resources/bg2.png");
		URL checkmark = GUI.class.getResource("/resources/checkmark.png");
		URL xmark = GUI.class.getResource("/resources/xmark.png");
		
        okButton = new JButton("Save");
        okButton.setIcon(new ImageIcon(checkmark));
        okButton.setOpaque(false);
        okButton.setBounds(667, 517, 79, 50);
        cancelButton = new JButton("Cancel");
        cancelButton.setIcon(new ImageIcon(xmark));
        cancelButton.setOpaque(false);
        cancelButton.setBounds(667, 578, 79, 50);
        textPanel.add(okButton);
        textPanel.add(cancelButton);
        
        JScrollPane scrollPane = new JScrollPane(); 
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        scrollPane3 = new JScrollPane();
        scrollPane3.setOpaque(false);
        scrollPane3.setBounds(112, 176, 526, 37);
        textPanel.add(scrollPane3);
         
        primaryActorTxt = new JTextPane();
        scrollPane3.setViewportView(primaryActorTxt);
        primaryActorTxt.setForeground(Color.WHITE);
        primaryActorTxt.setBorder(null);
        primaryActorTxt.setOpaque(false);
        
        scrollPane1 = new JScrollPane();
        scrollPane1.setOpaque(false);
        scrollPane1.setBounds(407, 43, 237, 44);
        textPanel.add(scrollPane1);
        idTxt = new JTextPane();
        scrollPane1.setViewportView(idTxt);
        idTxt.setForeground(Color.WHITE);
        idTxt.setBorder(null);
        idTxt.setOpaque(false);
        scrollPane.setBounds(112, 43, 275, 44);
        textPanel.add(scrollPane);
        nameTxt = new JTextPane();
        nameTxt.setOpaque(false);
        nameTxt.setBackground(Color.GRAY);
        scrollPane.setViewportView(nameTxt);
        nameTxt.setForeground(Color.WHITE);
        nameTxt.setBorder(null);
        
        scrollPane2 = new JScrollPane();
        scrollPane2.setOpaque(false);
        scrollPane2.setBounds(112, 124, 528, 29);
        textPanel.add(scrollPane2);
        descriptionTxt = new JTextPane();
        scrollPane2.setViewportView(descriptionTxt);
        descriptionTxt.setForeground(Color.WHITE);
        descriptionTxt.setBorder(null);
        descriptionTxt.setOpaque(false);
        scrollPane4 = new JScrollPane();
        scrollPane4.setOpaque(false);
        scrollPane4.setBounds(112, 233, 527, 37);
        textPanel.add(scrollPane4);
        
        supportingActorTxt = new JTextPane();
        scrollPane4.setViewportView(supportingActorTxt);
        supportingActorTxt.setForeground(Color.WHITE);
        supportingActorTxt.setBorder(null);
        supportingActorTxt.setOpaque(false);
        scrollPane5 = new JScrollPane();
        scrollPane5.setOpaque(false);
        scrollPane5.setBounds(111, 295, 528, 29);
        textPanel.add(scrollPane5);
        
        triggersTxt = new JTextPane();
        scrollPane5.setViewportView(triggersTxt);
        triggersTxt.setForeground(Color.WHITE);
        triggersTxt.setOpaque(false);
        scrollPane6 = new JScrollPane();
        scrollPane6.setOpaque(false);
        scrollPane6.setBounds(112, 347, 527, 37);
        textPanel.add(scrollPane6);
         
        preconditionsTxt = new JTextPane();
        scrollPane6.setViewportView(preconditionsTxt);
        preconditionsTxt.setForeground(Color.WHITE);
        preconditionsTxt.setBorder(null);
        preconditionsTxt.setOpaque(false);
        scrollPane7 = new JScrollPane();
        scrollPane7.setOpaque(false);
        scrollPane7.setBounds(112, 405, 527, 37);
        textPanel.add(scrollPane7);
      
        primaryFlowTxt = new JTextPane();
        scrollPane7.setViewportView(primaryFlowTxt);
        primaryFlowTxt.setForeground(Color.WHITE);
        primaryFlowTxt.setBorder(null);
        primaryFlowTxt.setOpaque(false);
        
        scrollPane8 = new JScrollPane();
        scrollPane8.setOpaque(false);
        scrollPane8.setBounds(112, 462, 529, 37);
        textPanel.add(scrollPane8);        
        
        alternateFlowTxt = new JTextPane();
        scrollPane8.setViewportView(alternateFlowTxt);
        alternateFlowTxt.setForeground(Color.WHITE);
        alternateFlowTxt.setBorder(null);
        alternateFlowTxt.setOpaque(false);
        scrollPane9 = new JScrollPane();
        scrollPane9.setOpaque(false);
        scrollPane9.setBounds(112, 519, 526, 37);
        textPanel.add(scrollPane9);
        
        minimalGuaranteeTxt = new JTextPane();
        scrollPane9.setViewportView(minimalGuaranteeTxt);
        minimalGuaranteeTxt.setForeground(Color.WHITE);
        minimalGuaranteeTxt.setBorder(null);
        minimalGuaranteeTxt.setOpaque(false);
        scrollPane10 = new JScrollPane();
        scrollPane10.setOpaque(false);
        scrollPane10.setBounds(112, 578, 529, 37);
        textPanel.add(scrollPane10);
        
        successGuaranteeTxt = new JTextPane();
        scrollPane10.setViewportView(successGuaranteeTxt);
        successGuaranteeTxt.setForeground(Color.WHITE);
        successGuaranteeTxt.setBorder(null);
        successGuaranteeTxt.setOpaque(false);
        JLabel label = new JLabel("Name: ");
        label.setIcon(new ImageIcon(boximage));
        label.setBounds(0, 1, 784, 650);
        textPanel.add(label);
        
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        setUpScrollPanes();
        setSize(USE_CASE_WIDTH, USE_CASE_HEIGHT);
        setVisible(true);
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
    public final void setUC(final UseCase usecase) {
        nameTxt.setText(usecase.getName());
        idTxt.setText(usecase.getID());
        descriptionTxt.setText(usecase.getDescription());
        primaryActorTxt.setText(usecase.getPrimaryActors());
        supportingActorTxt.setText(usecase.getSupportingActors());
        triggersTxt.setText(usecase.getTriggers());
        preconditionsTxt.setText(usecase.getPreconditions());
        primaryFlowTxt.setText(usecase.getPrimaryflow());
        alternateFlowTxt.setText(usecase.getAlternativeflow());
        minimalGuaranteeTxt.setText(usecase.getMinimalGuaruntees());
        successGuaranteeTxt.setText(usecase.getSuccessGuarantees());
    }
    
    /**************************************************************
     Adds ActionListener to okButton.
     @param listener the ActionListener to be added to the given button.
     **************************************************************/
    public final void addSaveListener(final ActionListener listener) {

        okButton.addActionListener(listener);
    }

    /**************************************************************
     Manages the action listeners that are currently connected to
     GUI objects.
     @param e the event
     **************************************************************/
    public final void actionPerformed(final ActionEvent e) {

        if (e.getSource() == okButton) {

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

        /** Sets close status to 1 and exits the pop-up box **/
        if (e.getSource() == cancelButton) {
            dispose();
        }

    }
}
