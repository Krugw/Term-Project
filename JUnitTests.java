import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTests {
	
	//UseCase class tests
	@Test
	public void testUseCaseGetName(){
		UseCase uc = new UseCase();
		assertTrue(uc.getName() == "Name of UseCase");
	}
	
	@Test
	public void testUseCaseGetID(){
		UseCase uc = new UseCase();
		assertTrue(uc.getID() == "Unique ID");
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
		assertTrue(uc.getPrimaryflow() == "Primary Flow");
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
		assertTrue(uc.toString() == "Unique ID");
	}
	
	@Test
	public void testProject1(){
		Project p = new Project();
		UseCase uc = new UseCase();
		
		p.addUsecase(uc);
		assertTrue(p.GetUsecase("Unique ID") == uc);
	}
	
	@Test
	public void testProject2(){
		Project p = new Project();
		UseCase uc = new UseCase();
		uc.setID("UC2");
		
		p.addUsecase(uc);
		assertFalse(p.GetUsecase("Unique ID") == uc);
	}
	
	//@Test
	//public void testProject3(){
	//	Project p = new Project();
	//	UseCase uc = new UseCase();
	//	uc.setName("Not Michael");
		
	//	p.addUsecase(uc);
	//	assertTrue(p.GetUsecase("Unique ID") == uc);
	//}
	
	@Test
	public void testProject4(){
		Project p = new Project();
		for(int i = 0; i<100; i++){
			UseCase uc = new UseCase();
			uc.setID(i + "");
			p.addUsecase(uc);
			assertTrue(p.GetUsecase(i+"") == uc);
		}
		
	}
	
	@Test
	public void testProject5(){
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
		assertTrue(p.GetUsecase("UC1") == uc1);
		assertTrue(p.GetUsecase("UC4") == uc4);
		assertTrue(p.GetUsecase("UC2") == uc2);
		assertTrue(p.GetUsecase("UC3") == uc3);
			
	}
	
	//Still working on testing the project class
	//I don't think we need test for the GUI stuff
}
