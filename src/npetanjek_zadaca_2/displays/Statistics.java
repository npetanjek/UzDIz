/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays;

/**
 *
 * @author nikol
 */
public class Statistics {
    
    private static Statistics INSTANCE;
    
    private Statistics() {}
    
    public static Statistics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Statistics();
        }
        return INSTANCE;
    }
    
}
