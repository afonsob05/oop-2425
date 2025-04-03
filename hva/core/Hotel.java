package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * The {@code Hotel} Represents a VetHotel.
 * Provides methods for registering and managing animals, species, employees, vaccines, and habitats.
 * Tracks state changes and supports file import for given data input.
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;


  /**
   * 
   * Hotel Attributes - Collections for all Classes inside Hotel;
   * @param {_hasSavedChanges} State Stracker;
   * 
   */
  private Collection<Habitat> _allHabitats = new TreeSet<>();
  private Collection<Animal> _allAnimals = new TreeSet<>();
  private Collection<Tree> _allTrees = new TreeSet<>();
  private Collection<Species> _allSpecies = new TreeSet<>(); 
  private Collection<Employee> _allEmployees = new TreeSet<>();
  private Collection<Vaccine> _allVacines = new TreeSet<>();
  private List<VaccineEvent> _vaccineHistory = new ArrayList<>();
  private boolean _hasSavedChanges = true;
  private Season _currentSeason = Season.SPRING;
  private Season _prevSeason;

  public void advanceSeason() {
    _currentSeason = _currentSeason.nextSeason();

    for (Tree tree : _allTrees) {
      tree.advanceBioCycle(_currentSeason);
  }
    markAsUnsaved();
  }

  /** getSeason
   * 
   * @return current Season the hotel is in.
  */
  public Season getCurrentSeason() {
    return _currentSeason;
  }



  /**
   * State trackers
   */

  public void markAsUnsaved() { _hasSavedChanges = false;}

  public void markAsSaved() { _hasSavedChanges = true; }

  public boolean getSaveState() { return _hasSavedChanges; }



  /**
   * Structure Gets
  */
  public Collection<Habitat> getAllHabitats() { return _allHabitats;}

  public Collection<Animal> getAllAnimals() { return _allAnimals; }

  public Collection<Tree> getAllTrees() {return _allTrees; }

  public Collection<Species> getAllSpecies() { return _allSpecies; }

  public Collection<Employee> getAllEmployees() { return _allEmployees;}

  public Collection<Vaccine> getAllVaccines() { return _allVacines;}

  public List<VaccineEvent> getAllEvents() { return _vaccineHistory; }
  


  /**
   * Calculate the global satisfaction of all animals and employees.
   *
   * @return the global satisfaction rounded to the nearest integer
   */
  public int calculateGlobalSatisfaction() {
    double totalSatisfaction = 0;

    for (Animal animal : _allAnimals) {
      totalSatisfaction += animal.calculateSatisfaction();
    }

    for (Employee employee : _allEmployees) {
      totalSatisfaction += employee.satisfaction();
    }

    return (int) Math.round(totalSatisfaction);
  }

  /** registerAnimal
   * 
   * create an Animal, and assign it to an habitat. Later on, he's also kept
   * track inside allAnimals, a list that contains all hotel animals.
   * 
   * @param animalId
   * @param name
   * @param habitatID
   * @param healthHistory
   * @param speciesID
   * @throws UnknownHabitatKeyException
   * @throws UnknownSpeciesKeyException
   * @throws DuplicateAnimalKeyException
   * @throws UnrecognizedEntryException
   */

  public void registerAnimal(String animalId, String name, String habitatID, String healthHistory, String speciesID) 
  throws UnknownHabitatKeyException, UnknownSpeciesKeyException, 
  DuplicateAnimalKeyException, UnrecognizedEntryException {

    Habitat habitat = findHabitatByID(habitatID);
    if (!isValidHabitatId(habitatID)) {
        throw new UnknownHabitatKeyException(habitatID);
    }

    Species species = findSpeciesByID(speciesID);
    if (species == null) {
        throw new UnknownSpeciesKeyException(speciesID);
    }

    for (Animal animal : getAllAnimals()) {
        if (animal.getAnimalID().equals(animalId)) {
            throw new DuplicateAnimalKeyException(animalId);
        }
    }

    Animal animal = new Animal( animalId, name, species, healthHistory, habitat);
    habitat.addAnimal(animal, habitat);
    species.addAnimal(animal);
    _allAnimals.add(animal);
    markAsUnsaved();
}

  /** isValidSpeciesId
   * given an ID, the method verifies if an ID is considered valid for species.
   * 
   * @param id
   * @return boolean
   */
  public boolean isValidSpeciesId(String id) {

    return id != null && id.matches("^[a-zA-Z0-9]{1,10}$");
  }

  /** isValidHabitatId
   * given an ID, the method checks if an ID is considered valid for habitat.
   * 
   * @param id
   * @return boolean
   */
  public boolean isValidHabitatId(String id) {

    return id != null && id.matches("^[a-zA-Z0-9]{1,10}$");
  }

  /** findSpeciesByID 
   * Given an ID, the method finds the specie name associated to it.
   * @param speciesId
   * @return name of Specie
   * @throws UnknownSpeciesKeyException
   */
  public String findSpeciesNameByID(String speciesId) 
    throws UnknownSpeciesKeyException {
    Species species = findSpeciesByID(speciesId);

    return species.getSpeciesName();
    }

  public void registerSpecies(String speciesId, String name) 
    throws DuplicateSpeciesKeyException, UnrecognizedEntryException {
    // Validate species Id
    if (!isValidSpeciesId(speciesId)) {
            throw new UnrecognizedEntryException("Invalid species ID: " + speciesId);
        }

    for (Species species : _allSpecies) {  // Assuming _speciesList exists
        if (species.getSpeciesID().equals(speciesId)) {
            throw new DuplicateSpeciesKeyException(speciesId);
        }
    }

    Species species = new Species(speciesId, name);
    _allSpecies.add(species);
    markAsUnsaved();
}

  /**
   * This method checks if the Employee already exists, if not creates it
   * and stores it in the hotel. The Employee created may differ based on 
   * the "empType" field.
   * 
   * @param employeeId
   * @param name
   * @param empType
   * @throws DuplicateEmployeeKeyException
   * @throws InvalidEmployeeTypeException
   * @throws UnrecognizedEntryException
   */

  public void registerEmployee(String employeeId, String name, String empType) 
  throws DuplicateEmployeeKeyException, 
  InvalidEmployeeTypeException, UnrecognizedEntryException {

    for (Employee employee : _allEmployees) {
        if (employee.getEmployeeID().equals(employeeId)) {
            throw new DuplicateEmployeeKeyException(employeeId);
        }
    }
    if (empType.equalsIgnoreCase("VET")) {
      Vet vet = new Vet(employeeId, name, empType);
      SatisfactionStrategy strategy = new VetSatisfactionStrategy(vet);
      vet.setSatisfactionStrategy(strategy);
      _allEmployees.add(vet);
      markAsUnsaved();
    } else if (empType.equalsIgnoreCase("TRT")) {
      Keeper keeper = new Keeper(employeeId, name, empType);
      SatisfactionStrategy strategy = new KeeperSatisfactionStrategy(keeper);
      keeper.setSatisfactionStrategy(strategy);
      _allEmployees.add(keeper);
      markAsUnsaved();
    } else {
        throw new InvalidEmployeeTypeException(empType);
    }
}

  /**addResponsibility
   * 
   * given an EmployeeID and a responsibility(habitatID or speciesID),
   * that very responsibility is stored within the employee's correspondant
   * Collection of responsibilities.
   * 
   * @param employeeId
   * @param responsibility
   * @throws UnknownEmployeeKeyException
   * @throws NoResponsibilityException
   * @throws UnknownSpeciesKeyException
   * @throws UnknownHabitatKeyException
   * @throws InvalidEmployeeTypeException
   */
  public void addResponsibility(String employeeId, String responsibility) 
  throws UnknownEmployeeKeyException, NoResponsibilityException,
  UnknownSpeciesKeyException, UnknownHabitatKeyException, InvalidEmployeeTypeException{

    Employee employee = findEmployeeByID(employeeId);
    if (employee == null) {
        throw new UnknownEmployeeKeyException(employeeId);
    }
    if (employee instanceof Vet) {
        Species species = findSpeciesByID(responsibility);
        if (species == null) {
            throw new UnknownSpeciesKeyException(responsibility);
        }
        ((Vet) employee).addResponsibility(responsibility, this);
        ((Vet) employee).addSpeciesToMap(responsibility, species);
        markAsUnsaved();
    } else if (employee instanceof Keeper) {

        Habitat habitat = findHabitatByID(responsibility);
        if (habitat == null) {
            throw new UnknownHabitatKeyException(responsibility);
        }
        ((Keeper) employee).addResponsibility(responsibility, this);
        ((Keeper) employee).addHabitatToMap(responsibility, habitat);
        markAsUnsaved();
    } else {
        throw new InvalidEmployeeTypeException(employee.getClass().getSimpleName());
    }
}

  /**removeResponsibility
   * 
   * given an EmployeeID and a responsibility(habitatID or speciesID),
   * the responsibility given is removed from the employee's correspondant
   * Collection of responsibilities.
   * 
   * @param employeeId
   * @param responsibility
   * @throws UnknownEmployeeKeyException
   * @throws NoResponsibilityException
   * @throws UnknownSpeciesKeyException
   * @throws UnknownHabitatKeyException
   * @throws InvalidEmployeeTypeException
   */
  public void removeResponsibility(String employeeID, String responsibility)   
    throws UnknownEmployeeKeyException, NoResponsibilityException, 
    UnknownSpeciesKeyException, UnknownHabitatKeyException, InvalidEmployeeTypeException{

  Employee employee = findEmployeeByID(employeeID);
  if (employee == null) {
      throw new UnknownEmployeeKeyException(employeeID);
  }
  if (employee instanceof Vet) {
      Species species = findSpeciesByID(responsibility);
      if (species == null) {
          throw new UnknownSpeciesKeyException(responsibility);
      }
      ((Vet) employee).removeResponsibility(responsibility);
      markAsUnsaved();
  } else if (employee instanceof Keeper) {

      Habitat habitat = findHabitatByID(responsibility);
      if (habitat == null) {
          throw new UnknownHabitatKeyException(responsibility);
      }
      ((Keeper) employee).removeResponsibility(responsibility);
      markAsUnsaved();
  } else {
      throw new InvalidEmployeeTypeException(employee.getClass().getSimpleName());
  }
}

  /** registerVaccine
   * 
   * A vaccine is created, and stored within the hotel's total vaccine database.
   * 
   * @param vaccineId
   * @param name
   * @param speciesIds
   * @throws DuplicateVaccineKeyException
   * @throws UnknownSpeciesKeyException
   */
  public void registerVaccine(String vaccineId, String name, String[] speciesIds) 
    throws DuplicateVaccineKeyException, UnknownSpeciesKeyException {
    for (Vaccine vaccine : getAllVaccines()) {
        if (vaccine.getVaccineID().equalsIgnoreCase(vaccineId)) {
            throw new DuplicateVaccineKeyException(vaccineId);
        }
    }

    List<Species> speciesList = new ArrayList<>();
    for (String speciesId : speciesIds) {
        Species species = findSpeciesByID(speciesId.trim());
        if (species == null) {
            throw new UnknownSpeciesKeyException(speciesId);
        }
        speciesList.add(species);
    }
    Vaccine vaccine = new Vaccine(vaccineId, name, speciesList);
    _allVacines.add(vaccine);
    markAsUnsaved();
}


  /** createTree
   * 
   * checks if Tree already exists, if not, add it to the hotel's
   * total tree database. Creation of tree may differ dependent on
   * the "type" given to the method.
   * 
   * @param treeId
   * @param name
   * @param type
   * @param age
   * @param diff
   * @throws DuplicateTreeKeyException
   * @throws InvalidTreeTypeException
   */
  public void createTree(String treeId, String name, String type, int age, int diff) 
    throws DuplicateTreeKeyException, InvalidTreeTypeException {
    for (Tree tree : _allTrees) {
        if (tree.getTreeID().equals(treeId)) {
            throw new DuplicateTreeKeyException(treeId);
        } 
    }
    Tree tree;
    if (type.equalsIgnoreCase("PERENE")) {
        tree = new Evergreen(treeId, name, age, type, diff, _currentSeason);
    } else if (type.equalsIgnoreCase("CADUCA")) {
        tree = new Lapsed(treeId, name, age, type, diff, _currentSeason);
    } else {
        throw new InvalidTreeTypeException(type);
    }
    _allTrees.add(tree);
    markAsUnsaved();
}

  /**registerHabitat
   * 
   * Given the correct parameters, a new Habitat is created, and stored in
   * the hotel's Habitat specific collection.
   * 
   * @param habitatId
   * @param name
   * @param area
   * @return
   * @throws DuplicateHabitatKeyException
   * @throws UnknownHabitatKeyException
   */
  public Habitat registerHabitat(String habitatId, String name, int area) 
    throws DuplicateHabitatKeyException, UnknownHabitatKeyException {
    if (!isValidHabitatId(habitatId)) {
      throw new UnknownHabitatKeyException(habitatId);
    }  
    for (Habitat habitat : _allHabitats) {
        if (habitat.getHabitatID().equals(habitatId)) {
           throw new DuplicateHabitatKeyException(habitatId);
        }
    }

    Habitat habitat = new Habitat(habitatId, name ,area);
    _allHabitats.add(habitat);
    markAsUnsaved();
    return habitat;
}

  /**
   * Transfers an animal to a new habitat.
   *
   * @param animalId
   * @param habitatId
   * @throws UnknownAnimalKeyException
   * @throws UnknownHabitatKeyException
   */
  public void transferHabitat(String animalId, String habitatId) throws 
    UnknownAnimalKeyException, UnknownHabitatKeyException {
      
    Animal animal = findAnimalByID(animalId);
    Habitat newHabitat = findHabitatByID(habitatId);
    Habitat currentHabitat = animal.getHabitat();
    currentHabitat.getHabitatAnimals().remove(animal);
    newHabitat.addAnimal(animal, newHabitat);
    animal.setHabitat(newHabitat);
  }

  /**
   * Read text input file and create corresponding domain entities.
   *
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException
   * @throws IOException
   **/
  public void importFile(String filename) {
    File file = new File(filename);
    
    if (!file.exists() || file.length() == 0) {
        System.err.println("Error: File does not exist or is empty: " + filename);
        return;
    }

    try {
        Parser parser = new Parser(this);
        parser.parseFile(filename);
    } catch (IOException e) {
        System.err.println("I/O error occurred: " + e.getMessage());
    } catch (UnrecognizedEntryException e) {
        System.err.println("Unrecognized entry in the file: " + e.getEntrySpecification());
    } catch (Exception e) {
        System.err.println("Unexpected error occurred while importing file: " + 
          filename + " - " + e.getMessage());
    }
  }


  /*
   * 
   * ID Finders - functions that find their corresponing object based on its ID.
   * 
  */

    public Habitat findHabitatByID(String habitatId) throws UnknownHabitatKeyException {
      for (Habitat habitat : _allHabitats) {
          if (habitat.getHabitatID().equalsIgnoreCase(habitatId)) {
              return habitat;
          }
      }
      throw new UnknownHabitatKeyException(habitatId);
    }

    public Species findSpeciesByID(String speciesId) throws UnknownSpeciesKeyException {
      for (Species species : _allSpecies) {
          if (species.getSpeciesID().equalsIgnoreCase(speciesId)) {
              return species;
          }
      }
      throw new UnknownSpeciesKeyException(speciesId);
    }

    public Animal findAnimalByID(String animalId) throws UnknownAnimalKeyException {
      for (Animal animal : _allAnimals) {
        if (animal.getAnimalID().equals(animalId)) {
          return animal;
        }
      }
      throw new UnknownAnimalKeyException(animalId);
    }

    public Tree findTreeByID(String treeId) {
      for (Tree tree : _allTrees) { 
        if (tree.getTreeID().equalsIgnoreCase(treeId)){
          return tree;
          }
      }
      return null;
    }

    public Employee findEmployeeByID(String employeeID) throws UnknownEmployeeKeyException {
      for (Employee employee : _allEmployees) {
          if (employee.getEmployeeID().equalsIgnoreCase(employeeID)) {
              return employee;
          }
      } 
      throw new UnknownEmployeeKeyException(employeeID);
    }

    public Vaccine findVaccineByID(String vaccineID) throws UnknownVaccineKeyException {
      for (Vaccine v: this.getAllVaccines()){
        if(v.getVaccineID().equalsIgnoreCase(vaccineID)){
          return v;
        }
      } throw new UnknownVaccineKeyException(vaccineID);
    }

    public List<VaccineEvent> showAllFaultyVaccines() {
      List<VaccineEvent> faulty = new ArrayList<>();
      for (VaccineEvent vac : this.getAllEvents()) {
        if (vac.getDamage() != Damage.NORMAL){
          faulty.add(vac);
        }
      }
      return faulty;
    }

    public void vaccinate(String vacID, String vetID, String animalID) throws 
    UnknownAnimalKeyException, UnknownEmployeeKeyException,
     UnknownVeterinarianKeyException, UnknownVaccineKeyException, VaccineDamageException, VeterinarianNotAuthorizedException {
      Vaccine vaccine = findVaccineByID(vacID);
      Animal animal = findAnimalByID(animalID);
      String specID = animal.getSpecies().getSpeciesID();
      
      // Find the veterinarian by ID
      Employee employee = findEmployeeByID(vetID);
      if (!(employee instanceof Vet)) {
          throw new UnknownVeterinarianKeyException(vetID);
      }
      
    
      
      
      Damage damage = vaccine.getDamageTerm(vaccine.calculateDamage(animal.getSpecies()));
      
      
      if (damage != Damage.NORMAL) {
        throw new VaccineDamageException(vacID);
      }

      VaccineEvent event = new VaccineEvent(vetID, vacID, specID, damage);
      animal.addHealthHistory(event);
      this.getAllEvents().add(event);
      vaccine.incrementTimesUsed();
    }
}