/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npetanjek_zadaca_2.displays.decorators;

import npetanjek_zadaca_2.displays.iterators.ScheduleIterator;
import npetanjek_zadaca_2.schedulers.ProgramComponent;

/**
 *
 * @author Nikola
 */
public class ShowScheduleDecorator extends Decorator {

    public ShowScheduleDecorator(ProgramComponent decoratedComponent) {
        super(decoratedComponent);
    }

    @Override
    public void print() {
        decoratedComponent.print();
        System.out.println("---------------------------");
        System.out.format("ID\t%-40s\tTrajanje\t%-40s\n", "Naziv", "Osoba - Uloga");
        System.out.format("--\t%-40s\t--------\t%-40s\n", "---------------", "---------------");
        ScheduleIterator iterator = new ScheduleIterator(decoratedComponent.getChildren());
        while (iterator.hasNext()) {
            ProgramComponent component = (ProgramComponent) iterator.next();
            ShowDecorator showDecorator = new ShowDecorator(component);
            showDecorator.print();
            System.out.println();
        }
    }
    
}
