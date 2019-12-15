/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.iterators;

import java.util.Iterator;
import java.util.List;
import npetanjek_zadaca_2.entities.Emisija;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ShowTypeIterator implements Iterator<ProgramComponent> {
    
    private final int showType;
    private int position;

    private List<ProgramComponent> programComponents;

    public ShowTypeIterator(List<ProgramComponent> programComponents, int showType) {
        this.programComponents = programComponents;
        this.showType = showType;
    }

    @Override
    public boolean hasNext() {
        if (position >= programComponents.size() || programComponents.get(position) == null) {
            return false;
        }
        return true;
    }

    @Override
    public ProgramComponent next() {
        int type = ((Emisija) programComponents.get(position)).getVrstaEmisije();
        if (type == showType) {
            return programComponents.get(position++);
        }
        position++;
        return null;
    }
    
}
