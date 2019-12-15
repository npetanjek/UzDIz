/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_builders.menu_builder;

import npetanjek_zadaca_2.displays.display_composite.menu_composite.CompositeMenu;

/**
 *
 * @author Nikola
 */
public class MenuBuildDirector {
    
    private final MenuBuilder builder;

    public MenuBuildDirector(final MenuBuilder builder) {
        this.builder = builder;
    }
    
    public CompositeMenu construct() {
        return builder.addHeader().addUserOptions().addFooter().build();
    }
    
}
