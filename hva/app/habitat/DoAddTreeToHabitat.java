package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat;
import hva.core.Tree;
import hva.core.exception.UnknownHabitatKeyException;
import hva.core.exception.DuplicateTreeKeyException;
import hva.core.exception.InvalidTreeTypeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitatId", Prompt.habitatKey());
    addStringField("treeId", Prompt.treeKey());
    addStringField("treeName", Prompt.treeName());
    addIntegerField("treeAge", Prompt.treeAge());
    addIntegerField("treeDifficulty", Prompt.treeDifficulty());
    addOptionField("treeType", Prompt.treeType(), "PERENE", "CADUCA");
  }
  
  @Override
  protected void execute() throws CommandException {
    
    try {
      Habitat habitat = _receiver.findHabitatByID(stringField("habitatId"));
      _receiver.createTree(stringField("treeId"), stringField("treeName"), 
        optionField("treeType"), integerField("treeAge"), integerField("treeDifficulty"));
      Tree tree = _receiver.findTreeByID(stringField("treeId"));
      habitat.addTree(tree);
      _display.popup(tree.toString());
    } catch (UnknownHabitatKeyException e) {
      throw new hva.app.exception.UnknownHabitatKeyException(stringField("habitatId"));
    } catch (DuplicateTreeKeyException e) {
      throw new hva.app.exception.DuplicateTreeKeyException(stringField("treeId"));
    } catch (InvalidTreeTypeException e) {
      throw new hva.app.exception.UnknownTreeKeyException(optionField("treeType"));
    }
  }
}
