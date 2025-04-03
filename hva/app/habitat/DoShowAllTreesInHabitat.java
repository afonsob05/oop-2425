package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Tree;
import java.util.*;
import hva.core.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    try {
      Habitat habitat = _receiver.findHabitatByID(habitatId);
      Collection<Tree> trees = habitat.getHabitatTrees();
      List<Tree> sortedTrees = new ArrayList<>(trees);
      sortedTrees.sort((t1, t2) -> t1.getTreeID().compareToIgnoreCase(t2.getTreeID()));

      for (Tree tree : sortedTrees) {
        _display.popup(tree.toString());
      }
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(habitatId);
    }
  }
}
