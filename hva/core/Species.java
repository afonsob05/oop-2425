package hva.core;

import java.io.Serializable;
import java.util.*;

public class Species implements Serializable, Comparable<Species> {
    private String _speciesID;
    private String _speciesName;
    private Collection<Animal> _speciesAnimals;
    private List<String> _veterinarians;


    /**
     * 
     * Class Constructor
     *
     */
    public Species(String id, String name){
        _speciesID = id;
        _speciesName = name;
        _speciesAnimals = new TreeSet<Animal>();
        _veterinarians = new ArrayList<>();
    }

    public String getSpeciesID(){ return _speciesID;}

    public String getSpeciesName(){ return _speciesName;}

    /**
     * Get the population of the species.
     *
     * @return the number of animals in this species
     */
    public int getPopulation() {
        return _speciesAnimals.size();
    }

    public int getVeterinarianCount() {
        return _veterinarians.size();
    }

    public void addAnimal(Animal animal) {
        _speciesAnimals.add(animal);
    }

    @Override
    public int compareTo(Species other) {
        return this._speciesID.compareTo(other._speciesID); // Compare by species ID
    }

}
