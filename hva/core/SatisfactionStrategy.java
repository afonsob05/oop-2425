package hva.core;

import java.io.Serializable;


/*
 * 
 * Interface Implementing Strategy Pattern Design, to solve conflicts
 * within Employee Satisfaction for different empTypes
 * 
*/
public interface SatisfactionStrategy extends Serializable {
    int calculateSatisfaction();
}
