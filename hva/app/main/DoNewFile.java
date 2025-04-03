package hva.app.main;

import com.sun.net.httpserver.Request;
import hva.core.HotelManager;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() {
    // Check if there are unsaved changes
    if (!_receiver.getHotel().getSaveState()) {
      // Prompt the user to save before creating a new file
      Boolean saveBeforeExit = Form.confirm(Prompt.saveBeforeExit());

      // If the user chooses to save, trigger the save action
      if (saveBeforeExit) {
        new DoSaveFile(_receiver).execute();
    }

    // Initialize a new hotel (resetting the current one)
    _receiver.createNewHotel();
    
    // Clear the file association (since this is a new file)
    _receiver.clearFileAssociation();

    // Mark that the application now has unsaved changes (new hotel created)
    _receiver.getHotel().markAsSaved();
  }
  }
}

