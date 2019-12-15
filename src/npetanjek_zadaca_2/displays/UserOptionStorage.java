/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays;

import java.util.HashMap;
import java.util.Map;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuItem;

/**
 *
 * @author Nikola
 */
public class UserOptionStorage {
    
    private static UserOptionStorage INSTANCE;
    
    private final Map<Integer, MenuItem> userOptions;
    
    private UserOptionStorage() {
        userOptions = new HashMap<>();
    }
    
    public static UserOptionStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserOptionStorage();
        }
        return INSTANCE;
    }
    
    public void storeMenuOption(int id, MenuItem userOption) {
        userOptions.put(id, userOption);
    }

    public Map<Integer, MenuItem> getUserOptions() {
        return userOptions;
    }
    
}
