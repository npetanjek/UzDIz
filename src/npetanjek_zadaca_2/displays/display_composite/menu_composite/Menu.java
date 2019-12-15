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
public class Menu extends CompositeMenu {
    
    private List<MenuItem> menuItems = new ArrayList<>();
    
    public Menu() {
    }

    @Override
    public void print() {
        for (MenuItem menuItem : menuItems) {
            System.out.println((menuItems.indexOf(menuItem) + 1) + ". " + menuItem);
        }
    }

    @Override
    public void add(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }
    
}
