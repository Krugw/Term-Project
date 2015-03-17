import java.util.Vector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/******************************************************************************
 * JUnitTests creates tests for the UseCase and Project classes.
 * @author Wesley Krug, Gabriel Steponovich, 
 *     Michael Brecker, Halston Raddatz
 * @version Winter 2015
 *****************************************************************************/
public class JUnitTests {
	
	/**************************************************************************
	 * Tests the getName() method of the class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetName() {
		UseCase uc = new UseCase();
		assertTrue(uc.getName() == "Name");
	}
	
	/**************************************************************************
	 * Tests the getID() method of the class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetID() {
		UseCase uc = new UseCase();
		assertTrue(uc.getID() == "Must Be Unique");
	}
	
	/**************************************************************************
	 * Tests the getDescription() method of the class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetDescription() {
		UseCase uc = new UseCase();
		assertTrue(uc.getDescription() == "Description");
	}
	
	/**************************************************************************
	 * Tests the getPrimaryActors() method of the class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetPrimaryActors() {
		UseCase uc = new UseCase();
		assertTrue(uc.getPrimaryActors() == "Primary Actors");
	}
	
	/**************************************************************************
	 * Tests the method getSupportingActors() of the class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetSupportingActors() {
		UseCase uc = new UseCase();
		assertTrue(uc.getSupportingActors() == "Supporting Actors");
	}
	
	/**************************************************************************
	 * Tests the method getTriggers() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetTriggers() {
		UseCase uc = new UseCase();
		assertTrue(uc.getTriggers() == "Triggers");
	}
	
	/**************************************************************************
	 * Tests the method getPreconditions() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetPreconditions() {
		UseCase uc = new UseCase();
		assertTrue(uc.getPreconditions() == "Preconditions");
	}
	
	/**************************************************************************
	 * Tests the method getPrimaryFlow() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetPrimaryFlow() {
		UseCase uc = new UseCase();
		assertTrue(uc.getPrimaryflow() == "Primary flow");
	}
	
	/**************************************************************************
	 * Tests the method getAlternateFlow() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetAlternateFlow() {
		UseCase uc = new UseCase();
		assertTrue(uc.getAlternativeflow() == "Alternate Flow");
	}
	
	/**************************************************************************
	 * Tests the method getMinimalGuarentee() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetMinimalGuarentee() {
		UseCase uc = new UseCase();
		assertTrue(uc.getMinimalGuaruntees() == "Minimal Guarantees");
	}
	
	/**************************************************************************
	 * Tests the method getSuccessGuarentees() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseGetSuccessGuarentees() {
		UseCase uc = new UseCase();
		assertTrue(uc.getSuccessGuarantees() == "Success Guarantees");
	}
	
	/**************************************************************************
	 * Tests the method setName(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetName() {
		UseCase uc = new UseCase();
		uc.setName("Michael");
		assertTrue(uc.getName() == "Michael");
	}
	
	/**************************************************************************
	 * Tests the method setID(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetID() {
		UseCase uc = new UseCase();
		uc.setID("UC1");
		assertTrue(uc.getID() == "UC1");
	}
	
	/**************************************************************************
	 * Tests the method setDescription(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetDescription() {
		UseCase uc = new UseCase();
		uc.setDescription("This is a description");
		assertTrue(uc.getDescription() == "This is a description");
	}
	
	/**************************************************************************
	 * Tests the method setPrimaryActors(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetPrimaryActors() {
		UseCase uc = new UseCase();
		uc.setPrimaryActors("User");
		assertTrue(uc.getPrimaryActors() == "User");
	}
	
	/**************************************************************************
	 * Tests the method setSupportingActors(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetSupportingActors() {
		UseCase uc = new UseCase();
		uc.setSupportingActors("MichaelB");
		assertTrue(uc.getSupportingActors() == "MichaelB");
	}
	
	/**************************************************************************
	 * Tests the method setTriggers(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetTriggers() {
		UseCase uc = new UseCase();
		uc.setTriggers("OK Button");
		assertTrue(uc.getTriggers() == "OK Button");
	}
	
	/**************************************************************************
	 * Tests the method setPreconditions(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetPreconditions() {
		UseCase uc = new UseCase();
		uc.setPreconditions("This is a precondition");
		assertTrue(uc.getPreconditions() == "This is a precondition");
	}
	
	/**************************************************************************
	 * Tests the method setPrimaryFlow(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetPrimaryFlow() {
		UseCase uc = new UseCase();
		uc.setPrimaryflow("First this, then this");
		assertTrue(uc.getPrimaryflow() == "First this, then this");
	}
	
	/**************************************************************************
	 * Tests the method setAlternateFlow(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetAlternateFlow() {
		UseCase uc = new UseCase();
		uc.setAlternativeflow("If this happens, then...");
		assertTrue(uc.getAlternativeflow() == "If this happens, then...");
	}
	
	/**************************************************************************
	 * Tests the method setMinimalGuarantees(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetMinimalGuarantees() {
		UseCase uc = new UseCase();
		uc.setMinimalGuarantees("It doesn't fail");
		assertTrue(uc.getMinimalGuaruntees() == "It doesn't fail");
	}
	
	/**************************************************************************
	 * Tests the method setSuccessGuarantees(String) of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseSetSuccessGuarantees() {
		UseCase uc = new UseCase();
		uc.setSuccessGuarantees("The program saves!");
		assertTrue(uc.getSuccessGuarantees() == "The program saves!");
	}
	
	/**************************************************************************
	 * Tests the method toString() of class UseCase.
	 *************************************************************************/
	@Test
	public final void testUseCaseToStringID() {
		UseCase uc = new UseCase();
		assertTrue(uc.toString() == "Must Be Unique");
	}
	
