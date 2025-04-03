package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addIntegerField("habitatArea", Prompt.habitatArea());

  }
  
  @Override
  protected void execute() throws CommandException {
    Habitat h = null;
    try {
      h = _receiver.findHabitatByID(stringField("habitatId"));
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
    }
    h.setArea(integerField("habitatArea"));
  }
}
