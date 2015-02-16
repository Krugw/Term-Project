/*****************************************************************
UseCase creates and manages all fields that are connected or attributed
to the creation of a UseCase. including Name, ID, Description, primaryActors
supportingActors, Triggers, Preconditions, Primaryflow, Alternateflow,
MinimalGuarantees, and SuccessGuarantees. 

@authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class UseCase {

	protected String Name;
	protected String ID;
	protected String Description;
	protected String primaryActors; 		//Separated actors into primary
	protected String supportingActors;		//and supporting actors		
	protected String Triggers;
	protected String Preconditions;
	protected String Primaryflow;
	protected String Alternateflow;
	protected String MinimalGuarantees;
	protected String SuccessGuarantees;
	protected LoadFileBox loadFile;
	
	/*****************************************************************
	Default UseCase values
	*****************************************************************/	
	public UseCase(){
		Name = "Name";
		ID = "Must Be Unique";
		Description = "Description";
		primaryActors ="Primary Actors";
		supportingActors = "Supporting Actors";
		Triggers = "Triggers";
		Preconditions = "Preconditions";
		Primaryflow = "Primary flow";
		Alternateflow = "Alternate Flow";
		MinimalGuarantees = "Minimal Guarantees";
		SuccessGuarantees = "Success Guarantees";
		
	}
	
	/*****************************************************************
	 Various setters for use in creating UseCase parameter values
	*****************************************************************/
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
	
	/*****************************************************************
	 Various getters for use in creation and returning UseCase values
	*****************************************************************/
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
	@Override
    public String toString() {
    	return ID;
    }
}
