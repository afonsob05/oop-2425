package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * 
 * Class representing the App manager. Manipulates the current VetHotel
 * 
 */


/*Hotel is being inicialized, as well as a filename field.
 * Filename will be used for saving purposes.
*/
public class HotelManager implements  Serializable {
  private Hotel _hotel = new Hotel();
  private String _filename;
 
   /* Creation of a new Hotel, rewriting the data of the previous Hotel.*/
  public void createNewHotel() {
      _hotel = new Hotel();
  }

  public void advanceSeason() {
    _hotel.advanceSeason();
  }

  public Season getCurrentSeason() {
    return _hotel.getCurrentSeason();
  }

  /*Ending the association with a File previously being used*/
  public void clearFileAssociation() {
    _filename = null;
  }
  
  /**
   * Gets the filename associated with the current hotel.
   *
   * @return the filename
   * @throws MissingFileAssociationException if no file is associated with the current hotel.
   */
  public String getFilename() throws MissingFileAssociationException {
    return _filename;
  } 

  /**save
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   * @throws IOException
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_hotel.getSaveState()) {
      System.out.println("No unsaved changes, skipping save.");
      return;
    }
    if (_filename == null || _filename.isEmpty()) {
      throw new MissingFileAssociationException();
    }
    try (FileOutputStream fileOut = new FileOutputStream(_filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
      out.writeObject(_hotel);
      _hotel.markAsSaved();
    }
}

  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException 
   * @throws MissingFileAssociationException
   * @throws IOException
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (filename == null || filename.isEmpty()) {
      throw new MissingFileAssociationException();
    }
    _filename = filename;
    save();
  }

  /**load
   * Loads up a pre-made file into the App.
   * 
   * @param filename name of the file containing the serialized application's state
   * to load.
   * @throws UnavailableFileException
   **/
  public void load(String filename) throws UnavailableFileException {
    try (FileInputStream fileInputStream = new FileInputStream(filename);
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

        _hotel = (Hotel) objectInputStream.readObject(); 
        _hotel.markAsSaved();
        _filename = filename;

    } catch (FileNotFoundException e) {
        throw new UnavailableFileException(filename);
    } catch (IOException e) {
        throw new UnavailableFileException(filename);
    } catch (ClassNotFoundException e) {
        throw new UnavailableFileException(filename);
    }
  }

  /**importFile
   * 
   * Read text input file and initializes the current VetHotel 
   * (which should be empty) with the domain entities
   *  representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   **/
  public void importFile(String filename) throws ImportFileException {
    File file = new File(filename);

    if (!file.exists() || file.length() == 0) {
        throw new ImportFileException(filename);

    }

    try {
        _hotel.importFile(filename);

    } catch (Exception e) {
        throw new ImportFileException("Unexpected error occurred: " + filename, e);
    }
}

  /**
   * Returns the VetHotel managed by this instance.
   *
   * @return the current VetHotel.
   **/
  public final Hotel getHotel() {
    return _hotel;
  }
}