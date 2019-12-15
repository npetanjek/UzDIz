/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public abstract class FileLoader {
    
    private final String fileName;
    private List<String[]> attributesList;
    
    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    protected List<String[]> getAttributesList() {
        return attributesList;
    }
    
    protected boolean readFile() {
        attributesList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Ne postoji datoteka s nazivom: " + fileName);
            return false;
        }
        BufferedReader br;
        String[] attributes;
        try {            
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                attributes = line.split("[;]");
                attributesList.add(attributes);
            }
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public abstract FileLoader load();
    
}
