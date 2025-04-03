package hva.core;


/* Season enumerator
 * 
 * Linked to State Design Pattern of Trees.
 * States all possible Seasons in a year.
*/
public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    /**nextSeason
     * determines what the next Season will be.
     * @return the next Season
     */
    public Season nextSeason() {
        return switch(this) {
            case SPRING -> SUMMER;
            case SUMMER -> FALL;
            case FALL -> WINTER;
            case WINTER -> SPRING;
        };
    }

    /**getSeasonCode
     * Useful method for state tracking inside of the App.
     * @return
     */
    public int getSeasonCode() {
        switch (this) {
            case SPRING:
                return 0;
            case SUMMER:
                return 1;
            case FALL:
                return 2;
            case WINTER:
                return 3;
            default:
                throw new IllegalArgumentException("Unknown season: " + this);
        }
    }
}