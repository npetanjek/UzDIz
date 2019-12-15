/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.observers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.entities.Uloga;

/**
 *
 * @author Nikola
 */
public class Participant implements Subject {
        
    private Osoba osoba;
    private Uloga uloga;    // state
    private List<Observer> showList; // emisije u kojima sudjeluje
    
    public Participant() {
        showList = new ArrayList<>();
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    @Override
    public void addObserver(Observer show) {
        this.showList.add(show);
    }

    @Override
    public void removeObserver(Observer o) {
        this.showList.remove(o);
    }

    @Override
    public Uloga getState() {
        return uloga;
    }

    @Override
    public void setState(Uloga state) {
        this.uloga = state;      
    }
    
    public void notifyObservers() {
        Iterator i = showList.iterator();
        while (i.hasNext()) {
            Observer o = (Observer) i.next();
            o.update(this);
        }
    }

    public List<Observer> getShowList() {
        return showList;
    }
    
}
