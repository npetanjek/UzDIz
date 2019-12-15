/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_builders.menu_builder;

import npetanjek_zadaca_2.displays.UserOptionStorage;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.CompositeMenu;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.Menu;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuFooter;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuHeader;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuItem;
import npetanjek_zadaca_2.displays.display_composite.menu_display_options.RevenueDisplay;
import npetanjek_zadaca_2.displays.display_composite.menu_display_options.RoleDisplay;
import npetanjek_zadaca_2.displays.display_composite.menu_display_options.ScheduleDisplay;
import npetanjek_zadaca_2.displays.display_composite.menu_display_options.ShowTypeOccurencesDisplay;

/**
 *
 * @author Nikola
 */
public class MenuBuilderImpl implements MenuBuilder {

    private CompositeMenu compositeMenu;

    public MenuBuilderImpl() {
        compositeMenu = new CompositeMenu();
    }
    
    @Override
    public CompositeMenu build() {
        return compositeMenu;
    }

    @Override
    public MenuBuilder addHeader() {
        MenuHeader menuHeader = new MenuHeader();
        compositeMenu.add(menuHeader);
        return this;
    }

    @Override
    public MenuBuilder addFooter() {
        MenuFooter menuFooter = new MenuFooter();
        compositeMenu.add(menuFooter);
        return this;
    }

    @Override
    public MenuBuilder addUserOptions() {
        int optionNumber = 1;
        UserOptionStorage userOptionStorage = UserOptionStorage.getInstance();        
        Menu menu = new Menu();
        MenuItem scheduleDisplay = new ScheduleDisplay();
        MenuItem revenueDisplay = new RevenueDisplay();
        MenuItem showTypeOccurencesDisplay = new ShowTypeOccurencesDisplay();
        MenuItem roleDisplay = new RoleDisplay();
        menu.add(scheduleDisplay);
        menu.add(revenueDisplay);
        menu.add(showTypeOccurencesDisplay);        
        menu.add(roleDisplay);
        userOptionStorage.storeMenuOption(optionNumber++, scheduleDisplay);
        userOptionStorage.storeMenuOption(optionNumber++, revenueDisplay);
        userOptionStorage.storeMenuOption(optionNumber++, showTypeOccurencesDisplay);
        userOptionStorage.storeMenuOption(optionNumber++, roleDisplay);
        compositeMenu.add(menu);
        return this;
    }
    
}
