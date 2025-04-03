package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateHabitatKeyException;
import hva.core.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("habitatName", Prompt.habitatName());
    addIntegerField("habitatArea", Prompt.habitatArea());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      // Chamar o método para registrar o habitat
      _receiver.registerHabitat(stringField("habitatId"), 
      stringField("habitatName"), integerField("habitatArea"));
    } catch (DuplicateHabitatKeyException e) {
      throw new hva.app.exception.DuplicateHabitatKeyException(stringField("habitatId"));
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException("habitatId");
    }
    }
}
