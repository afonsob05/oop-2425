package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of a Lapsed tree, during Spring.
*/

public class GerarFolhasStateCaduca implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.SUMMER) {
            tree.setState(new ComFolhasStateCaduca());
        }
    }

    @Override
    public String getStateName() {
        return "GERARFOLHAS";
    }

    public int getSeasonalEffort() {
        return 1;
    }
}