	//Project Class Tests
	
	/**************************************************************************
	 * Test the method getUseCase() of class Project.
	 *************************************************************************/
	@Test
	public final void testProjectGetUseCase1() {
		Project p = new Project();
		UseCase uc = new UseCase();
		
		p.addUsecase(uc);
		assertTrue(p.getUsecase("Must Be Unique") == uc);
	}
	
	/**************************************************************************
	 * Test the method getUseCase() of class Project while entering a 
	 * wrong ID string.
	 *************************************************************************/
	@Test
	public final void testProjectGetUseCaseWithWrongID() {
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setID("UC2");
		
		p.addUsecase(uc);
		assertFalse(p.getUsecase("Must Be Unique") == uc);
	}
	
	/**************************************************************************
	 * Test the method setName() of class Project.
	 *************************************************************************/
	@Test
	public final void testProjectChangingID() {
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setName("Not Michael");
		
		p.addUsecase(uc);
		assertFalse(p.getUsecase("Unique ID") == uc);
	}
	
	/**************************************************************************
	 * Tests the methods getUseCase() and addUseCase()of class Project.
	 * Tests the method setID(String) of class UseCase.
	 * Makes 100 UseCases with their own ids.
	 *************************************************************************/
	@Test
	public final void testProjectAdding100UseCases() {
		Project p = new Project();
		final int test = 100;
		for (int i = 0; i > test; i++) {
			UseCase uc = new UseCase();
			uc.setID(i + "");
			p.addUsecase(uc);
			assertTrue(p.getUsecase(i + "") == uc);
		}
		UseCase uc = new UseCase();
		uc.setID("47");
		p.addUsecase(uc);
		assertTrue(p.getUsecase("47") == uc);
		assertFalse(p.getUsecase("48") == uc);
		
	}
	
	/**************************************************************************
	 * Tests the addUsecase() method of class Project.
	 *************************************************************************/
	@Test
	public final void testProjectAddingUseCases() {
		Project p = new Project();
		UseCase uc1 = new UseCase();
		UseCase uc2 = new UseCase();
		UseCase uc3 = new UseCase();
		UseCase uc4 = new UseCase();
		
		uc1.setID("UC1");
		uc2.setID("UC2");
		uc3.setID("UC3");
		uc4.setID("UC4");
		
		p.addUsecase(uc1);
		p.addUsecase(uc2);
		p.addUsecase(uc3);
		p.addUsecase(uc4);
		assertTrue(p.getUsecase("UC2") == uc2);
		assertTrue(p.getUsecase("UC1") == uc1);
		assertTrue(p.getUsecase("UC4") == uc4);
		assertTrue(p.getUsecase("UC3") == uc3);
			
	}
	
	/**************************************************************************
	 * Tests for a non-existent UseCase in a Project.
	 *************************************************************************/
	@Test
	public final void testProjectNonexistantUseCase() {
		Project p = new Project();
		assertTrue(p.getUsecase("123") == null);
	}
	
