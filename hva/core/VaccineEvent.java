package hva.core;

public class VaccineEvent  {
    private String _vetID;
    private String _animalID;
    private String _vaccineID;
    private String _speciesID;
    private Damage _vaccineDamage;

    /***
     * 
     * Class Constructor
     * 
     */
    public VaccineEvent(String vetID, String vaccineID, String specID, Damage vaccineDamage){ 
        _vetID = vetID;
        _vaccineID = vaccineID;
        _speciesID = specID;
        _vaccineDamage = vaccineDamage;
    }

    /*
     * 
     * Vaccine Get-ers
     * 
    */
    public String getEventSpecies() { return _speciesID; }
    public String getEventVet() { return _vetID; }
    public String getEventID() {return _vaccineID; }
    public Damage getDamage(){ return _vaccineDamage;}

    /**
     * Original method Overriden.
     * Converts a Vaccine Object into a Parsed String, stating its assets.
     * 
    */
    @Override
    public String toString(){
        return String.format("REGISTO-VACINA|%s|%s|%s", 
            this.getEventID(), this.getEventVet(), this.getEventSpecies());
    }

}
