package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalKeyException;
import hva.core.exception.UnknownEmployeeKeyException;
import hva.core.exception.UnknownVaccineKeyException;
import hva.core.exception.UnknownVeterinarianKeyException;
import hva.core.exception.VaccineDamageException;
import hva.core.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccineID", Prompt.vaccineKey());
    addStringField("vetID", Prompt.veterinarianKey());
    addStringField("animalID", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException { 
    try {
      _receiver.vaccinate(stringField("vaccineID"), stringField("vetID"), stringField("animalID"));
    } catch (UnknownVeterinarianKeyException e) {
      throw new hva.app.exception.UnknownVeterinarianKeyException(stringField("vetID"));
    } catch (VeterinarianNotAuthorizedException e) {
      throw new hva.app.exception.VeterinarianNotAuthorizedException(e.getMessage(), stringField("vetID"));
    } catch (UnknownAnimalKeyException e) {
      throw new hva.app.exception.UnknownAnimalKeyException(stringField("animalID"));
    } catch (UnknownVaccineKeyException e) {
      throw new hva.app.exception.UnknownVaccineKeyException(stringField("vaccineID"));
    } catch (UnknownEmployeeKeyException e) {
      throw new hva.app.exception.UnknownEmployeeKeyException(e.getMessage());
    } catch (VaccineDamageException e) {
      _display.popup(hva.app.vaccine.Message.wrongVaccine(stringField("vaccineID"), stringField("animalID")));
    }
  }
}
