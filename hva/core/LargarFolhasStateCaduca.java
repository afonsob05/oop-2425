package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of a Lapsed tree, during Fall.
*/

public class LargarFolhasStateCaduca implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.WINTER) {
            tree.setState(new SemFolhasStateCaduca());
        }
    }

    @Override
    public String getStateName() {
        return "LARGARFOLHAS";
    }

    public int getSeasonalEffort() {
        return 5;
    }
}