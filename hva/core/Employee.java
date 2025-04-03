package hva.core;

import java.io.Serializable;
/*
 *
 * Represents an Employee working in the hotel.
 * This class will have 2 subclasses, Vet and Keeper.
 * 
*/
public abstract class Employee implements Serializable, Comparable<Employee>{
    private String _employeeID;
    private String _employeeName;
    private String _employeeType;


    /*
     *
     * Class Constructor - must be set to protected to allow compatibility
     * with this Class's subclasses.
     *
    */
    Employee(String empID, String empName){
        _employeeID = empID;
        _employeeName = empName;
    }

    /*
     *
     * Abstract methods - have different implementations based
     * on the Employee type. 
     *
    */
    public abstract int satisfaction();
    public abstract void addResponsibility(String responsibility, Hotel hotel);
    public abstract void removeResponsibility(String responsibility);
    public abstract String employeeString();
    public abstract String getResponsibility();

    /*
     * 
     * Common methods to all subclasses of Employee.
     * 
    */
    public void setType(String type) { _employeeType = type; }
    public String getEmployeeID(){ return _employeeID; }
    public String getEmpType() { return _employeeType; }
    public String getEmployeeName() {return _employeeName;}

    
    @Override
    public int compareTo(Employee other) {
        return this.getEmployeeID().compareTo(other.getEmployeeID()); // Compare by employee ID
    }

}
