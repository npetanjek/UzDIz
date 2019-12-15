/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.helpers;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import npetanjek_zadaca_2.entities.AbstractEmisija;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.entities.Osoba;
import npetanjek_zadaca_2.entities.Uloga;
import npetanjek_zadaca_2.entities.Vrsta;

/**
 *
 * @author Nikola
 */
public class ScheduleHelper {

    private static ScheduleHelper INSTANCE;

    private List<AbstractEmisija> showList;
    private List<Vrsta> typesList;
    private List<Osoba> peopleList;
    private List<Uloga> roleList;

    private ScheduleHelper() {
        showList = new ArrayList<>();
        typesList = new ArrayList<>();
        peopleList = new ArrayList<>();
        roleList = new ArrayList<>();
    }

    public static ScheduleHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScheduleHelper();
        }
        return INSTANCE;
    }

    public void addShow(AbstractEmisija emisija) {
        this.showList.add(emisija);
    }

    public void addType(Vrsta vrsta) {
        this.typesList.add(vrsta);
    }

    public void addPerson(Osoba osoba) {
        this.peopleList.add(osoba);
    }

    public void addRole(Uloga uloga) {
        this.roleList.add(uloga);
    }

    public Emisija getEmisijaById(int id) {
        Emisija e = null;
        if (showList != null && !showList.isEmpty()) {
            for (AbstractEmisija emisija : showList) {
                if (emisija.getId() == id) {
                        e = (Emisija) emisija.clone();
                }
            }
        }
        return e;
    }

    public Vrsta getVrstaById(int id) {
        Vrsta v = null;
        if (typesList != null && !typesList.isEmpty()) {
            for (Vrsta vrsta : typesList) {
                if (vrsta.getId() == id) {
                    v = vrsta;
                }
            }
        }
        return v;
    }

    public Osoba getOsobaById(int id) {
        Osoba o = null;
        if (peopleList != null && !peopleList.isEmpty()) {
            for (Osoba osoba : peopleList) {
                if (osoba.getId() == id) {
                    o = osoba;
                }
            }
        }
        return o;
    }

    public Uloga getUlogaById(int id) {
        Uloga u = null;
        if (roleList != null && !roleList.isEmpty()) {
            for (Uloga uloga : roleList) {
                if (uloga.getId() == id) {
                    u = uloga;
                }
            }
        }
        return u;
    }

    public LocalTime parseTime(String time) {
        String[] timeSplit = time.split(":");
        switch (timeSplit.length) {
            case 2:
                return LocalTime.of(Integer.parseInt(timeSplit[0].trim()), Integer.parseInt(timeSplit[1].trim()));
            case 3:
                return LocalTime.of(Integer.parseInt(timeSplit[0].trim()), Integer.parseInt(timeSplit[1].trim()), Integer.parseInt(timeSplit[2].trim()));
            default:
                return null;
        }
    }

    public List<Integer> getDays(String attribute) {
        List<Integer> days = new ArrayList<>();
        String[] attributeSplit;
        if (attribute.contains("-")) {
            attributeSplit = attribute.trim().split("-");
            int firstDay = Integer.parseInt(attributeSplit[0].trim());
            int lastDay = Integer.parseInt(attributeSplit[1].trim());
            for (int i = firstDay; i <= lastDay; i++) {
                days.add(i);
            }
        } else {
            attributeSplit = attribute.trim().split(",");
            for (String attr : attributeSplit) {
                days.add(Integer.parseInt(attr));
            }
        }
        return days;
    }

    public List<Vrsta> getTypesList() {
        return typesList;
    }

    public List<AbstractEmisija> getShowList() {
        return showList;
    }

    public List<Osoba> getPeopleList() {
        return peopleList;
    }

    public List<Uloga> getRoleList() {
        return roleList;
    }
}
