package hva.core;

import java.util.TreeSet;

/**
 * Strategy Design Patterns introduced to Keeper.
 * 
*/

public class KeeperSatisfactionStrategy implements SatisfactionStrategy {
    private Keeper keeper;
    /**
     * Constructor
     * @param keeper - a Keeper
     */
    public KeeperSatisfactionStrategy(Keeper keeper) {
        this.keeper = keeper;
    }


    /**
     * Original method Overriden
     * Calculate individual Keeper satisfaction.
     * @return the satisfaction of a Keeper.
    */
    @Override
    public int calculateSatisfaction() {
        TreeSet<String> habitatIds = keeper.getHabitats();

        double satisfaction = 300.0;

        for (String habitatId : habitatIds) {
            Habitat habitat = keeper.getHabitatById(habitatId);
            if (habitat == null) continue;

            int area = habitat.getArea();
            int population = habitat.getPopulation();

            int numKeepers = habitat.getNumberOfKeepers(); 
            double cleaningEffort = habitat.getTotalCleaningEffort();
            double work = area + 3 * population + cleaningEffort;
            satisfaction -= work / numKeepers;
        }

        return (int) satisfaction;
    }
}