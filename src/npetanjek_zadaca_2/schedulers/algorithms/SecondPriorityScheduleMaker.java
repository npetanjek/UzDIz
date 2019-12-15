/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.schedulers.algorithms;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.entities.AbstractEmisija;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.helpers.ScheduleHelper;
import npetanjek_zadaca_2.observers.Participant;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author nikol
 */
public class SecondPriorityScheduleMaker implements Schedule {

    private final List<String[]> showsWithSecondPriority;
    private final ScheduleHelper scheduleHelper;
    private final Program channel;

    public SecondPriorityScheduleMaker(Program channel) {
        this.channel = channel;
        showsWithSecondPriority = new ArrayList<>();
        scheduleHelper = ScheduleHelper.getInstance();
    }

    @Override
    public void getChannelSchedule() {
        List<String[]> forRemoval = new ArrayList<>();
        for (String[] row : channel.getChannelSchedule()) {
            if (row.length > 1 && !row[1].trim().equals("")) {
                this.showsWithSecondPriority.add(row);
                forRemoval.add(row);
            }
        }
        channel.getChannelSchedule().removeAll(forRemoval);
    }

    @Override
    public void addShow() {
        for (String[] strings : showsWithSecondPriority) {

            for (Integer day : scheduleHelper.getDays(strings[1].trim())) {
                for (ProgramComponent dayComponent : channel.getChildren()) {
                    if (day == dayComponent.getId()) {
                        Emisija emisija = scheduleHelper.getEmisijaById(Integer.parseInt(strings[0].trim()));
                        if (emisija == null) {
                            System.out.println("Emisija s ID " + strings[0].trim() + " ne postoji.");
                            break;
                        }
                        if (fitInSchedule(emisija, dayComponent.getId())) {
                            if (strings.length == 4) {
                                List<Participant> participants = getParticipants(strings[3].trim(), emisija);
                                if (participants != null) {
                                    emisija.setParticipants(participants);
                                    for (Participant participant : participants) {
                                        participant.addObserver(emisija);
                                    }
                                }
                            }
                            dayComponent.add(emisija);
                        }
                    }
                }
            }
        }
    }

    private List<Participant> getParticipants(String attribute, AbstractEmisija show) {
        List<Participant> participants = new ArrayList<>();
        String[] osobeUlogeArray = attribute.trim().split(",");
        for (int i = 0; i < osobeUlogeArray.length; i++) {
            String[] osobaUloga = osobeUlogeArray[i].trim().split("-");
            Osoba osoba = scheduleHelper.getOsobaById(Integer.parseInt(osobaUloga[0].trim()));
            Uloga uloga = scheduleHelper.getUlogaById(Integer.parseInt(osobaUloga[1].trim()));
            if (osoba != null && uloga != null) {
                Participant participant = new Participant();
                participant.setOsoba(osoba);
                participant.setUloga(uloga);
                participant.addObserver(show);
                participants.add(participant);
            }
        }
        return participants;
    }

    private boolean fitInSchedule(Emisija show, int day) {
        LocalTime showStartTime = scheduleHelper.parseTime(this.channel.getVrijemePocetka());
        LocalTime showEndTime = showStartTime.plusMinutes(show.getTrajanje());

        List<ProgramComponent> showList = new ArrayList<>();
        for (ProgramComponent dayComponent : channel.getChildren()) {
            if (dayComponent.getId() == day) {
                showList = dayComponent.getChildren();
            }
        }

        if (showList.isEmpty()) {
            show.setPocetakEmisije(showStartTime);
            show.setZavrsetakEmisije(showStartTime.plusMinutes(show.getTrajanje()));
            return true;
        } else {
            for (ProgramComponent terminComponent : showList) {
                if (showStartTime.isBefore(((Emisija) terminComponent).getPocetakEmisije()) 
                        && (showEndTime.isAfter(((Emisija) terminComponent).getPocetakEmisije()) 
                        || showEndTime.equals(((Emisija) terminComponent).getZavrsetakEmisije()) 
                        || showEndTime.isAfter(((Emisija) terminComponent).getZavrsetakEmisije()))) {
                    showStartTime = ((Emisija) terminComponent).getZavrsetakEmisije();
                    showEndTime = showStartTime.plusMinutes(show.getTrajanje());
                } else if (showStartTime.equals(((Emisija) terminComponent).getPocetakEmisije())) {
                    showStartTime = ((Emisija) terminComponent).getZavrsetakEmisije();
                    showEndTime = showStartTime.plusMinutes(show.getTrajanje());
                }
            }
            if (showStartTime.isBefore(scheduleHelper.parseTime(this.channel.getVrijemeZavrsetka()))) {
                if (!showEndTime.isBefore(showStartTime)) {
                    show.setPocetakEmisije(showStartTime);
                    show.setZavrsetakEmisije(showStartTime.plusMinutes(show.getTrajanje()));
                    return true;
                }
            }
        }
        return false;
    }

}
