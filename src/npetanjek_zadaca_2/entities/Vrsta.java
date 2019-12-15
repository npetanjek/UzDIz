/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.entities;

/**
 *
 * @author Nikola
 */
public class Vrsta {
    
    private int id;
    private String vrstaEmisije;
    private short mozeImatiReklame;
    private int maksTrajanjeReklama;

    public Vrsta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrstaEmisije() {
        return vrstaEmisije;
    }

    public void setVrstaEmisije(String vrstaEmisije) {
        this.vrstaEmisije = vrstaEmisije;
    }

    public short getMozeImatiReklame() {
        return mozeImatiReklame;
    }

    public void setMozeImatiReklame(short mozeImatiReklame) {
        this.mozeImatiReklame = mozeImatiReklame;
    }

    public int getMaksTrajanjeReklama() {
        return maksTrajanjeReklama;
    }

    public void setMaksTrajanjeReklama(int maksTrajanjeReklama) {
        this.maksTrajanjeReklama = maksTrajanjeReklama;
    }
    
}
