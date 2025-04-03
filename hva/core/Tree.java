package hva.core;

import java.io.Serializable;

public abstract class Tree implements Comparable<Tree>, Serializable{
    private String _treeID;
    private String _treeName;
    private int _treeAge;
    protected int _treeDifficulty;
    private String _treeType;
    private TreeState _state;
    private int seasonCount;


    /**
     * 
     * Class Constructor
     * 
    */
    Tree(String id, String name, int age, int difficulty, TreeState initialState){
        _treeID = id;
        _treeName = name;
        _treeAge = age;
        _treeDifficulty = difficulty;
        _state = initialState;
        seasonCount = 0;
    }


    /**
     * 
     * Tree Get-ers
     * 
    */
    public String getTreeID(){ return _treeID; }  
 
    public int getAge(){ return _treeAge; }

    public int getDifficulty() { return _treeDifficulty;}

    public double getCleaningEffort() {
        int seasonalEffort = _state.getSeasonalEffort();
        return _treeDifficulty * seasonalEffort * Math.log(_treeAge + 1);
    }

    public String getType() {return _treeType;}

    public String getStateName() { return _state.getStateName(); }



    public void setLeaves(String type) {_treeType = type;}

    public void setDifficulty(int difficulty){ _treeDifficulty = difficulty; };

    public void setState(TreeState state) { _state = state; }


    /**advanceBioCycle
     * This method allows the Trees to grow older. Every passing 
     * of 4 seasons since its creation, its age goes up 1
     * @param season
     */
    public void advanceBioCycle(Season season) {
        _state.advance(this, season);
        seasonCount++;
        if (seasonCount >= 4) {
            _treeAge++;
            seasonCount = 0;
        }
    }

    /** toString
     * Original method Overriden.
     * Transforms the Tree Object into a parsed string, stating
     * all its assests.
     * 
    */
    @Override
    public String toString() {
        return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s", 
            _treeID, _treeName, _treeAge, _treeDifficulty, _treeType, _state.getStateName());
    }

    @Override
    public int compareTo(Tree other) {
        return this._treeID.compareTo(other._treeID);
    }

    public abstract void cleaningDifficulty(Tree t, Season s);
}
