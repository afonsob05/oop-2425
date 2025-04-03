package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyException;
import hva.core.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("animalId", Prompt.animalKey());
    addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.transferHabitat(stringField("animalId"), stringField("habitatId"));
    } catch (UnknownAnimalKeyException e) {
      throw new hva.app.exception.UnknownAnimalKeyException(stringField("animalId"));
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
    }
  }
}

