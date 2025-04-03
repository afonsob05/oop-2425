package hva.core;

import hva.core.exception.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * The {@code Parser} class reads and processes input files to populate
 * the hotel's data, such as: species, animals, habitats, employees, trees, and vaccines.
 * Unrecognized or invalid entries raise one or more exceptions.
 */
public class Parser {
    private Hotel _hotel;

    /**
     * Constructor for the Parser class.
     *
     * @param h is a {@code Hotel} instance that will recieve the data
     * */
    Parser(Hotel h) {
        _hotel = h;
    }

    public void parseFile(String filename) throws IOException, UnrecognizedEntryException, NoResponsibilityException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);

        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException, NoResponsibilityException {
        String[] components = line.split("\\|");

        switch(components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            case "ANIMAL" -> parseAnimal(components);
            case "ÁRVORE" -> parseTree(components);
            case "HABITAT" -> parseHabitat(components);
            case "TRATADOR" -> parseEmployee(components, "TRT");
            case "VETERINÁRIO" -> parseEmployee(components, "VET");
            case "VACINA" -> parseVaccine(components);
            default -> throw new UnrecognizedEntryException ("tipo de entrada inválido: " + components[0]);
        }
    }

    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String habitatId = components[4];
            String healthHisString = "VOID";
            String speciesId = components[3];

            _hotel.registerAnimal(id, name, habitatId, healthHisString, speciesId);
        } catch (UnknownHabitatKeyException e) {
        // Handle UnknownHabitatKeyException (e.g., log or rethrow as UnrecognizedEntryException)
        throw new UnrecognizedEntryException("Unknown habitat key: " + e.getMessage());
        } catch (UnknownSpeciesKeyException e) {
        // Handle UnknownSpeciesKeyException similarly if needed
        throw new UnrecognizedEntryException("Unknown species key: " + e.getMessage());
        } catch (DuplicateAnimalKeyException e) {
        // Handle DuplicateAnimalKeyException similarly
        throw new UnrecognizedEntryException("Duplicate animal key: " + e.getMessage());
        }
    }

    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        if (components.length < 3) {
        throw new UnrecognizedEntryException("Insufficient data: Expected at least 3 components.");
        }

            String id = components[1];
            String name = components[2];

        try {
            _hotel.registerSpecies(id, name);
        } catch (DuplicateSpeciesKeyException e) {
            throw new UnrecognizedEntryException("Duplicate species entry: " + e.getMessage());
        }
    }

    private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException, NoResponsibilityException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerEmployee(id, name, empType);

            // Check if there are responsibilities to add
        if (components.length > 3) {
            String[] responsibilities = components[3].split(",");
            for (String responsibility : responsibilities) {
                _hotel.addResponsibility(id, responsibility);
            }
        }
        } catch (DuplicateEmployeeKeyException e) {
            throw new UnrecognizedEntryException("Duplicate employee entry: " + e.getMessage());
        } catch (InvalidEmployeeTypeException e) {
            throw new UnrecognizedEntryException("Invalid employee type: " + empType);
        } catch (UnknownEmployeeKeyException | UnknownSpeciesKeyException | UnknownHabitatKeyException e) {
            throw new UnrecognizedEntryException("Unknown key: " + e.getMessage());
        } catch (NoResponsibilityException e) {
            throw new UnrecognizedEntryException("Invalid responsibility: " + e.getMessage());
        }
    }

    private void parseVaccine(String[] components) throws UnrecognizedEntryException{
        try {
            String id = components[1];
            String name = components[2];
            String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
            _hotel.registerVaccine(id, name, speciesIds);
        } catch (DuplicateVaccineKeyException e) {
            throw new UnrecognizedEntryException("Duplicate vaccine entry: " + e.getMessage());
        } catch (UnknownSpeciesKeyException e) {
            throw new UnrecognizedEntryException("Unknown species key in vaccine entry: " + e.getMessage());
        }
    }
    private void parseTree(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int age = Integer.parseInt(components[3]);
            int diff = Integer.parseInt(components[4]);
            String type = components[5];

            _hotel.createTree(id, name, type, age, diff);
        } catch (DuplicateTreeKeyException e) {
            throw new UnrecognizedEntryException("Duplicate tree entry: " + e.getMessage());
        } catch (InvalidTreeTypeException e) {
            throw new UnrecognizedEntryException("Invalid tree type: " + e.getMessage());
        }
    }

    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int area = Integer.parseInt(components[3]);

            Habitat hab = _hotel.registerHabitat(id, name, area);

            if (components.length == 5) {
                String[] treeIds = components[4].split(",");
                for (String treeId : treeIds) {
                    Tree tree = _hotel.findTreeByID(treeId); // Assuming this method exists in Hotel
                    if (tree != null) {
                        hab.addTree(tree); // Assuming Habitat has addTree method
                    } else {
                        throw new UnrecognizedEntryException(treeId);
                    }
                }
            }
        } catch (DuplicateHabitatKeyException | UnknownHabitatKeyException e) {
            throw new UnrecognizedEntryException(e.getMessage());
        }
    }
}
