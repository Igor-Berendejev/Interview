import java.time.LocalDate;

public class OtherEmployee extends Employee{
    private String description;

    public OtherEmployee(String name, LocalDate birthday, LocalDate dateHired, String description) {
        super(name, birthday, dateHired);
        this.description = description;
    }
}
