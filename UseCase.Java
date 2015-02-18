/******************************************************************************
UseCase creates and manages all fields that are connected or attributed
to the creation of a UseCase. including Name, ID, Description, primaryActors
supportingActors, Triggers, Preconditions, Primaryflow, Alternateflow,
MinimalGuarantees, and SuccessGuarantees. 

@author Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
******************************************************************************/
public class UseCase {

	/**************************************************************************
	 * Name field for a UseCase.
	 **************************************************************************/
	private String name;
	
	/**************************************************************************
	 * ID field for a UseCase.
	 **************************************************************************/
	private String id;
	
	/**************************************************************************
	 * Description field for a UseCase.
	 **************************************************************************/
	private String description;
	
	/**************************************************************************
	 * Primary Actor field for a UseCase.
	 **************************************************************************/
	private String primaryActors; 	
	
	/**************************************************************************
	 * Supporting Actor field for a UseCase.
	 **************************************************************************/
	private String supportingActors;
	
	/**************************************************************************
	 * Triggers field for a UseCase.
	 **************************************************************************/
	private String triggers;
	
	/**************************************************************************
	 * Preconditions field for a UseCase.
	 **************************************************************************/
	private String preconditions;
	
	/**************************************************************************
	 * Primary Flow field for a UseCase.
	 **************************************************************************/
	private String primaryflow;
	
	/**************************************************************************
	 * Alternate Flow field for a UseCase.
	 **************************************************************************/
	private String alternateflow;
	
	/**************************************************************************
	 * Minimal Guarantees field for a UseCase.
	 **************************************************************************/
	private String minimalGuarantees;
	
	/**************************************************************************
	 * Success Guarantees field for a UseCase.
	 **************************************************************************/
	private String successGuarantees;
	
	/*****************************************************************
	* Initializing default UseCase values.
	*****************************************************************/	
	public UseCase() {
		name = "Name";
		id = "Must Be Unique";
		description = "Description";
		primaryActors = "Primary Actors";
		supportingActors = "Supporting Actors";
		triggers = "Triggers";
		preconditions = "Preconditions";
		primaryflow = "Primary flow";
		alternateflow = "Alternate Flow";
		minimalGuarantees = "Minimal Guarantees";
		successGuarantees = "Success Guarantees";
	}
	
	/*****************************************************************
	* Sets the name of the UseCase.
	* 
	*  @param nameUC is the name that the UseCase name will be set to.
	*****************************************************************/
	public final void setName(final String nameUC) {
		this.name = nameUC;
	}
	
	/*****************************************************************
	* Sets the ID of the UseCase.
	* 
	*  @param idUC is the ID that the UseCase ID will be set to.
	*****************************************************************/
	public final void setID(final String idUC) {
		if (!idUC.equals("")) {
			this.id = idUC;
		} else {
			this.id = "ID Not Given";
		}
	}

	/*****************************************************************
	* Sets the description of the UseCase.
	* 
	*  @param descriptionUC is the description that the UseCase description  
	*  will be set to.
	*****************************************************************/
	public final void setDescription(final String descriptionUC) {
		this.description = descriptionUC;
	}

	/*****************************************************************
	* Sets the primary actors of the UseCase.
	* 
	*  @param primaryActorUC is the primary actor that the UseCase 
    *  primary actor will be set to.
	*****************************************************************/
	public final void setPrimaryActors(final String primaryActorUC) {
		this.primaryActors = primaryActorUC;
	}
	
	/*****************************************************************
	* Sets the supporting actors of the UseCase.
	* 
	*  @param supportingActorUC is the supporting actor that the UseCase 
	*  supporting actor will be set to.
	*****************************************************************/
	public final void setSupportingActors(final String supportingActorUC) {
		this.supportingActors = supportingActorUC;
	}

	/*****************************************************************
	* Sets the triggers of the UseCase.
	* 
	*  @param triggersUC is the trigger that the UseCase trigger will be set to.
	*****************************************************************/
	public final void setTriggers(final String triggersUC) {
		this.triggers = triggersUC;
	}

