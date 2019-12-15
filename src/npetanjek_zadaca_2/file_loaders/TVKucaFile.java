/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import npetanjek_zadaca_2.entities.Program;

/**
 *
 * @author Nikola
 */
public class TVKucaFile extends FileLoader {

    private final List<Program> programList;

    public TVKucaFile(String tvKucaFilePath) {
        super(tvKucaFilePath);
        programList = new ArrayList<>();
    }

    @Override
    public FileLoader load() {
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        if (!fillChannelList()) {
            System.out.println("Lista programa nije popunjena.");
            return null;
        }
        System.out.println("Datoteka " + getFileName() + " ucitana.");
        return this;
    }

    private boolean fillChannelList() {
        List<String[]> attributes = super.getAttributesList();

        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }

        for (String[] attribute : attributes) {

            if (checkAttribute(attribute, attributes.indexOf(attribute))) {
                Program program = new Program(getFilePath(getFileName()) + attribute[4].trim());
                program.setId(Integer.parseInt(attribute[0].trim()));
                program.setNazivPrograma(attribute[1].trim());
                program.setVrijemePocetka(attribute[2].trim());
                String vrijemeZavrsetka = attribute[3].trim();
                if (vrijemeZavrsetka.equals("")) {
                    vrijemeZavrsetka = "23:59";
                }
                program.setVrijemeZavrsetka(vrijemeZavrsetka);
                programList.add(program);
            }
        }

        return true;
    }

    private boolean checkAttribute(String[] attribute, int index) {

        if (attribute.length < 5) {
            System.out.println("Krivo unesen " + (index + 1) + ". redak u " + getFileName());
            return false;
        }

        if (attribute[0].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName());
            return false;
        }

        if (attribute[2].trim().equals("") || !checkRegex(attribute[2], "^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
            System.out.println("Krivo uneseno vrijeme pocetka programa u " + (index + 1) + ". retku!");
            return false;
        }

        if (!attribute[3].trim().equals("") && !checkRegex(attribute[3], "^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
            System.out.println("Krivo uneseno vrijeme zavrsetka programa u " + (index + 1) + ". retku!");
            return false;
        }

        if (attribute[4].trim().equals("")) {
            System.out.println("Krivo unesen naziv datoteke programa u " + (index + 1) + ". retku!");
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

    private String getFilePath(String fullPath) {
        String filePath = "";
        String pattern = Pattern.quote(System.getProperty("file.separator"));
        String[] fullPathSplit = fullPath.split(pattern);
        for (int i = 0; i < fullPathSplit.length - 1; i++) {
            filePath += fullPathSplit[i] + File.separator;
        }
        return filePath;
    }

    public List<Program> getProgramList() {
        return programList;
    }

}
