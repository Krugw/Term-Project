import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * This class creates a GUI for the user to interact with in 
 * order to type in a new term and its definition to add
 * to their usecase project.
 * 
 * @author Wesley Krug, Gabriel Steponovich,
 * Michael Brecker, Halston Raddatz
 * @version Winter 2015
 *
 */
public class GlossaryWord extends JDialog implements ActionListener {
	
	/**
	 * serial Version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a panel text boxes.
	 */
	private JPanel panel;
	
	/**
	 * Creates a panel for buttons.
	 */
	private JPanel buttonPanel;
	
	/**
	 * Term label.
	 */
	private JLabel term;
	
	/**
	 * Definition label.
	 */
	private JLabel definition;
	
	/**
	 * Term text field.
	 */
	private JTextField word;
	
	/**
	 * Definition text field.
	 */
	private JTextPane def;
	
	/**
	 * OK button.
	 */
	private JButton oK;
	
	/**
	 * Cancel button.
	 */
	private JButton cancel;
	
	/**
	 * Creates an object for a glossary term.
	 */
	private Glossary glossaryTerm;	
	
	/**
	 * Constructor to set up GUI for user interaction.
	 */
	public GlossaryWord() {
		setLocationRelativeTo(null);
        setTitle("New Glossary Term");
        setSize(500,250);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        
        buttonPanel = new JPanel();
        
        term = new JLabel("Term: ");
        panel.add(term);
        word = new JTextField(30);
        panel.add(word);
        
        definition = new JLabel("Definition: ");
        panel.add(definition);
        def = new JTextPane();
        JScrollPane sp = new JScrollPane(def);
        panel.add(sp);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        oK = new JButton("Add Term");
        cancel = new JButton("Cancel");
        panel.add(oK);
        panel.add(cancel);
        buttonPanel.add(oK);
        buttonPanel.add(cancel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        oK.addActionListener(this);
        cancel.addActionListener(this);
        
        glossaryTerm = new Glossary();
        
        setVisible(true);
	}
	
	/**
	 * Adds an ActionListerner to save the term when ok is pressed.
	 * 
	 * @param listener is an ActionListener
	 */
	public final void addSaveListener(final ActionListener listener) {

	        oK.addActionListener(listener);
	}
	
	/**
	 * The action performed method for created a new term in the
	 * glossary.
	 * 
	 * @param e is an ActionEvent
	 */
	public final void actionPerformed(final ActionEvent e) {
        /** exits the pop-up box **/
        if (e.getSource() == cancel) {
            dispose();
        }
        
        if (e.getSource() == oK) {
        	if (!word.getText().equals("")) {
                try {

                    dispose();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No term entered.\n", "ID ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
                
            }
        	
        }
		
	}
	
	/**
	 * Gets a glossary item.
	 * 
	 * @return the object of a term
	 */
	public final Glossary getGlossaryTerm() {
		glossaryTerm.setWord(word.getText());
		glossaryTerm.setDefinition(def.getText());
		return glossaryTerm;
	}
	
	/**
	 * Sets a Glossary item.
	 * 
	 * @param glossary is a Glossary class object
	 */
	public final void setGlossaryTerm(final Glossary glossary) {
		word.setText(glossary.getWord());
		def.setText(glossary.getDefinition());
	}

}
