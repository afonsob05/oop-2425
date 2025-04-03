package hva.core;

import hva.core.exception.UnknownHabitatKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Keeper extends Employee {
    private int _numKeepers;
    private TreeSet<String> _keepHabitats;
    private Map<String, Habitat> _habitatMap;
    private SatisfactionStrategy _satisfactionStrategy;

    /*
     * 
     * Subclass Constructor - invokes super's constructor with common
     * fields.
     * 
    */
    public Keeper(String keepID, String keepName, String empType){
        super(keepID, keepName);
        setType(empType);
        _keepHabitats = new TreeSet<>();
        this._habitatMap = new HashMap<>();
        _satisfactionStrategy = new KeeperSatisfactionStrategy(this);
    }


    /**
     * 
     * All Get-er Functions related to Keeper
     * 
     */
    public TreeSet<String> getHabitats() {
        return _keepHabitats;
    }

    public Habitat getHabitatById(String habitatId) {
        return _habitatMap.get(habitatId); // Retrieve Habitat object by ID
    }

    public SatisfactionStrategy getSatisfactionStrategy() {
        return _satisfactionStrategy; 
    }

    public void setSatisfactionStrategy(SatisfactionStrategy satisfactionStrategy) {
        this._satisfactionStrategy = satisfactionStrategy;
    }

    /**satisfaction
     * Original method for satisfaction is Overriden.
     * Calculate a Keeper's Satisfaction.
     * @return integer level of satisfaction
     */
    @Override
    public int satisfaction(){
        return _satisfactionStrategy.calculateSatisfaction();
    }
    
    /**getResponsibility
     * Original method Overriden.
     * Show all of a Keeper's Responsibilities.
     * @return A string that contains all habitats which 
     * this Keeper is enroled to.
     */

    @Override
    public String getResponsibility(){ 
        return String.join(",", _keepHabitats);
    }

    public void addHabitatToMap(String habitatID, Habitat habitat) {
        _habitatMap.put(habitatID, habitat);
    }

    /**addResponsibility
     * Original method Overriden.
     * Add a responsibility to a Keeper.
     */
    @Override
    public void addResponsibility(String habitatId, Hotel hotel){
        Habitat habitat;
        try{
        habitat = hotel.findHabitatByID(habitatId);
        } catch (UnknownHabitatKeyException e) {
            throw new IllegalArgumentException("Habitat not found: " + habitatId);
        }
        String employeeId = super.getEmployeeID();
        if (employeeId == null) {
            throw new IllegalStateException("Employee ID is null");
        }
        _keepHabitats.add(habitatId);
        habitat.addKeeper(employeeId);
    }

    /**removeResponsibility
     * Original method Overriden.
     * Remove a responsibility from a Keeper.
     */
    @Override
    public void removeResponsibility(String habitatId){
        Habitat habitat = this.getHabitatById(habitatId);
        if (habitat == null) {
            throw new IllegalArgumentException("Habitat not found: " + habitatId);
        }
        String employeeId = super.getEmployeeID();
        if (employeeId == null) {
            throw new IllegalStateException("Employee ID is null");
        }
        _keepHabitats.remove(habitatId);
        _habitatMap.remove(habitatId);
        habitat.removeKeeper(employeeId);
    }

    /** employeeString
     * 
     * this Method converts the Keeper to a Parsed 
     * string containing all his assets.
     * 
    */
    @Override
    public String employeeString() {
        String responsibilities = getResponsibility();
        if (responsibilities.isEmpty()) {
            return String.format("TRT|%s|%s", this.getEmployeeID(), this.getEmployeeName());
        } else {
            return String.format("TRT|%s|%s|%s", this.getEmployeeID(), this.getEmployeeName(), responsibilities);
        }
    }
}
