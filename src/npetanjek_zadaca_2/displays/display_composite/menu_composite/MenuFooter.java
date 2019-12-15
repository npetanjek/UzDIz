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
public class MenuFooter implements MenuItem {
    
    private final List<String> displayStrings;

    public MenuFooter() {
        displayStrings = new ArrayList<>();
        buildFooter();
    }
    
    private void buildFooter() {
        displayStrings.add("0. Izlaz");
        displayStrings.add("====================");
    }

    @Override
    public void print() {
        for (String displayString : displayStrings) {
            System.out.println(displayString);
        }
    }
    
}
