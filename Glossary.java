import java.util.ArrayList;

public class Glossary {
	private String word;
	private String definition;
	
	
	public Glossary(){
		word = "";
		definition = "";
	}
	
	public void setWord(String word){
		this.word = word;
	}
	
	public void setDefinition(String definition){
		this.definition = definition;
	}
	
	public String getWord(){
		return word;
	}
	
	public String getDefinition(){
		return definition;
	}
	
	
}
