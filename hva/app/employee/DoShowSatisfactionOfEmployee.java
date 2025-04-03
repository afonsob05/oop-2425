package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeId", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      Employee employee = _receiver.findEmployeeByID(stringField("employeeId"));
      _display.addLine(employee.satisfaction());
      _display.display();
    } catch (UnknownEmployeeKeyException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(stringField("employeeId"));
    }
  }
}
