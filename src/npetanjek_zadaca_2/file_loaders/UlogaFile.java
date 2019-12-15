/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.helpers.ScheduleHelper;

/**
 *
 * @author nikol
 */
public class UlogaFile extends FileLoader {

    public UlogaFile(String fileName) {
        super(fileName);
    }

    @Override
    public FileLoader load() {
        
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        
        if (!fillRoleList()) {
            System.out.println("Lista uloga nije popunjena.");
            return null;
        }
        
        System.out.println("Datoteka " + getFileName() + " ucitana.");
        
        return this;
        
    }
    
    private boolean fillRoleList() {
        List<String[]> attributes = super.getAttributesList();
        
        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }
        
        for (String[] attribute : attributes) {
            if (checkAttribute(attribute, attributes.indexOf(attribute))) {
                Uloga uloga = new Uloga();
                uloga.setId(Integer.parseInt(attribute[0].trim()));
                uloga.setOpisUloge(attribute[1].trim());
                ScheduleHelper.getInstance().addRole(uloga);
            }
        }
        
        return true;
    }
    
    private boolean checkAttribute (String[] attribute, int index) {
        
        if (attribute.length < 2) {
            System.out.println("Nisu uneseni svi potrebni atributi za ulogu br. " + 
                        (index + 1) + ", uloga je preskocena.");
            return false;
        }
        
        if (attribute[0].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName() + 
                    ", uloga je preskocena.");
            return false;
        }
        
        if (attribute[1].trim().equals("")) {
            System.out.println("Krivo unesen opis uloge u " + (index + 1) + ". retku u " + getFileName() + 
                    ", uloga je preskocena.");
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
