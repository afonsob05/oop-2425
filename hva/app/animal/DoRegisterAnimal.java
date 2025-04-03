package hva.app.animal;

import hva.core.Hotel;
import hva.core.Species;
import hva.core.exception.DuplicateAnimalKeyException;
import hva.core.exception.UnknownHabitatKeyException;
import hva.core.exception.DuplicateSpeciesKeyException;
import hva.core.exception.UnknownSpeciesKeyException;
import hva.core.exception.UnrecognizedEntryException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    // Solicitar informações ao usuário
    String animalId = Form.requestString(Prompt.animalKey());
    String name = Form.requestString(Prompt.animalName());
    String speciesID = Form.requestString(Prompt.speciesKey());
    String habitatID = Form.requestString(hva.app.habitat.Prompt.habitatKey()); 
    String healthHistory = "VOID";

    try {
      // Verifica se a espécie existe
      Species species;
      try {
        species = _receiver.findSpeciesByID(speciesID);
      } catch (UnknownSpeciesKeyException e) {
        // Se a espécie não existir, pedir o nome e registrar a espécie
        String speciesName = Form.requestString(Prompt.speciesName());
        if (_receiver.isValidSpeciesId(speciesID)) {
          try {
          _receiver.registerSpecies(speciesID, speciesName);  // Registra a espécie
          species = _receiver.findSpeciesByID(speciesID);  // Obter a nova espécie
        } catch (DuplicateSpeciesKeyException ex) {
          throw new hva.app.exception.UnknownSpeciesKeyException(speciesID);
        } catch (UnrecognizedEntryException ex) {
          throw new hva.app.exception.UnknownAnimalKeyException(animalId);
        }
      }else {
          throw new UnknownSpeciesKeyException(speciesID);
        }
      }

      // Chamar o método para registrar o animal
      _receiver.registerAnimal(animalId, name, habitatID, healthHistory, speciesID);
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(habitatID);
    } catch (UnknownSpeciesKeyException e) {
      throw new hva.app.exception.UnknownSpeciesKeyException(speciesID);
    } catch (DuplicateAnimalKeyException e) {
      throw new hva.app.exception.DuplicateAnimalKeyException(animalId);
    } catch (UnrecognizedEntryException ex) {
      throw new hva.app.exception.UnknownAnimalKeyException(animalId);
    }
  }
}

