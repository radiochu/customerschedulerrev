package Model;

public class Contacts {
    public String name;
    public int id;

    public Contacts(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
