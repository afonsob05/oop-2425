package hva.core;

public class Lapsed extends Tree {

    /*
     * 
     * Class Constructor
     * 
    */
    public Lapsed(String id, String name, int age, String treeType, int difficulty, Season currentSeason) {
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
                return new GerarFolhasStateCaduca();
            case SUMMER:
                return new ComFolhasStateCaduca();
            case FALL:
                return new LargarFolhasStateCaduca();
            case WINTER:
                return new SemFolhasStateCaduca();
            default:
                throw new IllegalArgumentException("Unknown season: " + season);
        }
    }


    /** cleaningDifficulty
     * Sets the cleaning Effort integer for a Lapsed Tree, all year round.
     * @param t - Tree
     * @param s - Season 
     */
    @Override
    public void cleaningDifficulty(Tree t, Season s){
        switch(s){
            case WINTER : t.setDifficulty(0);
            case SPRING : t.setDifficulty(1);
            case SUMMER : t.setDifficulty(2);
            case FALL : t.setDifficulty(5);
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

