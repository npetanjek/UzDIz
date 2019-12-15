/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.display_composite.menu_display_options;

import java.util.List;
import java.util.Scanner;
import npetanjek_zadaca_2.TVKuca;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.MenuItem;
import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.schedulers.Day;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public abstract class Display implements MenuItem {
    
    protected Program program;
    protected Day day;
    private final List<Program> channels;

    public Display() {
        channels = TVKuca.getInstance().getProgramlist();
    }
    
     protected boolean getUserInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("Unesite program");
        String input = s.nextLine();
        if (input.equals("")) {
            System.out.println("Neispravan unos");
            return false;
        }
        int programNumber = Integer.parseInt(input);
        if (programNumber > channels.size() || programNumber < 1) {
            System.out.println("Postoje samo " + channels.size() + " programa!");
            return false;
        }
        this.program = channels.get(programNumber - 1);
        System.out.println("Unesite dan");
        input = s.nextLine();
        if (input.equals("")) {
            System.out.println("Neispravan unos");
            return false;
        }
        int dayNumber = Integer.parseInt(input);
        if (dayNumber < 1 || dayNumber > 7) {
            System.out.println("Tjedan ima 7 dana :)");
            return false;
        }
        ProgramComponent dayComponent = program.getChildren().get(dayNumber - 1);
        this.day = (Day) dayComponent;
        return true;
    }
    
}
