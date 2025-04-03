package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.core.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownSpeciesKeyException;
import hva.core.exception.NoResponsibilityException;
import hva.core.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employeeId", Prompt.employeeKey());
    addStringField("responsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.removeResponsibility(stringField("employeeId"), stringField("responsibility"));
    } catch (UnknownEmployeeKeyException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(stringField("employeeId"));
    } catch (NoResponsibilityException e) {
      throw new hva.app.exception.NoResponsibilityException(stringField("employeeId"), stringField("responsibility"));
    } catch (UnknownSpeciesKeyException e) {
      throw new hva.app.exception.UnknownSpeciesKeyException(stringField("responsibility"));

    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("responsibility"));
      
    } catch (InvalidEmployeeTypeException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(stringField("employeeId"));
    }
  }
}
