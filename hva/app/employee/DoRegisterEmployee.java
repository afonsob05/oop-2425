package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeKeyException;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.core.exception.UnrecognizedEntryException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("employeeName", Prompt.employeeName());
    addOptionField("empType", Prompt.employeeType(), "VET", "TRT");
  }
  
  @Override
  protected void execute() throws CommandException {

    try {
      _receiver.registerEmployee(stringField("employeeId"), stringField("employeeName"),
       optionField("empType"));
    } catch (DuplicateEmployeeKeyException e) {
      throw new hva.app.exception.DuplicateEmployeeKeyException(stringField("employeeId"));
    } catch (InvalidEmployeeTypeException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(optionField("empType"));
    } catch (UnrecognizedEntryException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(stringField("employeeId"));
    }
  }
}
