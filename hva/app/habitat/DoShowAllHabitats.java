package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat;
import java.util.*;
import pt.tecnico.uilib.menus.Command;


/**
 * Show all Habitats registered in a given VetHotel.
 */
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    // Retrieve the list of habitats from the hotel
        Collection<Habitat> habitats = _receiver.getAllHabitats();
        List<Habitat> sortedHabitats = new ArrayList<>(habitats);
        sortedHabitats.sort((a1,a2)-> a1.getHabitatID().compareToIgnoreCase(a2.getHabitatID()));

        for(Habitat h : sortedHabitats) {

        // Show the habitats to the user
        _display.popup(h.toString());
    }
  }
}
