package hva.app.vaccine;

import java.util.*;
import hva.core.Hotel;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show all Vaccines registered in a given VetHotel.
 */
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    Collection<Vaccine> vaccines = _receiver.getAllVaccines();
    List<Vaccine> sortedVaccines = new ArrayList<>(vaccines);
    sortedVaccines.sort((a1,a2)-> a1.getVaccineID().compareToIgnoreCase(a2.getVaccineID()));

    for(Vaccine v : sortedVaccines) {
    _display.popup(v.toString());
    }
  }
}
