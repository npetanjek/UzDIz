/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.entities;

import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.observers.Observer;
import npetanjek_zadaca_2.observers.Participant;
import npetanjek_zadaca_2.observers.Subject;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public abstract class AbstractEmisija implements ProgramComponent, Observer {

    private int id;
    private String nazivEmisije;
    private int vrstaEmisije;
    private int trajanje;    
    private List<Participant> participants;
    
    public AbstractEmisija() {
        participants = new ArrayList<>();
    }

    public AbstractEmisija(AbstractEmisija target) {
        if (target != null) {
            this.id = target.id;
            this.nazivEmisije = target.nazivEmisije;
            this.vrstaEmisije = target.vrstaEmisije;
            this.trajanje = target.trajanje;
            this.participants = new ArrayList<>();
            for (Participant participant : target.participants) {
                this.participants.add(participant);
            }
        }
    }
    
    @Override
    public abstract AbstractEmisija clone();

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivEmisije() {
        return nazivEmisije;
    }

    public void setNazivEmisije(String nazivEmisije) {
        this.nazivEmisije = nazivEmisije;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }
    
    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        if (participants != null && !participants.isEmpty()) {
            this.participants.addAll(participants);
        }
    }

    public int getVrstaEmisije() {
        return vrstaEmisije;
    }

    public void setVrstaEmisije(int vrstaEmisije) {
        this.vrstaEmisije = vrstaEmisije;
    }

    @Override
    public void print() {
        System.out.println(id + " " + nazivEmisije + " " + vrstaEmisije + " "
                + trajanje);
    }
    
    @Override
    public void update(Subject o) {
        for (Participant participant : participants) {
            if (participant.equals(o)) {
                participant.setState(o.getState());
            }
        }
    }

}
