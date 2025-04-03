package hva.core;

import java.io.Serializable;
import java.util.*;

/*
 *
 * Represents an Habitat inside of the Hotel.
 * Keeps account of all the animals, species and trees inside of it.
 * 
*/

public class Habitat implements Serializable, Comparable<Habitat>{
    private String _habitatID;
    private String _habitatName;
    private Collection<Species> _species;
    private Collection<Animal> _animals;
    private Collection<Tree> _trees;
    private Collection<Species> _positiveInfluences;
    private Collection<Species> _negativeInfluences;
    private Collection<Species> _neutralInfluences;
    private int _area;
    private List<String> _keepers;

    /* 
     * 
     * Class Constructor
     * 
    */

    public Habitat(String id,String name, int area) {
        _habitatID = id;
        _habitatName = name;
        _area = area;
        _species = new TreeSet<Species>();
        _animals = new TreeSet<Animal>();
        _trees = new TreeSet<Tree>();
        _positiveInfluences = new TreeSet<Species>();
        _negativeInfluences = new TreeSet<Species>();
        _neutralInfluences = new TreeSet<Species>();
        _keepers = new ArrayList<>();
    }

    /*
     * 
     * Habitat Get Functions;
     * 
    */

    public String getHabitatID() { return _habitatID;}

    public String getHabitatName() { return _habitatName;}

    public Collection<Animal> getHabitatAnimals(){ return _animals;}

    public Collection<Species> getHabitatSpecies(){ return _species; }

    public Collection<Tree> getHabitatTrees(){ return _trees;}

    public String getName(){ return _habitatName; }

    public int getArea(){ return _area; }

    public int setArea(int area){ return _area = area; }

    public int getPopulation(){ return _animals.size(); }

    public int getNumberOfTrees(){ return _trees.size(); }

    public Collection<Species> getPositiveInfluences() { return _positiveInfluences; }

    public Collection<Species> getNegativeInfluences() { return _negativeInfluences; }

    public Collection<Species> getNeutralInfluences() { return _neutralInfluences; }

    
    /**
     * 
     * Habitat-related Operations
     * 
    */
    public List<String> getKeepers() { return _keepers; }

    public int getNumberOfKeepers() {
        return _keepers.size();
    }

    public void addKeeper(String keeperID) {
        if (!_keepers.contains(keeperID)) {
            _keepers.add(keeperID);
        }
    }

    public void removeKeeper(String keeperID) {
        _keepers.remove(keeperID);
    }


    /** addAnimal
     * Method that recieves and animal and a habitat, and stores the animal
     * in said habitat.
     * @param a - an Animal
     * @param h - an Habitat
     */
    public void addAnimal(Animal a, Habitat h){
        if (!(h.getHabitatAnimals().contains(a))) { h.getHabitatAnimals().add(a); }
        if (!(h.getHabitatSpecies().contains(a.getSpecies()))) {h.getHabitatSpecies().add(a.getSpecies());}
    }

    /** addTree
     * Method that stores the tree in an habitat.
     * @param t - a Tree
     */
    public void addTree(Tree t){
        if (!(this.getHabitatTrees().contains(t))) {
            this.getHabitatTrees().add(t);
        }
    }

    /*** addPositive/Negative/NeutralInfluence
     * These methods recieve a specie, and store it inside the 
     * habitat's list that keep track of the optimal, not favorable
     * and neutral influences, respectively.
     * @param species
     */

    public void addPositiveInfluence(Species species) {
        _positiveInfluences.add(species);
    }

    public void addNegativeInfluence(Species species) {
        _negativeInfluences.add(species);
    }

    public void addNeutralInfluence(Species species) {
        _neutralInfluences.add(species);
    }

    /** setInfluence
     * Set the influence of a specie inside of an habitat.
     * @param species the species to influence
     * @param influence String that can have 3 values: "POS", "NEG" & "NEU".
     */
    public void setInfluence(Species species, String influence) {
        _positiveInfluences.remove(species);
        _negativeInfluences.remove(species);
        _neutralInfluences.remove(species);

        switch (influence) {
            case "POS":
                _positiveInfluences.add(species);
                break;
            case "NEG":
                _negativeInfluences.add(species);
                break;
            case "NEU":
                _neutralInfluences.add(species);
                break;
            }
    }

    /** getTotalCleaningEffort
     * Calculate the total cleaning effort for all trees in the habitat.
     *
     * @return said total cleaning effort
     */
    public double getTotalCleaningEffort() {
        return _trees.stream().mapToDouble(Tree::getCleaningEffort).sum();
    }
    

    /** toString
     * Original method overriden.
     * Parses the Tree information into a formatted string.
     * 
    */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("HABITAT|%s|%s|%d|%d", _habitatID, _habitatName, _area, getNumberOfTrees()));
        for (Tree tree : _trees) {  
            sb.append("\n").append(tree.toString());
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Habitat other) {
        return this._habitatID.compareTo(other._habitatID);
    }

}

