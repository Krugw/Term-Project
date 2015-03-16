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

public class GlossaryWord extends JDialog implements ActionListener{

	private JPanel panel;
	private JPanel buttonPanel;
	
	private JLabel term;
	private JLabel definition;
	
	private JTextField word;
	private JTextPane def;
	
	private JButton OK;
	private JButton cancel;
	
	private Glossary glossaryTerm;	
	
	public GlossaryWord(){
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
        getContentPane().add(panel,BorderLayout.NORTH);
        
        OK = new JButton("Add Term");
        cancel = new JButton("Cancel");
        panel.add(OK);
        panel.add(cancel);
        buttonPanel.add(OK);
        buttonPanel.add(cancel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        OK.addActionListener(this);
        cancel.addActionListener(this);
        
        glossaryTerm = new Glossary();
        
        setVisible(true);
	}
	
	public final void addSaveListener(final ActionListener listener) {

	        OK.addActionListener(listener);
	}
	  
	public void actionPerformed(ActionEvent e) {
        /** exits the pop-up box **/
        if (e.getSource() == cancel) {
            dispose();
        }
        
        if(e.getSource() == OK){
        	if(!word.getText().equals("")){
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
	
	public Glossary getGlossaryTerm(){
		glossaryTerm.setWord(word.getText());
		glossaryTerm.setDefinition(def.getText());
		return glossaryTerm;
	}
	
	public void setGlossaryTerm(Glossary glossary){
		word.setText(glossary.getWord());
		def.setText(glossary.getDefinition());
	}

}
