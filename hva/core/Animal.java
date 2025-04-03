package hva.core;

import java.io.Serializable;
import java.util.*;

public class Animal implements Serializable, Comparable<Animal> {

    private String _animalID;
    private String _animalName;
    private Species _animalSpecie;
    private Habitat _animalHabitat;
    private List<VaccineEvent> _healthHistory;

    /**
     * Class Constructor 
    */

    public Animal(String id, String name, Species specie, String healthHistory, Habitat habitat) {
        _animalID = id;
        _animalName = name;
        _animalSpecie = specie;
        _healthHistory = new ArrayList<>();
        _animalHabitat = habitat;
    }

    /**
     * Animal Get functions;
    */

    public String getAnimalID() { return _animalID;}

    public String getAnimalName() { return _animalName;}

    public List<VaccineEvent> getHealthHistory() { return _healthHistory;}

    public Species getSpecies() { return _animalSpecie;}

    public Habitat getHabitat() { return _animalHabitat;}

    public void setHabitat(Habitat newHabitat) {_animalHabitat = newHabitat;}


    /**addHealthHistory
     * Add a VaccineEvent to the health history of the animal.
     *
     * @param event the VaccineEvent to be added
     */
    public void addHealthHistory(VaccineEvent event) {
        _healthHistory.add(event);
    }

    /**calculateSatisfaction
     * Calculate the satisfaction of the animal based on the given formula.
     * @return of the wanted value
     */

    public int calculateSatisfaction() {
        int sameSpeciesCount = 0;
        int differentSpeciesCount = 0;

        for (Animal animal : _animalHabitat.getHabitatAnimals()) {
            if (animal != this) {
                if (animal.getSpecies().equals(this._animalSpecie)) {
                    sameSpeciesCount++;
                } else {
                    differentSpeciesCount++;
                }
            }
        }

        double area = _animalHabitat.getArea();
        int population = _animalHabitat.getPopulation();
        double adequacao = calculateAdequacao();

        return (int) (20 + 3 * sameSpeciesCount - 2 * differentSpeciesCount + area / population + adequacao);
    }


    /** calculateAdequacao
     * 
     * Method that checks wether a said Species is within the habitat's 
     * most or least favorable species. If neither, habitat has a 
     * neutral influence.
     * 
     * @return Fixed values based on Habitat's influence on a given species
     */

    private double calculateAdequacao() {
        if (_animalHabitat.getPositiveInfluences().contains(_animalSpecie)) {
            return 20.0; // Positive influence
        } else if (_animalHabitat.getNegativeInfluences().contains(_animalSpecie)) {
            return -20.0; // Negative influence
        } else {
            return 0.0;
        }
    }

    /** toString
     * 
     * Original toString Overriden method.
     * Converts the Animal data into String form
     * 
     * @return Formatted string with Animal data.
     */
    @Override
    public String toString() {                                                                      
        return String.format("ANIMAL|%s|%s|%s|%s|%s", _animalID, _animalName, _animalSpecie.getSpeciesID(), getHealthHistoryString(), _animalHabitat.getHabitatID());
    }

    private String getHealthHistoryString() {
        if (_healthHistory.isEmpty()) {
            return "VOID";
        }
        StringBuilder healthHistoryString = new StringBuilder();
        for (int i = 0; i < _healthHistory.size() - 1; i++) {
            healthHistoryString.append(_healthHistory.get(i).getDamage().getVaccineDamage()).append(",");
        }
        // Append the last element without a trailing comma
        healthHistoryString.append(_healthHistory.get(_healthHistory.size() - 1).getDamage());
        return healthHistoryString.toString();
    }
  
    /** compareTo
     * Original method overriden.
     * Function used later on, in collection Sorting.
     * Used for presentation of animals, in lexicographical order.
     * 
     * @return integer representing the compareTo value.
    */

    @Override
    public int compareTo(Animal other) {
        return this._animalID.compareTo(other._animalID);
    }
}