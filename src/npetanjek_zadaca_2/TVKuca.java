/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2;

import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.entities.Program;

/**
 *
 * @author Nikola
 */
public class TVKuca {

    private static TVKuca INSTANCE;

    private List<Program> programlist;

    private TVKuca() {
        programlist = new ArrayList<>();
    }

    public static TVKuca getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TVKuca();
        }
        return INSTANCE;
    }

    public void startChannels(List<Program> programList) {
        this.programlist = programList;
        for (Program program : this.programlist) {
            program.load();            
        }
    }

    public List<Program> getProgramlist() {
        return programlist;
    }

}
