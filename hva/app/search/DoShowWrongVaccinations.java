package hva.app.search;

import java.util.*;
import hva.core.Hotel;
import hva.core.VaccineEvent;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
      List<VaccineEvent> allFaulty = _receiver.showAllFaultyVaccines();

      for(VaccineEvent v : allFaulty) {
        _display.popup(v.toString());
      }
  }
}
