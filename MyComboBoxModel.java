import javax.swing.*;
import java.util.Vector;

public class MyComboBoxModel extends DefaultComboBoxModel<UseCase> {
    public MyComboBoxModel(Vector<UseCase> useCases) {
        super(useCases);
    }
    @Override
    public UseCase getSelectedItem() {
        UseCase selectedUseCase = (UseCase) super.getSelectedItem();
        return selectedUseCase;
    }
}
