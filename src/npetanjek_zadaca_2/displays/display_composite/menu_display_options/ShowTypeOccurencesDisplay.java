/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_display_options;

import java.util.Scanner;
import npetanjek_zadaca_2.TVKuca;
import npetanjek_zadaca_2.displays.decorators.ShowTypeOccurencesDecorator;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuItem;
import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.entities.Vrsta;
import npetanjek_zadaca_2.helpers.ScheduleHelper;

/**
 *
 * @author Nikola
 */
public class ShowTypeOccurencesDisplay implements MenuItem {
    
    private ScheduleHelper scheduleHelper;
    private Vrsta showType;

    public ShowTypeOccurencesDisplay() {
        scheduleHelper = ScheduleHelper.getInstance();
    }
    
    private void getUserInput() {
        for (Vrsta vrsta : scheduleHelper.getTypesList()) {
            System.out.println(vrsta.getId() + " " + vrsta.getVrstaEmisije());
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Odaberite vrstu emisije");
        String input = s.nextLine();
        showType = scheduleHelper.getVrstaById(Integer.parseInt(input));
    }

    @Override
    public void print() {
        getUserInput();
        for (Program program : TVKuca.getInstance().getProgramlist()) {
            ShowTypeOccurencesDecorator decorator = new ShowTypeOccurencesDecorator(program, showType);
            decorator.print();
        }
    }

    @Override
    public String toString() {
        return "Ispis tjednog plana za vrstu emisije";
    }
    
}
