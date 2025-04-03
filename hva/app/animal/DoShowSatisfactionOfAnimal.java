package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("animalId", Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String animalId = stringField("animalId");
    try {
      Animal animal = _receiver.findAnimalByID(animalId);
      _display.addLine(animal.calculateSatisfaction());
      _display.display();
    } catch (UnknownAnimalKeyException e) {
      throw new hva.app.exception.UnknownAnimalKeyException(animalId);
    }
  }
}
