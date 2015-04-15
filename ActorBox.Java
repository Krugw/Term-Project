
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
public class ActorBox extends DefaultComboBoxModel<String> {
    /**
	 * version id
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param useCases
     */
    public ActorBox(final Vector<String> aName) {
        super(aName);
    }
    

	/*****************************************************************
	Returns the currently selected UseCase.
	@return selectedUSeCase
	*****************************************************************/	
    @Override
	public final String getSelectedItem() {
        String selectedName = (String) super.getSelectedItem();
        return selectedName;
    }
}
