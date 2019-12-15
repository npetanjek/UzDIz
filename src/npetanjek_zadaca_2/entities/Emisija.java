/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.entities;

import java.time.LocalTime;
import java.util.List;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class Emisija extends AbstractEmisija implements Comparable<Emisija> {

    private LocalTime pocetakEmisije = null;
    private LocalTime zavrsetakEmisije = null;

    public Emisija() {
    }

    public Emisija(Emisija target) {
        super(target);
    }

    @Override
    public AbstractEmisija clone() {
        return new Emisija(this);
    }

    public LocalTime getPocetakEmisije() {
        return pocetakEmisije;
    }

    public void setPocetakEmisije(LocalTime pocetakEmisije) {
        this.pocetakEmisije = pocetakEmisije;
    }

    public LocalTime getZavrsetakEmisije() {
        return zavrsetakEmisije;
    }

    public void setZavrsetakEmisije(LocalTime zavrsetakEmisije) {
        this.zavrsetakEmisije = zavrsetakEmisije;
    }
    
    @Override
    public void add(ProgramComponent programComponent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ProgramComponent> getChildren() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(Emisija e) {
        return this.getPocetakEmisije().compareTo(e.getPocetakEmisije());
    }

}
