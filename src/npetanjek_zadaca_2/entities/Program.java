/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.entities;

import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.file_loaders.ProgramFile;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class Program extends ProgramFile implements ProgramComponent {
    
    private int id;
    private String nazivPrograma;
    private String vrijemePocetka;
    private String vrijemeZavrsetka;
    List<ProgramComponent> components;

    public Program(String fileName) {
        super(fileName);
        components = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivPrograma() {
        return nazivPrograma;
    }

    public void setNazivPrograma(String nazivPrograma) {
        this.nazivPrograma = nazivPrograma;
    }

    public String getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(String vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public String getVrijemeZavrsetka() {
        return vrijemeZavrsetka;
    }

    public void setVrijemeZavrsetka(String vrijemeZavrsetka) {
        this.vrijemeZavrsetka = vrijemeZavrsetka;
    }

    @Override
    public void add(ProgramComponent programComponent) {
        components.add(programComponent);
    }

    @Override
    public List<ProgramComponent> getChildren() {
        return components;
    }

    @Override
    public void print() {       
        System.out.println(getId() + " " + getNazivPrograma() + " " + getVrijemePocetka() + " - " + getVrijemeZavrsetka());
    }
    
}
