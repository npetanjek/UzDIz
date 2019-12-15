/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2;

import java.util.Scanner;
import npetanjek_zadaca_2.displays.display_builders.menu_builder.MenuBuildDirector;
import npetanjek_zadaca_2.displays.display_builders.menu_builder.MenuBuilder;
import npetanjek_zadaca_2.displays.display_builders.menu_builder.MenuBuilderImpl;
import npetanjek_zadaca_2.displays.display_composite.menu_composite.CompositeMenu;
import npetanjek_zadaca_2.entities.Program;
import npetanjek_zadaca_2.file_loaders.FileFactory;
import npetanjek_zadaca_2.file_loaders.FileLoader;
import npetanjek_zadaca_2.file_loaders.TVKucaFile;
import npetanjek_zadaca_2.schedulers.schedule_builder.ProgramBuilder;
import npetanjek_zadaca_2.schedulers.schedule_builder.ProgramBuilderImpl;
import npetanjek_zadaca_2.schedulers.schedule_builder.ScheduleBuildDirector;

/**
 *
 * @author Nikola
 */
public class Npetanjek_zadaca_2 {

    static String tvKucaFileName;
    static String emisijaFileName;
    static String vrstaFileName;
    static String osobaFileName;
    static String ulogaFileName;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (!checkArguments(args)) {
            System.out.println("Gasim program...");
            return;
        }

        initialize();

        for (Program program : TVKuca.getInstance().getProgramlist()) {
            ProgramBuilder builder = new ProgramBuilderImpl();
            ScheduleBuildDirector director = new ScheduleBuildDirector(builder);
            Program p = director.construct(program);
        }

        Scanner s = new Scanner(System.in);

        CompositeMenu menu = buildMenu();

        while (true) {
            menu.print();
            String choice = s.nextLine();
            if (choice.equals("0") || choice.equals("")) {
                System.out.println("Zaustavljam program...");
                return;
            }
            try {
                menu.runUserOption(Integer.parseInt(choice));
            } catch (NumberFormatException ex) {
                System.err.println("Neispravan format unosa");
            }
        }

    }

    private static CompositeMenu buildMenu() {
        final MenuBuilder menuBuilder = new MenuBuilderImpl();
        final MenuBuildDirector menuBuildDirector = new MenuBuildDirector(menuBuilder);
        CompositeMenu menu = menuBuildDirector.construct();
        return menu;
    }

    private static void initialize() {
        FileFactory fileFactory = new FileFactory();
        FileLoader tvKucaFile = fileFactory.makeFile(tvKucaFileName, "t");
        TVKuca.getInstance().startChannels(((TVKucaFile) tvKucaFile).getProgramList());    
        fileFactory.makeFile(ulogaFileName, "u");
        fileFactory.makeFile(osobaFileName, "o");        
        fileFactory.makeFile(vrstaFileName, "v");
        fileFactory.makeFile(emisijaFileName, "e");        
    }

    private static boolean checkArguments(String[] arguments) {
        if (arguments.length < 10) {
            System.out.println("Niste unijeli sve argumente!");
            return false;
        }
        return readFileNames(arguments);
    }

    private static boolean readFileNames(String[] arguments) {
        boolean tvKucaSet = false;
        boolean emisijeSet = false;
        boolean osobeSet = false;
        boolean ulogeSet = false;
        boolean vrsteSet = false;

        for (int i = 0; i < arguments.length; i += 2) {
            switch (arguments[i]) {
                case "-t":
                    if (arguments[i + 1].trim().equals("")) {
                        System.out.println("Niste unijeli naziv datoteke TV kuce!");
                        return false;
                    }
                    tvKucaFileName = arguments[i + 1];
                    tvKucaSet = true;
                    break;
                case "-e":
                    if (arguments[i + 1].trim().equals("")) {
                        System.out.println("Niste unijeli naziv datoteke emisija!");
                        return false;
                    }
                    emisijaFileName = arguments[i + 1];
                    emisijeSet = true;
                    break;
                case "-v":
                    if (arguments[i + 1].trim().equals("")) {
                        System.out.println("Niste unijeli naziv datoteke vrsta emisija!");
                        return false;
                    }
                    vrstaFileName = arguments[i + 1];
                    vrsteSet = true;
                    break;
                case "-o":
                    if (arguments[i + 1].trim().equals("")) {
                        System.out.println("Niste unijeli naziv datoteke osoba!");
                        return false;
                    }
                    osobaFileName = arguments[i + 1];
                    osobeSet = true;
                    break;
                case "-u":
                    if (arguments[i + 1].trim().equals("")) {
                        System.out.println("Niste unijeli naziv datoteke uloga!");
                        return false;
                    }
                    ulogaFileName = arguments[i + 1];
                    ulogeSet = true;
                    break;
            }
        }

        if (!tvKucaSet || !emisijeSet || !vrsteSet || !osobeSet || !ulogeSet) {
            System.out.println("Niste unijeli nazive svih datoteka!");
            return false;
        }

        return true;
    }

}
