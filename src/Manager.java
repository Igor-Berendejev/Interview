import java.time.LocalDate;
import java.util.List;

public class Manager extends Employee {
    List<String> subordinates;

    public Manager(String name, LocalDate birthday, LocalDate dateHired, List<String> subordinates) {
        super(name, birthday, dateHired);
        this.subordinates = subordinates;
    }

    public List<String> getSubordinates() {
        return subordinates;
    }
}
