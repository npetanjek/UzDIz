/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_display_options;

import npetanjek_zadaca_2.displays.decorators.ShowScheduleDecorator;
import npetanjek_zadaca_2.displays.decorators.ProgramDecorator;

/**
 *
 * @author Nikola
 */
public class ScheduleDisplay extends Display {

    @Override
    public void print() {
        if (getUserInput()) {
            ProgramDecorator programDecorator = new ProgramDecorator(program);
            programDecorator.print();
            ShowScheduleDecorator dayDecorator = new ShowScheduleDecorator(day);
            dayDecorator.print();
        }
    }

    @Override
    public String toString() {
        return "Ispis rasporeda";
    }

}
