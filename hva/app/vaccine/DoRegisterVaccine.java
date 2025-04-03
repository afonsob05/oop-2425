package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownSpeciesKeyException;
import hva.core.exception.UnknownVaccineKeyException;
import hva.core.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    // Solicitar informações ao usuário
    String vaccineId = Form.requestString(Prompt.vaccineKey());
    String name = Form.requestString(Prompt.vaccineName());
    String speciesIdsString = Form.requestString(Prompt.listOfSpeciesKeys());

    // Dividir a string de IDs das espécies em um array de strings
    String[] speciesList = speciesIdsString.split(",");

    try {
      // Chamar o método para registrar a vacina
      _receiver.registerVaccine(vaccineId, name, speciesList);
    } catch (DuplicateVaccineKeyException e) {
      throw new hva.app.exception.DuplicateVaccineKeyException(vaccineId);
    } catch (UnknownSpeciesKeyException e) {
      throw new hva.app.exception.UnknownSpeciesKeyException(e.getMessage());
    } 
  }
}
