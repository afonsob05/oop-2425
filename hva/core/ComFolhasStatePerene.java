package hva.core;

/** ComFolhasStatePerene
 * Class that implements the interface TreeState.
 * Represents the Leaf state of an Evergreen tree, during Summer and Fall.
*/

public class ComFolhasStatePerene implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.WINTER) {
            tree.setState(new LargarFolhasStatePerene());
        }
    }

    @Override
    public String getStateName() {
        return "COMFOLHAS";
    }

    public int getSeasonalEffort() {
        return 1;
    }
}