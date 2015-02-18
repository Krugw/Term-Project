import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JUnitTests {
	
	//UseCase class tests
	@Test
	public void testUseCaseGetName(){
		UseCase uc = new UseCase();
		assertTrue(uc.getName() == "Name");
	}
	
	@Test
	public void testUseCaseGetID(){
		UseCase uc = new UseCase();
		assertTrue(uc.getID() == "Must Be Unique");
	}
	
	@Test
	public void testUseCaseGetDescription(){
		UseCase uc = new UseCase();
		assertTrue(uc.getDescription() == "Description");
	}
	
	@Test
	public void testUseCaseGetPrimaryActors(){
		UseCase uc = new UseCase();
		assertTrue(uc.getPrimaryActors() == "Primary Actors");
	}
	
	@Test
	public void testUseCaseGetSupportingActors(){
		UseCase uc = new UseCase();
		assertTrue(uc.getSupportingActors() == "Supporting Actors");
	}
	
	@Test
	public void testUseCaseGetTriggers(){
		UseCase uc = new UseCase();
		assertTrue(uc.getTriggers() == "Triggers");
	}
	
	@Test
	public void testUseCaseGetPreconditions(){
		UseCase uc = new UseCase();
		assertTrue(uc.getPreconditions() == "Preconditions");
	}
	
	@Test
	public void testUseCaseGetPrimaryFlow(){
		UseCase uc = new UseCase();
		assertTrue(uc.getPrimaryflow() == "Primary flow");
	}
	
	@Test
	public void testUseCaseGetAlternateFlow(){
		UseCase uc = new UseCase();
		assertTrue(uc.getAlternativeflow() == "Alternate Flow");
	}
	
	@Test
	public void testUseCaseGetMinimalGuarentee(){
		UseCase uc = new UseCase();
		assertTrue(uc.getMinimalGuaruntees() == "Minimal Guarantees");
	}
	
	@Test
	public void testUseCaseGetSuccessGuarentees(){
		UseCase uc = new UseCase();
		assertTrue(uc.getSuccessGuarantees() == "Success Guarantees");
	}
	
	@Test
	public void testUseCaseSetName(){
		UseCase uc = new UseCase();
		uc.setName("Michael");
		assertTrue(uc.getName() == "Michael");
	}
	
	@Test
	public void testUseCaseSetID(){
		UseCase uc = new UseCase();
		uc.setID("UC1");
		assertTrue(uc.getID() == "UC1");
	}
	
	@Test
	public void testUseCaseSetDescription(){
		UseCase uc = new UseCase();
		uc.setDescription("This is a description");
		assertTrue(uc.getDescription() == "This is a description");
	}
	
	@Test
	public void testUseCaseSetPrimaryActors(){
		UseCase uc = new UseCase();
		uc.setPrimaryActors("User");
		assertTrue(uc.getPrimaryActors() == "User");
	}
	
	@Test
	public void testUseCaseSetSupportingActors(){
		UseCase uc = new UseCase();
		uc.setSupportingActors("MichaelB");
		assertTrue(uc.getSupportingActors() == "MichaelB");
	}
	
	@Test
	public void testUseCaseSetTriggers(){
		UseCase uc = new UseCase();
		uc.setTriggers("OK Button");
		assertTrue(uc.getTriggers() == "OK Button");
	}
	
	@Test
	public void testUseCaseSetPreconditions(){
		UseCase uc = new UseCase();
		uc.setPreconditions("This is a precondition");
		assertTrue(uc.getPreconditions() == "This is a precondition");
	}
	
	@Test
	public void testUseCaseSetPrimaryFlow(){
		UseCase uc = new UseCase();
		uc.setPrimaryflow("First this, then this");
		assertTrue(uc.getPrimaryflow() == "First this, then this");
	}
	
	@Test
	public void testUseCaseSetAlternateFlow(){
		UseCase uc = new UseCase();
		uc.setAlternativeflow("If this happens, then...");
		assertTrue(uc.getAlternativeflow() == "If this happens, then...");
	}
	
	@Test
	public void testUseCaseSetMinimalGuarantees(){
		UseCase uc = new UseCase();
		uc.setMinimalGuarantees("It doesn't fail");
		assertTrue(uc.getMinimalGuaruntees() == "It doesn't fail");
	}
	
	@Test
	public void testUseCaseSetSuccessGuarantees(){
		UseCase uc = new UseCase();
		uc.setSuccessGuarantees("The program saves!");
		assertTrue(uc.getSuccessGuarantees() == "The program saves!");
	}
	
	@Test
	public void testUseCaseToStringID(){
		UseCase uc = new UseCase();
		assertTrue(uc.toString() == "Must Be Unique");
	}
	
	//Project Class Tests
	
	@Test
	public void testProjectGetUseCase(){
		Project p = new Project();
		UseCase uc = new UseCase();
		
		p.addUsecase(uc);
		assertTrue(p.GetUsecase("Must Be Unique") == uc);
	}
	
	@Test
	public void testProjectGetUseCaseWithWrongID(){
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setID("UC2");
		
		p.addUsecase(uc);
		assertFalse(p.GetUsecase("Must Be Unique") == uc);
	}
	
	@Test
	public void testProjectChangingID(){
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setName("Not Michael");
		
		p.addUsecase(uc);
		assertFalse(p.GetUsecase("Unique ID") == uc);
	}
	
	@Test
	public void testProjectAdding100UseCases(){
		Project p = new Project();
		for(int i = 0; i<100; i++){
			UseCase uc = new UseCase();
			uc.setID(i + "");
			p.addUsecase(uc);
			assertTrue(p.GetUsecase(i+"") == uc);
		}
		
	}
	
	@Test
	public void testProjectAddingUseCases(){
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
		assertTrue(p.GetUsecase("UC2") == uc2);
		assertTrue(p.GetUsecase("UC1") == uc1);
		assertTrue(p.GetUsecase("UC4") == uc4);
		assertTrue(p.GetUsecase("UC3") == uc3);
			
	}
	
	@Test
	public void testProjectNonexistantUseCase(){
		Project p = new Project();
		assertTrue(p.GetUsecase("123") == null);
	}
	
	@Test
	public void testProjectNonexistantID(){
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setID("");
		p.addUsecase(uc);
		assertTrue(p.GetUsecase("ID Not Given") == uc);
	}
	
	@Test
	public void testProjectLoadSave(){
		Project p1 = new Project();
		UseCase uc = new UseCase();
		p1.setProjectName("JUnitTest1");
		p1.addUsecase(uc);
		p1.saveToXML("C:\\Users\\Michael\\workspace\\UseCaseEditor");
		Project p2 = new Project();
		p2.loadFromXML("JUnitTest1");
		assertTrue(p2.GetUsecase("Must Be Unique").getID().equals(uc.getID()));
		assertTrue(p2.GetUsecase("Must Be Unique").getName().equals(uc.getName()));
		assertTrue(p2.GetUsecase("Must Be Unique").getDescription().equals(uc.getDescription()));
		assertTrue(p2.GetUsecase("Must Be Unique").getPreconditions().equals(uc.getPreconditions()));
		assertTrue(p2.GetUsecase("Must Be Unique").getTriggers().equals(uc.getTriggers()));
		assertTrue(p2.GetUsecase("Must Be Unique").getPrimaryActors().equals(uc.getPrimaryActors()));
		assertTrue(p2.GetUsecase("Must Be Unique").getAlternativeflow().equals(uc.getAlternativeflow()));
		assertTrue(p2.GetUsecase("Must Be Unique").getSupportingActors().equals(uc.getSupportingActors()));
		assertTrue(p2.GetUsecase("Must Be Unique").getMinimalGuaruntees().equals(uc.getMinimalGuaruntees()));
		assertTrue(p2.GetUsecase("Must Be Unique").getSuccessGuarantees().equals(uc.getSuccessGuarantees()));
	}
	
	@Test
	public void testProjectLoadSaveEmptyFields(){
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
		assertTrue(p2.GetUsecase("Must Be Unique").getID().equals(uc.getID()));
		assertTrue(p2.GetUsecase("Must Be Unique").getName().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getDescription().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getPreconditions().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getTriggers().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getPrimaryActors().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getAlternativeflow().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getSupportingActors().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getMinimalGuaruntees().equals(" "));
		assertTrue(p2.GetUsecase("Must Be Unique").getSuccessGuarantees().equals(" "));
		
	}
	
	@Test
	public void testProjectGetIDs(){
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
		Vector<String> IDS = new Vector<String>();
		IDS.add("UC1");
		IDS.add("UC2");
		IDS.add("UC3");
		IDS.add("UC4");
		assertEquals(p.Getids(), IDS);
	}
	
	@Test
	public void testProjectGetProjectName(){
		Project p = new Project();
		assertTrue(p.GetProjectName().equals("Project"));
	}
	
	@Test
	public void testProjectRemoveUsecase(){
		Project p = new Project();
		UseCase uc = new UseCase();
		UseCase uc1 = new UseCase();
		uc1.setID("UC1");
		p.addUsecase(uc);
		p.RemoveUsecase(uc);
		assertFalse(p.GetUsecase("Must Be Unique") == uc);
		p.addUsecase(uc);
		p.addUsecase(uc1);
		p.RemoveUsecase(uc);
		assertFalse(p.GetUsecase("Must Be Unipue") == uc);
		assertTrue(p.GetUsecase("UC1") == uc1);
	}
	
	@Test 
	public void testProjectToString(){
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
		assertTrue(p.toString().equals("Project{Usecases=[UC1, UC2, UC3, UC4], ProjectName='Editor'}"));
	}
	
	/*
	 * Tests for LoadFileBox class
	 * You will have to change the filename/directory to your own
	@Test
	public void testLoadFileBox(){
		LoadFileBox lfb = new LoadFileBox();
		String file = lfb.getFileSelected();
		System.out.println(file);
		assertTrue(file.equals("C:\\Users\\Michael\\Desktop\\Test"));
	}
	
	@Test
	public void testLoadFileBoxCreate(){
		LoadFileBox lfb = new LoadFileBox(true);
		String directory = lfb.getFileSelected();
		System.out.println(directory);
		assertTrue(directory.equals("C:\\Users\\Michael\\Desktop"));
	}
	*/
	
	/* Attempted to test the UseCaseEditor, but I don't think we can
	@Test
	public void testUseCaseEditor(){
		UseCaseEditor uce = new UseCaseEditor();
		UseCase uc = new UseCase();
		uce.setUC(uc);
		assertTrue(uce.getUC() == uc);
	}
	*/
}
