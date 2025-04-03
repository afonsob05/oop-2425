package hva.app.animal;

import hva.core.Hotel;
import java.util.*;
import hva.core.Animal;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all Animals registered in a given VetHotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
      Collection<Animal> animals = _receiver.getAllAnimals();
      List<Animal> sortedAnimals = new ArrayList<>(animals);
      sortedAnimals.sort((a1,a2)-> a1.getAnimalID().compareToIgnoreCase(a2.getAnimalID()));
      
      _display.popup(sortedAnimals);
  }
}
