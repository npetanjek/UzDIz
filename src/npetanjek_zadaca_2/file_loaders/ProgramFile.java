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

/**
 *
 * @author Nikola
 */
public class ProgramFile extends FileLoader {

    private List<String[]> channelSchedule;

    public ProgramFile(String fileName) {
        super(fileName);
        channelSchedule = new ArrayList<>();
    }

    @Override
    public FileLoader load() {
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        if (!fillChannelSchedule()) {
            System.out.println("Program nije popunjen zapisima iz " + getFileName());
            return null;
        }
        System.out.println("Datoteka " + getFileName() + " ucitana.");
        return this;
    }

    private boolean fillChannelSchedule() {

        List<String[]> attributes = super.getAttributesList();

        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }

        for (String[] attribute : attributes) {

            if (!checkAttribute(attribute, attributes.indexOf(attribute))) {
                continue;
            }

            channelSchedule.add(attribute);

        }

        return true;

    }

    private boolean checkAttribute(String[] attribute, int index) {

        if (attribute[0].trim().equals("") || !checkRegex(attribute[0].trim(), "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName()
                    + ", redak je preskocen.");
            return false;
        }

        if (attribute.length > 1) {
            if (!attribute[1].trim().equals("") && !checkRegex(attribute[1], "^[1-6]-[1-7]|([1-7],)+[1-7]|[1-7]$")) {
                System.out.println("Neispravan format dana u tjednu za emisiju " + attribute[0].trim()
                        + " u " + getFileName() + ", redak " + (index + 1) + ", redak je preskocen.");
                return false;
            }
            if (attribute.length > 2) {
                if (!attribute[2].trim().equals("") && !checkRegex(attribute[2], "^(0[0-9]|1[0-9]|2[0-3]|[0-9])(:[0-5][0-9]){1,2}$")) {
                    System.out.println("Neispravan format vremena pocetka za emisiju " + attribute[0].trim()
                            + " u " + getFileName() + ", redak " + (index + 1) + ", redak je preskocen.");
                    return false;
                }
                if (attribute.length == 4) {
                    String[] osobaUloga = attribute[3].split(",");
                    for (String oU : osobaUloga) {
                        if (!attribute[3].trim().equals("") && !checkRegex(oU, "^\\d+-\\d+$")) {
                            System.out.println("Neispravan format osoba-uloga u " + (index + 1) + " .retku " + getFileName()
                                    + ", emisija je preskocena.");
                            return false;
                        }
                    }
                }
            }
        }
        if (attribute.length > 4) {
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

    public List<String[]> getChannelSchedule() {
        return channelSchedule;
    }

    public void setChannelSchedule(List<String[]> channelSchedule) {
        this.channelSchedule = channelSchedule;
    }

}
