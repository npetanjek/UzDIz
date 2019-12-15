/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.helpers.ScheduleHelper;

/**
 *
 * @author nikol
 */
public class OsobaFile extends FileLoader {

    public OsobaFile(String fileName) {
        super(fileName);
    }

    @Override
    public FileLoader load() {
        
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        
        if (!fillPeopleList()) {
            System.out.println("Lista osoba nije popunjena.");
            return null;
        }
        
        System.out.println("Datoteka " + getFileName() + " ucitana.");
        return this;
        
    }
    
    private boolean fillPeopleList() {
        List<String[]> attributes = super.getAttributesList();
        
        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }
        
        for (String[] attribute : attributes) {
            if (checkAttribute(attribute, attributes.indexOf(attribute))) {
                Osoba osoba = new Osoba();
                osoba.setId(Integer.parseInt(attribute[0].trim()));
                osoba.setImeIPrezime(attribute[1].trim());
                ScheduleHelper.getInstance().addPerson(osoba);
            }
        }
        
        return true;
    }
    
    private boolean checkAttribute (String[] attribute, int index) {
        
        if (attribute.length != 2) {
            System.out.println("Pogrešan broj atributa za osobu br. " + 
                        (index + 1) + ", osoba je preskocena.");
            return false;
        }
        
        if (attribute[0].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName() + 
                    ", osoba je preskocena.");
            return false;
        }
        
        if (attribute[1].trim().equals("")) {
            System.out.println("Krivo uneseno ime osobe u " + (index + 1) + ". retku u " + getFileName() + 
                    ", osoba je preskocena.");
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
