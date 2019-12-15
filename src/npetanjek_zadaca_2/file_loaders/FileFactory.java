/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.file_loaders;

/**
 *
 * @author Nikola
 */
public class FileFactory {
    
    public FileLoader makeFile(String fileName, String fileType) {
        switch(fileType) {
            case "t":
                return new TVKucaFile(fileName).load();
            case "e":
                return new EmisijaFile(fileName).load();
            case "v":
                return new VrstaFile(fileName).load();
            case "o":
                return new OsobaFile(fileName).load();
            case "u":
                return new UlogaFile(fileName).load();
            default:
                return null;
        }
    }
    
}
