package hva.core;

/** class Evergreen
 * Subclass of Tree. 
 * 
*/

public class Evergreen extends Tree{

    /*
     * 
     * Class Constructor
     * 
     */

    public Evergreen(String id, String name, int age, String treeType, int difficulty, Season currentSeason){
        super(id, name, age, difficulty, getInitialState(currentSeason));
        setLeaves(treeType);
    }

    /*** getInitialState
     * Linked with the State Design Pattern
     * @param season
     * @return Leafstate of the Season, based on tree type
     */

    private static TreeState getInitialState(Season season) {
        switch (season) {
            case SPRING:
                return new GerarFolhasStatePerene();
            case SUMMER:
                return new ComFolhasStatePerene();
            case FALL:
                return new ComFolhasStatePerene();
            default:
                throw new IllegalArgumentException("Unknown season: " + season);
        }
    }
    
    /** cleaningDifficulty
     * Sets the cleaning Effort integer for an Evergreen Tree, all year round.
     * @param t - Tree
     * @param s - Season 
     */
    @Override
    public void cleaningDifficulty(Tree t, Season s){
        switch(s){
            case WINTER : t.setDifficulty(2);
            default : t.setDifficulty(1);
        }
    }

    /** toString
     * Original method Overriden.
     * Calls super toString, implemented in the Tree class.
    */
    @Override
    public String toString() {
        return super.toString();
    }
}
