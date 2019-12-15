/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.iterators;

import java.util.Iterator;
import java.util.List;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ScheduleIterator implements Iterator<ProgramComponent> {
    
    private final List<ProgramComponent> programComponents;
    private int position;

    public ScheduleIterator(List<ProgramComponent> programComponents) {
        this.programComponents = programComponents;
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
        return programComponents.get(position++);
    }
    
}
