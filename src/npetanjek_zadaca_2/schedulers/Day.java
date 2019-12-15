/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import npetanjek_zadaca_2.entities.Emisija;

/**
 *
 * @author Nikola
 */
public class Day implements ProgramComponent {
    
    private int day;
    private List<ProgramComponent> programComponents;

    public Day() {
        programComponents = new ArrayList<>();
    }
    
    @Override
    public void add(ProgramComponent programComponent) {
        programComponents.add(programComponent);
        sortList();
    }
    
    private void sortList() {
        Collections.sort((List<Emisija>)(List<?>) programComponents);
    }

    public int getDay() {
        return day;
    }
    
    public String getDayName() {
        switch(day) {
            case 1:
                return "Ponedjeljak";
            case 2:
                return "Utorak";
            case 3:
                return "Srijeda";
            case 4:
                return "Cetvrtak";
            case 5:
                return "Petak";
            case 6:
                return "Subota";
            case 7:
                return "Nedjelja";
        }
        return null;
    }

    @Override
    public List<ProgramComponent> getChildren() {
        return programComponents;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public void print() {        
        System.out.println(getDayName());
    }

    @Override
    public int getId() {
        return day;
    }
    
}
