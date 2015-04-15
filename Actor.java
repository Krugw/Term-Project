import java.util.ArrayList;

public class Actor {
    private String name;
    private String description;
    private ArrayList<UseCase> useCases;

    public Actor (String n, String d) {
    	useCases = new ArrayList<UseCase>();
        name = n;
        description = d;
    }
    public final void addUsecase(final UseCase uc) {
        for (UseCase uc2 : useCases) {
            if (uc2.getID().equals(uc.getID())) {
                useCases.remove(uc2);
                useCases.add(uc);
                return;
            }
        }
        useCases.add(uc);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public String toString(){
    	String out = "";
    	out += getName() + ",";
    	out += getDescription() + ",";
    	for(UseCase uc : useCases){
    		out += uc.getID() + ",";
    	}
    	return out;
    }
    public void setDescription(String description) {
        this.description = description;
    }
	public void removeUseCase(UseCase uc) {
		useCases.remove(uc);
	}

}
