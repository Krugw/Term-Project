
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setLocationRelativeTo(null);
        setTitle("Use Case Editor");
        setSize(EDITOR_WIDTH, EDITOR_HEIGHT);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(TEXT_PANEL_LAYOUT, 2));

        /** Sets up the textFields with default values **/
        textPanel.add(new JLabel("Name: "));
        nameTxt = new JTextPane();
        JScrollPane sp = new JScrollPane(nameTxt);
        textPanel.add(sp);

        textPanel.add(new JLabel("ID: "));
        idTxt = new JTextPane();
        sp = new JScrollPane(idTxt);
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

        setSize(USE_CASE_WIDTH, USE_CASE_HEIGHT);
        setVisible(true);
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
