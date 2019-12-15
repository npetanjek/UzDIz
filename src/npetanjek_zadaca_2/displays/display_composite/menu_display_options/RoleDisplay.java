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
import npetanjek_zadaca_2.displays.iterators.ScheduleIterator;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.helpers.ScheduleHelper;
import npetanjek_zadaca_2.observers.Observer;
import npetanjek_zadaca_2.observers.Participant;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class RoleDisplay implements MenuItem {

    private Osoba person;
    private Uloga oldRole;
    private Uloga newRole;

    private ScheduleHelper scheduleHelper;

    public RoleDisplay() {
        scheduleHelper = ScheduleHelper.getInstance();
    }

    private boolean getUserInput() {
        for (Osoba osoba : scheduleHelper.getPeopleList()) {
            System.out.println(osoba.getId() + " " + osoba.getImeIPrezime());
        }
        Scanner s = new Scanner(System.in);
        System.out.println("Unesite ID osobe");
        String input = s.nextLine();
        if (input.equals("")) {
            System.out.println("Neispravan unos");
            return false;
        }
        int personId = Integer.parseInt(input);
        for (Uloga uloga : scheduleHelper.getRoleList()) {
            System.out.println(uloga.getId() + " " + uloga.getOpisUloge());
        }
        System.out.println("Unesite ID trenutne uloge");
        input = s.nextLine();
        if (input.equals("")) {
            System.out.println("Neispravan unos");
            return false;
        }
        int oldRoleId = Integer.parseInt(input);
        System.out.println("Unesite ID nove uloge");
        input = s.nextLine();
        if (input.equals("")) {
            System.out.println("Neispravan unos");
            return false;
        }
        int newRoleId = Integer.parseInt(input);
        person = scheduleHelper.getOsobaById(personId);
        oldRole = scheduleHelper.getUlogaById(oldRoleId);
        newRole = scheduleHelper.getUlogaById(newRoleId);
        
        if (person == null || oldRole == null || newRole == null) {
            return false;
        }

        System.out.println("");
        return true;
    }

    private void changeRole() {
        List<Program> channels = TVKuca.getInstance().getProgramlist();
        for (Program channel : channels) {
            ScheduleIterator dayIterator = new ScheduleIterator(channel.getChildren());
            while (dayIterator.hasNext()) {
                ProgramComponent dayComponent = dayIterator.next();
                ScheduleIterator showIterator = new ScheduleIterator(dayComponent.getChildren());
                while (showIterator.hasNext()) {
                    Emisija show = (Emisija) showIterator.next();
                    for (Participant participant : show.getParticipants()) {
                        if (participant.getOsoba().equals(person)) {
                            for (Observer observer : participant.getShowList()) {
                                if (participant.getState().equals(oldRole)) {
                                    participant.setState(newRole);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Uloga osobe " + person.getImeIPrezime() + " promijenjena iz " + oldRole.getOpisUloge() + " u " + newRole.getOpisUloge());
    }

    @Override
    public void print() {
        if (getUserInput()) {
            changeRole();
        }        
    }

    @Override
    public String toString() {
        return "Promjena uloge osobe u emisijama";
    }

}
