package hva.core;

import hva.core.exception.UnknownSpeciesKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;   

public class Vet extends Employee {
    private TreeSet<String> _vetSpecies;
    private Map<String, Species> _speciesMap;
    private Map<String, Integer> _speciesVeterinarians = new HashMap<>();
    private SatisfactionStrategy _satisfactionStrategy;

    /*
     * 
     * Subclass Constructor - invokes super's constructor with common
     * fields.
     * 
    */

    public Vet(String vetID, String vetName, String empType){
        super(vetID, vetName);
        setType(empType);
        _vetSpecies = new TreeSet<>();
        this._speciesMap = new HashMap<>();
        _satisfactionStrategy = new VetSatisfactionStrategy(this);
    }

    public TreeSet<String> getSpecies() {
        return _vetSpecies;
    }

    public int getPopulation() {
        return _speciesMap.size();
    }

    public int getNumberOfVeterinariansForSpecies(String speciesID) {
        return _speciesVeterinarians.getOrDefault(speciesID, 1);
    }

    /**
     * Get a species by its ID from a collection of species.
     *
     * @param speciesCollection the collection of species
     * @param speciesID the ID of the species to find
     * @return the species with the matching ID, or null if not found
     */
    public Species getSpeciesByID(String speciesID) {

        return _speciesMap.get(speciesID); 

    }

    public Map<String, Integer> getSpeciesVeterinarians() {
        return _speciesVeterinarians;
    }

    public SatisfactionStrategy getSatisfactionStrategy() {
        return _satisfactionStrategy;
    }

    public void setSatisfactionStrategy(SatisfactionStrategy satisfactionStrategy) {
        this._satisfactionStrategy = satisfactionStrategy;
    }

    @Override
    public int satisfaction(){
        return _satisfactionStrategy.calculateSatisfaction();
    }


    /**getResponsibility
     * Original method Overriden.
     * Show all of a Vet's Responsibilities.
     * @return A string that contains all species which this Vet is 
     * ensured to vaccinate.
     */
    @Override
    public String getResponsibility(){ 
        return String.join(",", _vetSpecies);
    }

    public void addSpeciesToMap(String speciesID, Species species) {
        _speciesMap.put(speciesID, species);
    }

    @Override
    public void addResponsibility(String speciesId, Hotel hotel){
        try{
            hotel.findSpeciesByID(speciesId);
        } catch (UnknownSpeciesKeyException e) {
            throw new IllegalArgumentException("Species not found: " + speciesId);
        }
        _vetSpecies.add(speciesId);
    }

    /**addResponsibility
     * Original method Overriden.
     * Remove a Vet's responsibility.
     */
    @Override
    public void removeResponsibility(String speciesId){
        _vetSpecies.remove(speciesId);
        _speciesMap.remove(speciesId);
    }

    /** employeeString
     * 
     * this Method converts the Vet to a Parsed 
     * string containing all his assets.
     * 
    */

    @Override
    public String employeeString() {
        String responsibilities = getResponsibility();
        if (responsibilities.isEmpty()) {
            return String.format("VET|%s|%s", this.getEmployeeID(), this.getEmployeeName());
        } else {
            return String.format("VET|%s|%s|%s", this.getEmployeeID(), this.getEmployeeName(), responsibilities);
        }
    }
}