	/**************************************************************************
	 * Tests for a non-existent UseCase ID in a Project.
	 *************************************************************************/
	@Test
	public final void testProjectNonexistantID() {
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setID("");
		p.addUsecase(uc);
		assertTrue(p.getUsecase("ID Not Given") == uc);
	}
	
	/**************************************************************************
	 * Tests the Load and Save functionality of the Project class.
	 *************************************************************************/
	@Test
	public final void testProjectLoadSave() {
		Project p1 = new Project();
		UseCase uc = new UseCase();
		p1.setProjectName("JUnitTest1");
		p1.addUsecase(uc);
		p1.saveToXML("C:\\Users\\Michael\\workspace\\UseCaseEditor");
		Project p2 = new Project();
		p2.loadFromXML("JUnitTest1");
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getID().equals(uc.getID()));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getName().equals(uc.getName()));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getDescription().equals(uc.getDescription()));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getPreconditions().equals(uc.getPreconditions()));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getTriggers().equals(uc.getTriggers()));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getPrimaryActors().equals(uc.getPrimaryActors()));
		assertTrue(p2.getUsecase("Must Be Unique"
				+ "").getAlternativeflow().equals(uc.getAlternativeflow()));
		assertTrue(p2.getUsecase("Must Be Unique"
				+ "").getSupportingActors().equals(uc.getSupportingActors()));
		assertTrue(p2.getUsecase("Must Be Unique"
				+ "").getMinimalGuaruntees().equals(uc.getMinimalGuaruntees()));
		assertTrue(p2.getUsecase("Must Be Unique"
				+ "").getSuccessGuarantees().equals(uc.getSuccessGuarantees()));
	}
	
	/**************************************************************************
	 * Tests Load and Save functions if text fields are empty.
	 *************************************************************************/
	@Test
	public final void testProjectLoadSaveEmptyFields() {
		Project p1 = new Project();
		UseCase uc = new UseCase();
		
		uc.setName("");
		uc.setDescription("");
		uc.setPrimaryActors("");
		uc.setSupportingActors("");
		uc.setPreconditions("");
		uc.setTriggers("");
		uc.setPrimaryflow("");
		uc.setAlternativeflow("");
		uc.setMinimalGuarantees("");
		uc.setSuccessGuarantees("");
		p1.setProjectName("JUnitTest1");
		p1.addUsecase(uc);
		p1.saveToXML("C:\\Users\\Michael\\workspace\\UseCaseEditor");
		Project p2 = new Project();
		p2.loadFromXML("JUnitTest1");
		assertTrue(p2.getUsecase("Must Be Unique").getID().equals(uc.getID()));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getName().equals(" "));
		assertTrue(p2.getUsecase("Must Be "
				+ "Unique").getDescription().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getPreconditions().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getTriggers().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getPrimaryActors().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getAlternativeflow().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getSupportingActors().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getMinimalGuaruntees().equals(" "));
		assertTrue(p2.getUsecase("Must Be"
				+ " Unique").getSuccessGuarantees().equals(" "));
	}
	
	/**************************************************************************
	 * Test the getIDs() method of class Project.
	 *************************************************************************/
	@Test
	public final void testProjectGetIDs() {
		Project p = new Project();
		UseCase uc1 = new UseCase();
		UseCase uc2 = new UseCase();
		UseCase uc3 = new UseCase();
		UseCase uc4 = new UseCase();
		uc1.setID("UC1");
		uc2.setID("UC2");
		uc3.setID("UC3");
		uc4.setID("UC4");
		p.addUsecase(uc1);
		p.addUsecase(uc2);
		p.addUsecase(uc3);
		p.addUsecase(uc4);
		Vector<String> ids = new Vector<String>();
		ids.add("UC1");
		ids.add("UC2");
		ids.add("UC3");
		ids.add("UC4");
		assertEquals(p.getIDs(), ids);
	}
	
	/**************************************************************************
	 * Test the getProjectName() method of Project class.
	 *************************************************************************/
	@Test
	public final void testProjectGetProjectName() {
		Project p = new Project();
		assertTrue(p.getProjectName().equals("Project"));
	}
	
	/**************************************************************************
	 * Test the removeUsecase() method of Project class.
	 *************************************************************************/
	@Test
	public final void testProjectRemoveUsecase()  {
		Project p = new Project();
		UseCase uc = new UseCase();
		UseCase uc1 = new UseCase();
		uc1.setID("UC1");
		p.addUsecase(uc);
		p.removeUsecase(uc);
		assertFalse(p.getUsecase("Must Be Unique") == uc);
		p.addUsecase(uc);
		p.addUsecase(uc1);
		p.removeUsecase(uc);
		assertFalse(p.getUsecase("Must Be Unipue") == uc);
		assertTrue(p.getUsecase("UC1") == uc1);
	}
	
	/**************************************************************************
	 * Test the toString() method of the project class.
	 *************************************************************************/
	@Test
	public final void testProjectToString() {
		Project p = new Project();
		p.setProjectName("Editor");
		UseCase uc1 = new UseCase();
		UseCase uc2 = new UseCase();
		UseCase uc3 = new UseCase();
		UseCase uc4 = new UseCase();
		uc1.setID("UC1");
		uc2.setID("UC2");
		uc3.setID("UC3");
		uc4.setID("UC4");
		p.addUsecase(uc1);
		p.addUsecase(uc2);
		p.addUsecase(uc3);
		p.addUsecase(uc4);
		System.out.println(p.toString());
		assertTrue(p.toString().equals("Project{UseCases="
				+ "[UC1, UC2, UC3, UC4], ProjectName='Editor'}"));
	}
	
	/**
	 * testing get and set term for word.
	 */
	@Test
	public final void testGlossaryGetSetWord(){
		Glossary g = new Glossary();
		g.setWord("word");
		assertTrue(g.getWord().equals("word"));
		g.setDefinition("word");
		assertTrue(g.getDefinition().equals("word"));
	}
	
	/**
	 * Test setting nothing for word.
	 */
	@Test
	public final void testBlankSetWord() {
		Glossary g = new Glossary();
		g.setWord("");
		assertTrue(g.getWord().equals(""));
	}
	
	/**
	 * Test setting 100 words and defs.
	 */
	@Test
	public final void testGlossaryWord() {
		Glossary g = new Glossary();
		for (int i = 1; i < 100; i++) {
			g.setWord("w" + i);
			g.setDefinition("d" + i);
			assertTrue(g.getWord().equals("w" + i));
			assertTrue(g.getDefinition().equals("d" + i));
		}
	}
	
	/**
	 * Test not setting word.
	 */
	@Test
	public final void testGlossaryGetWord() {
		Glossary g = new Glossary();
		assertTrue(g.getWord().equals(""));
	}
	
	/**
	 * Saving/loading a glossary term.
	 */
	@Test
	public final void testSaveTermProject() {
		 Project p = new Project();
		 Glossary g = new Glossary();
		 Glossary g2 = new Glossary();
		 p.setProjectName("JUnitTest2");
		 g.setWord("word");
		 g.setDefinition("def");
		 p.addGlossaryItem(g);
		 g2.setWord("word2");
		 g2.setDefinition("def2");
		 p.addGlossaryItem(g2);
		 p.saveToXML("C:\\Users\\Michael\\workspace\\UseCaseEditor");
		 Project p2 = new Project();
		 p2.loadFromXML("JUnitTest2");
		 assertTrue(p2.getGlossaryItem("word").getDefinition().equals("def"));
		 assertTrue(p2.getGlossaryItem("word2").getDefinition().equals("def2"));
	}
	
	/**
	 * Test getting the vector of terms.
	 */
	@Test
	public final void testGetTermsVector() {
		Glossary g1 = new Glossary();
		Glossary g2 = new Glossary();
		Glossary g3 = new Glossary();
		Glossary g4 = new Glossary();
		Glossary g5 = new Glossary();
		
		Project p = new Project();
		g1.setWord("w1");
		g1.setDefinition("d1");
		g2.setWord("w2");
		g2.setDefinition("d2");
		g3.setWord("w3");
		g3.setDefinition("d3");
		g4.setWord("w4");
		g4.setDefinition("d4");
		g5.setWord("w5");
		g5.setDefinition("d5");
		
		p.addGlossaryItem(g1);
		p.addGlossaryItem(g2);
		p.addGlossaryItem(g3);
		p.addGlossaryItem(g4);
		p.addGlossaryItem(g5);
		
		Vector<String> v = new Vector<String>();
		v.add("w1");
		v.add("w2");
		v.add("w3");
		v.add("w4");
		v.add("w5");
		
		assertTrue(v.equals(p.getTerms()));
	}
