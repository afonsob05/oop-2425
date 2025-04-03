package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    try {
            // Check if the hotel has an associated filename
            if (_receiver.getFilename() == null) {
                _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
            }
            else _receiver.save();
        } catch (MissingFileAssociationException e) {
            // Provide a clear message when there's no file associated
            _display.popup("No file associated with the hotel. Please provide a filename.");
        } catch (IOException e) {
            // Handle I/O exceptions during save
            _display.popup("An error occurred while saving the file: " + e.getMessage());
        }
  }
}