	/*****************************************************************
	* Sets the preconditions of the UseCase.
	* 
	*  @param preconditionsUC is the preconditions that the UseCase 
	*  preconditions will be set to.
	*****************************************************************/
	public final void setPreconditions(final String preconditionsUC) {
		this.preconditions = preconditionsUC;
	}

	/*****************************************************************
	* Sets the primary flow of the UseCase.
	* 
	*  @param primaryFlowUC is the primary flow that the UseCase 
	*  primary flow will be set to.
	*****************************************************************/
	public final void setPrimaryflow(final String primaryFlowUC) {
		this.primaryflow = primaryFlowUC;
	}

	/*****************************************************************
	* Sets the alternative flow of the UseCase.
	* 
	*  @param alternativeFlowUC is the alternate flow that the UseCase 
	*  alternate flow will be set to.
	*****************************************************************/
	public final void setAlternativeflow(final String alternativeFlowUC) {
		this.alternateflow = alternativeFlowUC;
	}

	/*****************************************************************
	* Sets the minimal guarantee of the UseCase.
	* 
	*  @param minimalGuaranteeUC is the minimal guarantee that the UseCase 
	*  minimal guarantee will be set to.
	*****************************************************************/
	public final void setMinimalGuarantees(final String minimalGuaranteeUC) {
		this.minimalGuarantees = minimalGuaranteeUC;
	}

	/*****************************************************************
	* Sets the success guarantee of the UseCase.
	* 
	*  @param successGuaranteeUC is the success guarantee that the UseCase
	*  success guarantee will be set to.
	*****************************************************************/
	public final void setSuccessGuarantees(final String successGuaranteeUC) {
		this.successGuarantees = successGuaranteeUC;
	}
	
	/*****************************************************************
	* Gets the name of the UseCase.
	* 
	*  @return name is that name of the UseCase
	*****************************************************************/
	public final String getName() {
		return name;
	}
	
	/*****************************************************************
	* Gets the ID of the UseCase.
	* 
	*  @return ID is that ID of the UseCase
	*****************************************************************/
	public final String getID() {
		return id;
	}

	/*****************************************************************
	* Gets the description of the UseCase.
	* 
	*  @return description is that description of the UseCase
	*****************************************************************/
	public final String getDescription() {
		return description;
	}

	/*****************************************************************
	* Gets the primary actors of the UseCase.
	* 
	*  @return primaryActors is the primary actors of the UseCase
	*****************************************************************/
	public final String getPrimaryActors() {
		return primaryActors;
	}
	
	/*****************************************************************
	* Gets the supporting actors of the UseCase.
	* 
	*  @return supportingActors is the supporting actors of the UseCase
	*****************************************************************/
	public final String getSupportingActors() {
		return supportingActors;
	}

	/*****************************************************************
	* Gets triggers of the UseCase.
	* 
	*  @return triggers are the triggers of the UseCase
	*****************************************************************/
	public final String getTriggers() {
		return triggers;
	}

	/*****************************************************************
	* Gets the preconditions of the UseCase.
	* 
	*  @return preconditions are the preconditions of the UseCase
	*****************************************************************/
	public final String getPreconditions() {
		return preconditions;
	}

	/*****************************************************************
	* Gets the primary flow of the UseCase.
	* 
	*  @return primaryflow is the primary flow of the UseCase
	*****************************************************************/
	public final String getPrimaryflow() {
		return primaryflow;
	}

	/*****************************************************************
	* Gets the alternate flow of the UseCase.
	* 
	*  @return alternateflow is the alternate flow of the UseCase
	*****************************************************************/
	public final String getAlternativeflow() {
		return alternateflow;
	}

	/*****************************************************************
	* Gets the minimal guarantees of the UseCase.
	* 
	*  @return minimalGuarantees are the minimal guarantees of the UseCase
	*****************************************************************/
	public final String getMinimalGuaruntees() {
		return minimalGuarantees;
	}

	/*****************************************************************
	* Gets the success guarantees of the UseCase.
	* 
	*  @return successGuarantees are the success guarantees of the UseCase
	*****************************************************************/
	public final String getSuccessGuarantees() {
		return successGuarantees;
	}
	
	@Override
	public final String toString() {
    	return id;
    }
}
