/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_display_options;

import npetanjek_zadaca_2.displays.decorators.ProgramDecorator;
import npetanjek_zadaca_2.displays.decorators.ShowRevenueDecorator;

/**
 *
 * @author Nikola
 */
public class RevenueDisplay extends Display {

    @Override
    public void print() {
        if (getUserInput()) {
            ProgramDecorator programDecorator = new ProgramDecorator(program);
            programDecorator.print();
            ShowRevenueDecorator dayDecorator = new ShowRevenueDecorator(day);
            dayDecorator.print();
        }
    }

    @Override
    public String toString() {
        return "Ispis potencijalnih prihoda od reklama";
    }

}
