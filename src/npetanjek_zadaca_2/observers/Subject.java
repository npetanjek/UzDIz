/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.observers;

import npetanjek_zadaca_2.entities.Uloga;

/**
 *
 * @author Nikola
 */
public interface Subject {
    
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public Uloga getState();
    public void setState(Uloga state);
    
}
