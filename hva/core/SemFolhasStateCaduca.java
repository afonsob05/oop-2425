package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of a Lapsed tree, during Winter.
*/

public class SemFolhasStateCaduca implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.SPRING) {
            tree.setState(new GerarFolhasStateCaduca());
        }
    }

    @Override
    public String getStateName() {
        return "SEMFOLHAS";
    }

    public int getSeasonalEffort() {
        return 0;
    }
}