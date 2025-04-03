package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Species;
import hva.core.exception.UnknownHabitatKeyException;
import hva.core.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
    addOptionField("habInfluence", Prompt.habitatInfluence(), "POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
      Habitat habitat;
      Species species;

      try {
        habitat = _receiver.findHabitatByID(stringField("habitatId"));
      } catch (UnknownHabitatKeyException e) {
        throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
      }
  
      try {
        species = _receiver.findSpeciesByID(stringField("speciesId"));
      } catch (UnknownSpeciesKeyException e) {
        throw new hva.app.exception.UnknownSpeciesKeyException(stringField("speciesId"));
      }
  
      habitat.setInfluence(species, optionField("habInfluence"));
      }
    }
