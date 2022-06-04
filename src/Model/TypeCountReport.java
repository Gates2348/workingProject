package Model;

/**
 * type count report class sets type and the count of that type
 */
public class TypeCountReport {
    private String type;
    private int count;

    /**
     * this is the constructor for the class
     * @param type sets type to string
     * @param count sets count to int
     */
    public TypeCountReport(String type, int count) {
        this.type = type;
        this.count = count;
    }

    /**
     * gets type
     * @return returns type
     */
    public String getType() {
        return type;
    }

    /**
     * sets type
     * @param type sets type to string
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets count
     * @return returns count
     */
    public int getCount() {
        return count;
    }

    /**
     * sets count
     * @param count sets count to int
     */
    public void setCount(int count) {
        this.count = count;
    }
}
