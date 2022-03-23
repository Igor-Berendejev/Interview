import java.time.LocalDate;

public abstract class Employee {
    private String name;
    private LocalDate birthday;
    private LocalDate dateHired;

    public Employee(String name, LocalDate birthday, LocalDate dateHired) {
        this.name = name;
        this.birthday = birthday;
        this.dateHired = dateHired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }
}
