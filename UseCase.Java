import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UseCase {
	// Just a basic Idea so far.
	protected String Name;
	protected String ID;
	protected String Description;
	protected String primaryActors; 		//Separated actors into primary
	protected String supportingActors;	//and supporting actors		
	protected String Triggers;
	protected String Preconditions;
	protected String Primaryflow;
	protected String Alternateflow;
	protected String MinimalGuarantees;
	protected String SuccessGuarantees;
	
	public UseCase(){
		Name = "a";
		ID = "b";
		Description = "c";
		primaryActors ="d";
		supportingActors = "e";
		Triggers = "f";
		Preconditions = "g";
		Primaryflow = "h";
		Alternateflow = "i";
		MinimalGuarantees = "j";
		SuccessGuarantees = "k";
		
	}

	public void setName(String name) {
		this.Name = name;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public void setPrimaryActors(String primaryActor) {
		this.primaryActors = primaryActor;
	}
	
	public void setSupportingActors(String supportingActor){
		this.supportingActors = supportingActor;
	}

	public void setTriggers(String triggers) {
		this.Triggers = triggers;
	}

	public void setPreconditions(String preconditions) {
		this.Preconditions = preconditions;
	}

	public void setPrimaryflow(String primaryFlow) {
		this.Primaryflow = primaryFlow;
	}

	public void setAlternativeflow(String alternativeFlow) {
		this.Alternateflow = alternativeFlow;
	}

	public void setMinimalGuarantees(String minimalGuarantee) {
		this.MinimalGuarantees = minimalGuarantee;
	}

	public void setSuccessGuarantees(String successGuarantee) {
		this.SuccessGuarantees = successGuarantee;
	}
	
	public String getName() {
		return Name;
	}
	public String getID() {
		return ID;
	}

	public String getDescription() {
		return Description;
	}

	public String getPrimaryActors() {
		return primaryActors;
	}
	
	public String getSupportingActors(){
		return supportingActors;
	}

	public String getTriggers() {
		return Triggers;
	}

	public String getPreconditions() {
		return Preconditions;
	}

	public String getPrimaryflow() {
		return Primaryflow;
	}

	public String getAlternativeflow() {
		return Alternateflow;
	}

	public String getMinimalGuaruntees() {
		return MinimalGuarantees;
	}

	public String getSuccessGuarantees() {
		return SuccessGuarantees;
	}
	
	
	public boolean saveAsText(String filename){
		if (filename.equals("")) {
			return false;
		}
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(filename)));
			
			out.println("" + this.getName());
			out.println("" + this.getID());
			out.println("" + this.getDescription());
			out.println("" + this.getPrimaryActors());
			out.println("" + this.getSupportingActors());
			out.println("" + this.getTriggers());
			out.println("" + this.getPreconditions());
			out.println("" + this.getPrimaryflow());
			out.println("" + this.getAlternativeflow());
			out.println("" + this.getMinimalGuaruntees());
			out.println("" + this.getSuccessGuarantees());
			out.close();
			
			return true;
			
		}catch (IOException ex) {
			return false;
		}
	}

	public void loadFromText(String filename){
		try {
			Scanner scanner = new Scanner(new File(filename));
			
			Name = scanner.nextLine().trim();
			ID = scanner.nextLine().trim();
			Description = scanner.nextLine().trim();
			primaryActors = scanner.nextLine().trim();
			supportingActors = scanner.nextLine().trim();
			Triggers = scanner.nextLine().trim();
			Preconditions = scanner.nextLine().trim();
			Primaryflow = scanner.nextLine().trim();
			Alternateflow = scanner.nextLine().trim();
			MinimalGuarantees = scanner.nextLine().trim();
			SuccessGuarantees = scanner.nextLine().trim();
			scanner.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@Override
    public String toString() {
        return "UseCase{" +
                "Name='" + Name + '\'' +
                ", ID='" + ID + '\'' +
                ", Description='" + Description + '\'' +
                ", primaryActors='" + primaryActors + '\'' +
                ", supportingActors='" + supportingActors + '\'' +
                ", Triggers='" + Triggers + '\'' +
                ", Preconditions='" + Preconditions + '\'' +
                ", Primaryflow='" + Primaryflow + '\'' +
                ", Alternateflow='" + Alternateflow + '\'' +
                ", MinimalGuarantees='" + MinimalGuarantees + '\'' +
                ", SuccessGuarantees='" + SuccessGuarantees + '\'' +
                '}';
    }
}
