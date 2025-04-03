package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of an Evergreen tree, during Spring.
*/

public class GerarFolhasStatePerene implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.SUMMER) {
            tree.setState(new ComFolhasStatePerene());
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