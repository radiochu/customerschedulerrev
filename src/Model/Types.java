package Model;

public class Types {
    public String month;
    public String type;
    public int count;

    public Types(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public String getMonth() {
        return month;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }
}
