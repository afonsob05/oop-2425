package hva.app.search;

import hva.core.Animal;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
      Habitat habitat = _receiver.findHabitatByID(stringField("habitatId"));
      for (Animal animal : habitat.getHabitatAnimals()) {
        _display.addLine(animal.toString());
      }
      _display.display();
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
    }
  }
}
