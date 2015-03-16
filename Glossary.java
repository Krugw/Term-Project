/*********************************************************************
 * This class creates a term and its definition for the 
 * Glossary in the Usecase.
 *
 * @author Wesley Krug, Gabriel Steponovich,
 * Michael Brecker, Halston Raddatz
 * @version Winter 2015
 ********************************************************************/
public class Glossary {
	
	/**
	 * This is the term name.
	 */
	private String word;
	
	/**
	 * This is the term's definition.
	 */
	private String definition;
	
	
	/**
	 * This is the constructor that initializes the global variables.
	 */
	public Glossary() {
		word = "";
		definition = "";
	}
	
	/**
	 * This sets the name of the term.
	 * 
	 * @param term is the String of the word name.
	 */
	public final void setWord(final String term) {
		this.word = term;
	}
	
	/**
	 * This sets the definition for a term.
	 * 
	 * @param def is a string definition of the term.
	 */
	public final void setDefinition(final String def) {
		this.definition = def;
	}
	
	/**
	 * This gets the name of a term.
	 * 
	 * @return the name of the term.
	 */
	public final String getWord() {
		return word;
	}
	
	/**
	 * This gets the definition of the term.
	 * 
	 * @return the definition of the term.
	 */
	public final String getDefinition() {
		return definition;
	}
	
	
}
