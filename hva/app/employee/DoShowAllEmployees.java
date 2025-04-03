package hva.app.employee;

import java.util.*;
import hva.core.Employee;
import hva.core.Hotel;
import hva.core.Keeper;
import hva.core.Vet;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all Employees registered in a given VetHotel.
 */
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
    Collection<Employee> employees = _receiver.getAllEmployees();
    List<Employee> sortedEmployees = new ArrayList<>(employees);
    sortedEmployees.sort((a1, a2) -> a1.getEmployeeID().compareToIgnoreCase(a2.getEmployeeID()));

    for (Employee employee : sortedEmployees) {
      _display.popup(employee.employeeString());
    }
  }
}
