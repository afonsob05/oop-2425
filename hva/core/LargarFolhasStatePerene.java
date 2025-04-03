package hva.core;

/** ComFolhasStateCaduca
 * Class that implements the interface TreeState.
 * Represents the Leaf state of an Evergreen tree, during Winter.
*/

public class LargarFolhasStatePerene implements TreeState {
    @Override
    public void advance(Tree tree, Season season) {
        if (season == Season.SPRING) {
            tree.setState(new GerarFolhasStatePerene());
        }
    }

    @Override
    public String getStateName() {
        return "LARGARFOLHAS";
    }

    public int getSeasonalEffort() {
        return 2;
    }
}