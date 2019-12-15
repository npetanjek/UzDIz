/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import npetanjek_zadaca_2.entities.Vrsta;
import npetanjek_zadaca_2.helpers.ScheduleHelper;

/**
 *
 * @author Nikola
 */
public class VrstaFile extends FileLoader {

    public VrstaFile(String fileName) {
        super(fileName);
    }

    @Override
    public FileLoader load() {
        if (!readFile()) {
            System.out.println("Pogreska prilikom citanja " + getFileName());
            return null;
        }
        if (!fillTypeList()) {
            System.out.println("Lista vrsti emisija nije popunjena.");
            return null;
        }
        System.out.println("Datoteka " + getFileName() + " ucitana.");
        return this;
    }

    private boolean fillTypeList() {
        List<String[]> attributes = super.getAttributesList();

        if (attributes == null) {
            System.out.println("Pogreska prilikom citanja atributa iz " + getFileName());
            return false;
        }

        for (String[] attribute : attributes) {
            if (checkAttribute(attribute, attributes.indexOf(attribute))) {
                Vrsta vrsta = new Vrsta();
                vrsta.setId(Integer.parseInt(attribute[0].trim()));
                vrsta.setVrstaEmisije(attribute[1].trim());
                vrsta.setMozeImatiReklame(Short.parseShort(attribute[2].trim()));
                vrsta.setMaksTrajanjeReklama(Integer.parseInt(attribute[3].trim()));
                ScheduleHelper.getInstance().addType(vrsta);
            }
        }

        return true;
    }

    private boolean checkAttribute(String[] attribute, int index) {

        if (attribute.length < 4) {
            System.out.println("Nisu uneseni svi potrebni atributi za vrstu emisije br. "
                    + (index + 1) + ", vrsta emisije je preskocena.");
            return false;
        }

        if (attribute[0].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo unesen ID " + (index + 1) + ". retka u " + getFileName()
                    + ", vrsta emisije je preskocena.");
            return false;
        }
        
        if (!attribute[2].trim().equals("0") && !attribute[2].trim().equals("1")) {
            System.out.println("Krivo unesen atribut \"moze imati reklame\" " + (index + 1) + ". retka u " + getFileName()
                    + ", vrsta emisije je preskocena.");
            return false;
        }
        
        if (attribute[3].trim().equals("") || !checkRegex(attribute[0], "^\\d+$")) {
            System.out.println("Krivo uneseno maks trajanje reklama " + (index + 1) + ". retka u " + getFileName()
                    + ", vrsta emisije je preskocena.");
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
