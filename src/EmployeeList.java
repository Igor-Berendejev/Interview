import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeList {
    private List<Employee> employeeSet;

    public boolean addEmployee() {
        ArrayList<String> employeeParameters = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/DD");
        System.out.println("Please type a path to employee data file");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            Path employeeDataPath = Paths.get(fileName);
            try (BufferedReader employeeDataReader = Files.newBufferedReader(employeeDataPath, StandardCharsets.US_ASCII)) {
                String parameter;
                // assume that employee type, name, date of birth, date when hired and any other data are
                // on separate lines in the file and dates are written in format YYYY-MM-DD
                while ((parameter = employeeDataReader.readLine()) != null) {
                    employeeParameters.add(parameter);
                }
                if (employeeParameters.get(0).toLowerCase().equals("worker")) {
                    return employeeSet.add(new Worker(employeeParameters.get(1),
                            LocalDate.parse(employeeParameters.get(2), formatter),
                            LocalDate.parse(employeeParameters.get(3), formatter)));
                } else if (employeeParameters.get(0).toLowerCase().equals("manager")) {
                    String[] subordinates = employeeParameters.get(4).split(",");
                    return employeeSet.add(new Manager(employeeParameters.get(1),
                            LocalDate.parse(employeeParameters.get(2), formatter),
                            LocalDate.parse(employeeParameters.get(3), formatter),
                            Arrays.asList(subordinates)));
                } else return employeeSet.add(new OtherEmployee(employeeParameters.get(1),
                        LocalDate.parse(employeeParameters.get(2), formatter),
                        LocalDate.parse(employeeParameters.get(3), formatter),
                        employeeParameters.get(4)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeEmployee(Employee employee){
        return employeeSet.remove(employee);
    }

    public boolean linkToManager(Employee employee, Manager manager){
        if (!employeeSet.contains(manager)) return false;
        return manager.getSubordinates().add(employee.getName());
    }

    public void changeEmployeeType(Employee employee){
        if (employeeSet.contains(employee)){
            employeeSet.remove(employee);
            addEmployee();
        }
        else System.out.println("The employee is not in the list");
    }

    public void sortByName(){
        Collections.sort(employeeSet, new Comparator <Employee>(){
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void sortByHireDate(){
        Collections.sort(employeeSet, new Comparator <Employee>(){
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getDateHired().compareTo(o2.getDateHired());
            }
        });
    }
}
