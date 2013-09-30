/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashSet;

/**
 *
 * @author patrick
 */
public class QuarantineRegister {
    
    private HashSet<Quarantine> quarantines = new HashSet(0);
    
    public QuarantineRegister() {
    }

    public HashSet<Quarantine> getQuarantines() {
        return quarantines;
    }

    public void setQuarantines(HashSet<Quarantine> quarantines) {
        this.quarantines = quarantines;
    }
    
    
    
}
