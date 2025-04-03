package hva.core;

import java.io.Serializable;
import java.util.*;

/*
 *
 * Represents a Vaccine.
 * registers things like id, name, number of administrations, and species
 * it's enlisted to.
*/
public class Vaccine implements Serializable, Comparable<Vaccine>{
    private String _vaccineID;
    private String _vaccineName;
    private int _administeredCount;
    private List<Species> _species;

    /*
     *
     * Class Constructor with no Species associated to the vaccine;
     *
    */

    public Vaccine(String id, String name){
        _vaccineID = id;
        _vaccineName = name;
        _administeredCount = 0;
        _species = new ArrayList<>();
    }

    /*
     *
     * Class Constructor with species associated to the vaccine;
     *
    */

    public Vaccine(String id, String name, List<Species> species){
        _vaccineID = id;
        _vaccineName = name;
        _administeredCount = 0;
        _species = species;
    }

    /*
     * 
     * Class Get functions;
     * 
    */

    public String getVaccineID(){ return _vaccineID; }

    public String getVaccineName(){ return _vaccineName; }

    public int getTimesUsed(){ return _administeredCount; }

    public void incrementTimesUsed(){ _administeredCount++; }

    public String speciesBiggestName(){
        Species speciesBiggestName = _species.get(0);
        for (Species s : _species) {
            if (s.getSpeciesName().length() > speciesBiggestName.getSpeciesName().length()) {
                speciesBiggestName = s;
            }
        }
        return speciesBiggestName.getSpeciesName();
    }

    public int sizebiggestName(Species species){
        return Math.max(speciesBiggestName().length(), species.getSpeciesName().length());
    }

    public int commonCharacters(Species species1, Species species2){
        int commonCharacters = 0;
        String name1 = species1.getSpeciesName();
        String name2 = species2.getSpeciesName();
        
        for (int i = 0; i < name1.length(); i++) {
            for (int j = 0; j < name2.length(); j++) {
                if (name1.charAt(i) == name2.charAt(j)) {
                    commonCharacters++;
                    break; // Move to the next character in name1
                }
            }
        }
        return commonCharacters;
    }

    public int calculateDamage(Species species) {
        if (_species.contains(species)) {
            return -1; // Special value indicating same species
        }
    
        int maxDamage = 0;
        for (Species s : _species) {
            int sizeBiggestName = Math.max(species.getSpeciesName().length(), s.getSpeciesName().length());
            int commonChars = commonCharacters(species, s);
            int damage = sizeBiggestName - commonChars;
            if (damage > maxDamage) {
                maxDamage = damage;
            }
        }
        return maxDamage;
    }

    public Damage getDamageTerm(int damage) {
        if (damage == -1) {
            return Damage.NORMAL;
        } else if (damage == 0) {
            return Damage.CONFUSÃO;
        } else if (damage >= 1 && damage <= 4) {
            return Damage.ACIDENTE;
        } else if (damage >= 5) {
            return Damage.ERROR;
        } else {
            return Damage.CONFUSÃO; // Default case
        }
    }

    /** toString
     * Original method Overriden.
     * Converts the Vaccine Object into a Parsed string, stating all its assets.
     * 
    */
    @Override
    public String toString(){
        if (_species.isEmpty()) {
            return String.format("VACINA|%s|%s|%d",
            _vaccineID, _vaccineName, _administeredCount);
        }
        Collections.sort(_species);

        StringBuilder specList = new StringBuilder();
        for (Species s : _species) {
            specList.append(s.getSpeciesID()).append(",");
        }
        specList.setLength(specList.length() - 1);
        return String.format("VACINA|%s|%s|%d|%s",
        _vaccineID, _vaccineName, _administeredCount, specList.toString());
    }

    @Override
    public int compareTo(Vaccine other) {
        return this._vaccineID.compareTo(other._vaccineID); // Compare by species ID
    }
}
