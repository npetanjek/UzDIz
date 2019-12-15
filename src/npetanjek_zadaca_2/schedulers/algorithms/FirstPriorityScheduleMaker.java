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
public class FirstPriorityScheduleMaker implements Schedule {

    private final List<String[]> showsWithFirstPriority;
    private final ScheduleHelper scheduleHelper;
    private final Program channel;

    public FirstPriorityScheduleMaker(Program channel) {
        this.channel = channel;
        showsWithFirstPriority = new ArrayList<>();
        scheduleHelper = ScheduleHelper.getInstance();
    }

    @Override
    public void addShow() {
        for (String[] row : showsWithFirstPriority) {
            for (Integer day : scheduleHelper.getDays(row[1].trim())) {
                for (ProgramComponent dayComponent : channel.getChildren()) {
                    if (day == dayComponent.getId()) {
                        Emisija emisija = scheduleHelper.getEmisijaById(Integer.parseInt(row[0].trim()));
                        if (emisija == null) {
                            System.out.println("Emisija s ID " + row[0].trim() + " ne postoji.");
                            break;
                        }
                        LocalTime showStart = scheduleHelper.parseTime(row[2].trim());
                        if (!isOverlapping(emisija, showStart, day)) {
                            emisija.setPocetakEmisije(showStart);
                            emisija.setZavrsetakEmisije(showStart.plusMinutes(emisija.getTrajanje()));
                            if (row.length == 4) {
                                List<Participant> participants = getParticipants(row[3].trim(), emisija);
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

    private boolean isOverlapping(AbstractEmisija show, LocalTime showStartTime, int day) {
        LocalTime channelStart = scheduleHelper.parseTime(this.channel.getVrijemePocetka());
        LocalTime channelEnd = scheduleHelper.parseTime(this.channel.getVrijemeZavrsetka());
        LocalTime showEndTime = showStartTime.plusMinutes(show.getTrajanje());
        List<ProgramComponent> terminList = new ArrayList<>();
        for (ProgramComponent dayComponent : channel.getChildren()) {
            if (dayComponent.getId() == day) {
                terminList = dayComponent.getChildren();
            }
        }
        if (terminList.isEmpty()) {
            return false;
        } else {
            for (ProgramComponent terminComponent : terminList) {
                if (showStartTime.isBefore(((Emisija) terminComponent).getPocetakEmisije())
                        && (showEndTime.isAfter(((Emisija) terminComponent).getPocetakEmisije())
                        || showEndTime.isAfter(((Emisija) terminComponent).getZavrsetakEmisije()))) {
                    System.out.println("Emisija " + show.getId() + " " + show.getNazivEmisije() + 
                            " se preklapa s ostalim emisijama te nije dodana u raspored.");
                    return true;
                } else if (showStartTime.equals(((Emisija) terminComponent).getPocetakEmisije())) {
                    System.out.println("Emisija " + show.getId() + " " + show.getNazivEmisije() + 
                            " se preklapa s ostalim emisijama te nije dodana u raspored.");
                    return true;
                }
            }
        }
        if (showEndTime.isBefore(showStartTime) || showStartTime.isBefore(channelStart) || showEndTime.isAfter(channelEnd)) {
            System.out.println("Emisija " + show.getId() + " " + show.getNazivEmisije() + 
                    " nije unutar vremenskog intervala trajanja programa te nije dodana u raspored");
            return true;
        }
        return false;
    }

    @Override
    public void getChannelSchedule() {
        List<String[]> forRemoval = new ArrayList<>();

        for (String[] row : channel.getChannelSchedule()) {
            if (row.length > 2 && !row[1].trim().equals("") && !row[2].trim().equals("")) {
                this.showsWithFirstPriority.add(row);
                forRemoval.add(row);
            }
        }
        channel.getChannelSchedule().removeAll(forRemoval);
    }

}
