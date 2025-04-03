package hva.core;

import java.util.Set;

/**
 * Strategy Design Patterns introduced to Vet.
 * 
*/

public class VetSatisfactionStrategy implements SatisfactionStrategy {
    private Vet vet;
    /**
     * Constructor
     * @param vet - a Vet
     */
    public VetSatisfactionStrategy(Vet vet) {
        this.vet = vet;
    }

    /**
     * Original method Overriden
     * Calculate individual Vet satisfaction.
     * @return the satisfaction of a Vet.
    */
    @Override
    public int calculateSatisfaction() {
        Set<String> speciesIds = vet.getSpecies();

        double satisfaction = 20.0;

        for (String speciesId : speciesIds) {
            Species s = vet.getSpeciesByID(speciesId);
            if (s == null) {
                continue; 
            }

            int population = s.getPopulation();
            int numVets = vet.getNumberOfVeterinariansForSpecies(speciesId);
            satisfaction -= (double) population / numVets;
        }

        return (int) satisfaction;
    }
}