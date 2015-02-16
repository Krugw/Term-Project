import javax.swing.*;
import java.util.Vector;


/*****************************************************************
MyComboBoxModel creates and manages a ComboBox to be used by other
parts of our program. In its current form relies on UseCase.java;
as it's contents are generated therein. Extends the DefaultComboBox
class.
@authors
@version Summer 2015
*****************************************************************/
public class MyComboBoxModel extends DefaultComboBoxModel<UseCase> {
    public MyComboBoxModel(Vector<UseCase> useCases) {
        super(useCases);
    }
    
	/*****************************************************************
	Returns the currently selected UseCase
	@return selectedUSeCase
	*****************************************************************/	
    @Override    
    public UseCase getSelectedItem() {
        UseCase selectedUseCase = (UseCase) super.getSelectedItem();
        return selectedUseCase;
    }
}
