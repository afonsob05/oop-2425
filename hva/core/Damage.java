package hva.core;

/** Damage Enumerator
 * Used to keep the possible damage outputs of each vaccination.
 * 
*/

public enum Damage {
    NORMAL("NORMAL"), 
    CONFUSÃO("CONFUSÃO"), 
    ACIDENTE("ACIDENTE"), 
    ERROR("ERRO");
    private String _damage;
    
    Damage(String damage){
        _damage = damage;
    }

    public String getVaccineDamage() { return _damage; }
}
