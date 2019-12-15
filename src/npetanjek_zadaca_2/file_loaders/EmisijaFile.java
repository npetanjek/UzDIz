/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import npetanjek_zadaca_2.entities.AbstractEmisija;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.helpers.ScheduleHelper;
import npetanjek_zadaca_2.observers.Participant;

/**
 *
 * @author Nikola
 */
public class EmisijaFile extends FileLoader {
    
    ScheduleHelper scheduleHelper;
    
    public EmisijaFile(String fileName) {
        super(fileName);
        scheduleHelper = ScheduleHelper.getInstance();
    }

    @Override
    public FileLoader load() {
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        if (!fillShowList()) {
            System.out.println("Lista emisija nije popunjena.");
            return null;
        }
        System.out.println("Datoteka " + getFileName() + " ucitana.");        
        return this;
    }

    private boolean fillShowList() {
        List<String[]> attributes = super.getAttributesList();

        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }

        for (String[] attribute : attributes) {
            if (!checkAttribute(attribute, attributes.indexOf(attribute))) {
                continue;
            }

            AbstractEmisija emisija = new Emisija();
            emisija.setId(Integer.parseInt(attribute[0].trim()));
            emisija.setNazivEmisije(attribute[1].trim());
            if (scheduleHelper.getVrstaById(Integer.parseInt(attribute[2].trim())) == null) {
                continue;
            }
            emisija.setVrstaEmisije(Integer.parseInt(attribute[2].trim()));
            emisija.setTrajanje(Integer.parseInt(attribute[3].trim()));
            if (attribute.length == 5) {
                List<Participant> participants = getParticipants(attribute[4].trim());
                if (participants != null) {
                    emisija.setParticipants(participants);
                    for (Participant participant : participants) {
                        participant.addObserver(emisija);
                    }
                }
            }
            scheduleHelper.addShow(emisija);
        }

        return true;

    }
    
    private List<Participant> getParticipants (String attribute) {
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
                participants.add(participant);
            }
        }
        return participants;
    }

    private boolean checkAttribute(String[] attribute, int index) {

        if (attribute.length < 4) {
            System.out.println("Nisu uneseni svi potrebni atributi za emisiju br. "
                    + (index + 1) + ", emisija je preskocena.");
            return false;
        }

        if (attribute[0].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName()
                    + ", emisija je preskocena.");
            return false;
        }

        if (attribute[2].trim().equals("") || !checkRegex(attribute[2], "^\\d+$")) {
            System.out.println("Krivo unesena vrsta emisije " + (index + 1) + ". retka u " + getFileName()
                    + ", emisija je preskocena.");
        }

        if (attribute[3].trim().equals("") || !checkRegex(attribute[3], "^\\d+$")) {
            System.out.println("Krivo uneseno vrijeme trajanja emisije " + (index + 1) + ". retka u " + getFileName()
                    + ", emisija je preskocena.");
            return false;
        }

        if (attribute.length == 5) {
            String[] osobaUloga = attribute[4].split(",");
            for (String oU : osobaUloga) {
                if (!checkRegex(oU, "^\\d+-\\d+$")) {
                    System.out.println("Neispravan format osoba-uloga u " + (index + 1) + " .retku " + getFileName()
                            + ", emisija je preskocena.");
                    return false;
                }
            }
        }

        if (attribute.length > 5) {
            System.out.println("Neispravan zapis u " + (index + 1) + ". retku u " + getFileName()
                    + "; uneseno je previse elemenata. Emisija je preskocena.");
            return false;
        }

        return true;
    }

    private boolean checkRegex(String attribute, String regex) {
        Pattern pattern;
        Matcher m;

        pattern = Pattern.compile(regex);
        m = pattern.matcher(attribute.trim());
        return m.matches();
    }

}
