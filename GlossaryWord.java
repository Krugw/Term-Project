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
import javax.swing.ImageIcon;
import java.awt.Color;

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
        panel.setBounds(0, 0, 484, 211);
        
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 201, 484, 10);
        panel.setLayout(null);
        JScrollPane sp = new JScrollPane();
        sp.setBackground(Color.BLACK);
        sp.setOpaque(false);
        sp.setForeground(Color.BLACK);
        sp.setViewportBorder(null);
        sp.setBounds(107, 100, 259, 66);
        
        sp.getViewport().setOpaque(false);
        panel.add(sp);
        
        sp.setBorder(null);
        def = new JTextPane();
        sp.setViewportView(def);
        def.setDisabledTextColor(Color.WHITE);
        def.setBorder(null);
        def.setOpaque(false);
        word = new JTextField(30);
        word.setBorder(null);
        word.setOpaque(false);
        word.setBounds(116, 27, 246, 36);
        panel.add(word);
        cancel = new JButton("");
        cancel.setIcon(new ImageIcon(GlossaryWord.class.getResource("/resources/cancel.png")));
        cancel.setBounds(241, 177, 80, 23);
        panel.add(cancel);
        cancel.addActionListener(this);
        getContentPane().setLayout(null);
        getContentPane().add(panel);
        
        
        oK = new JButton("");
        oK.setIcon(new ImageIcon(GlossaryWord.class.getResource("/resources/ok.png")));
        oK.setBounds(151, 177, 80, 23);
        panel.add(oK);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(GlossaryWord.class.getResource("/resources/glossayword.png")));
        lblNewLabel.setBounds(0, 0, 484, 211);
        panel.add(lblNewLabel);
        //buttonPanel.add(oK);
        //buttonPanel.add(cancel);
        getContentPane().add(buttonPanel);
        
        oK.addActionListener(this);
        
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
