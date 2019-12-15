/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_composite;

import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.displays.UserOptionStorage;

/**
 *
 * @author Nikola
 */
public class CompositeMenu implements MenuItem {
    
    private final List<MenuItem> menuItems;

    public CompositeMenu() {
        menuItems = new ArrayList<>();
    }

    @Override
    public void print() {
        for (MenuItem menuItem : menuItems) {
            menuItem.print();
        }
    }

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }
    
    public void runUserOption(int userOption) {
        MenuItem menuItem = UserOptionStorage.getInstance().getUserOptions().get(userOption);
        if (menuItem == null) {
            System.out.println("Nepodrzana opcija");
            return;
        }
        menuItem.print();
    }
    
    
}
