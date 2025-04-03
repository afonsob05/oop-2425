package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import hva.core.exception.ImportFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      // Open a file dialog to select a file
      String filename = Form.requestString(Prompt.openFile());
      // Check if there are unsaved changes
      if (!_receiver.getHotel().getSaveState()) {
        // Prompt the user to save before opening a new file
        Boolean saveBeforeExit = Form.confirm(Prompt.saveBeforeExit());

        // If the user chooses to save, call DoSaveFile to handle the saving process
        if (saveBeforeExit) {
          new DoSaveFile(_receiver).execute();
        }
      }
      // Use the receiver (HotelManager) to handle the file opening and importing
      _receiver.load(filename); // This will invoke the import logic

    } catch (UnavailableFileException ex) {
      // Handle the UnavailableFileException by displaying a message or logging
      System.err.println("Failed to open file: " + ex.getMessage());
      // Optionally throw a custom exception or handle it further
      throw new FileOpenFailedException(ex);
    } catch (Exception ex) {
      // Handle any other unexpected exceptions
      System.err.println("An unexpected error occurred: " + ex.getMessage());
    }
  }
}
