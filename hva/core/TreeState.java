package hva.core;


/**
 * 
 * State Design Pattern implemented for Trees. Creates much more
 * code flexibility for tweaks in Season and Tree.
 * 
*/
public interface TreeState {
    void advance(Tree tree, Season season);
    String getStateName();
    int getSeasonalEffort();
}