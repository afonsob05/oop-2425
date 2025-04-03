package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of a Lapsed tree, during Summer.
*/

public class ComFolhasStateCaduca implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.FALL) {
            tree.setState(new LargarFolhasStateCaduca());
        }
    }

    @Override
    public String getStateName() {
        return "COMFOLHAS";
    }

    public int getSeasonalEffort() {
        return 2;
    }
}