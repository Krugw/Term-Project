
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;


/*****************************************************************
MyComboBoxModel creates and manages a ComboBox to be used by other
parts of our program. In its current form relies on UseCase.java;
as it's contents are generated therein. Extends the DefaultComboBox
class.
authors Wesley Krug, Gabriel Steponovich, 
         Michael Brecker, Halston Raddatz
@version Winter 2015
*****************************************************************/
public class MyComboBoxModel extends DefaultComboBoxModel<UseCase> {
    /**
	 * version id
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param useCases
     */
    public MyComboBoxModel(final Vector<UseCase> useCases) {
        super(useCases);
    }
    
	/*****************************************************************
	Returns the currently selected UseCase.
	@return selectedUSeCase
	*****************************************************************/	
    @Override
	public final UseCase getSelectedItem() {
        UseCase selectedUseCase = (UseCase) super.getSelectedItem();
        return selectedUseCase;
    }
}
