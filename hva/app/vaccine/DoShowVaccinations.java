package hva.app.vaccine;

import java.util.*;
import hva.core.Hotel;
import hva.core.VaccineEvent;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    _display.popup(_receiver.getAllEvents());
  }
}
