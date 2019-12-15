/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class MenuHeader implements MenuItem {
    
    private final List<String> displayStrings;

    public MenuHeader() {
        displayStrings = new ArrayList<>();
        buildHeader();
    }
    
    private void buildHeader() {
        displayStrings.add("====================");
        displayStrings.add("      IZBORNIK      ");
        displayStrings.add("====================");
        displayStrings.add("Izaberite opciju:");
    }

    @Override
    public void print() {
        for (String displayString : displayStrings) {
            System.out.println(displayString);
        }
    }
}
