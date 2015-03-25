
public class Actor {
    private String name;
    private String description;

    public Actor (String n, String d) {
        name = n;
        description = d;
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

    public void setDescription(String description) {
        this.description = description;
    }

}
